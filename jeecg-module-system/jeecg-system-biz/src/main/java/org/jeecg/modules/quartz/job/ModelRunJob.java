package org.jeecg.modules.quartz.job;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.util.RestUtil;
import org.jeecg.modules.demo.om.entity.OmModelRun;
import org.jeecg.modules.demo.om.entity.OmTask;
import org.jeecg.modules.demo.om.service.IOmModelRunService;
import org.jeecg.modules.demo.om.service.IOmTaskService;
import org.jeecg.modules.demo.om.util.ModelUtil;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.LinkedHashMap;

/**
 * 示例带参定时任务
 * 
 * @Author Scott
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
@Slf4j
public class ModelRunJob implements Job {

	@Autowired
	private IOmTaskService omTaskService;

	@Autowired
	private IOmModelRunService iOmModelRunService;

	/**
	 * 若参数变量名修改 QuartzJobController中也需对应修改
	 */
	private String parameter;



	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	private String getTaskTypeName(String taskType){
		if("1".equals(taskType)){
			return "train";
		} else if ("2".equals(taskType)) {
			return "eval";
		}else if ("3".equals(taskType)) {
			return "deploy";
		}else{
			return "unknown";
		}
	}

	private String getRunUrl(String taskType){
		switch (taskType){
			case "1":
				return ModelUtil.getTrainTaskUrl();
			case "2":
				return ModelUtil.getEvalTaskUrl();
			case "3":
				return ModelUtil.getDeployTaskUrl();
			default:
				return null;
		}
	}

	private void runTrain() throws Exception{

	}
	private void runEval() throws Exception{

	}
	private void runDeploy() throws Exception{

	}


	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		log.info("Model Task **** IN ****");
//		log.info(" Model Train Job Execution key："+jobExecutionContext.getJobDetail().getKey());
//		log.info( String.format("welcome %s! Jeecg-Boot 带参数定时任务 SampleParamJob !   时间:" + DateUtils.now(), this.parameter));
		OmTask task = null;
		try{

//			omTaskService.
			// 1 get train task can be running
			// db has task with status: init
			// current running task count
			// concurrent count is greater than the running task count
			QueryWrapper<OmTask> qwGetTask = new QueryWrapper<>();
			qwGetTask.eq("status","1").orderByAsc("create_time").last("limit 1");
			task= omTaskService.getOne(qwGetTask);
			if(task == null){
				// no task need to be handled
				log.info("no task should be started");
			}else{
				String taskType = task.getTaskType();
				String taskTypeName = getTaskTypeName(taskType);
				boolean isTrain = ModelUtil.isTrainTask(taskType);
				boolean isEval = ModelUtil.isEvalTask(taskType);
				boolean isDeploy = ModelUtil.isDeployTask(taskType);
				log.info("Find a [{}] task with id {} to be started", taskTypeName, task.getId());
				QueryWrapper<OmTask> qwGetRunningTask = new QueryWrapper<>();
				qwGetRunningTask.between("status","2","4");

				long count = omTaskService.count(qwGetRunningTask);
				if(count >= ModelUtil.getTrainConcurrent()){
					log.info(String.format("QUIT, Running count (status>=2 and status <=4) is %d, greater than the threshold (concurrent count %d)",
							count,
							ModelUtil.getTrainConcurrent()));
					return;
				}else{

					// train task
					// eval task

					// deploy task

					// starting
					task.setStatus("2");
					task.setStartTime(new Date());
					omTaskService.updateById(task);
					log.info("Update task status to starting: {}",task.getId());

					String urlRunTrain = getRunUrl(taskType);

					OmModelRun runInfo = null;
					if(isTrain) {
						runInfo = iOmModelRunService.getModelTrainInfo(task.getId());
					}
					if(isEval || isDeploy) {
						runInfo = iOmModelRunService.getModelEvalInfo(task.getId());
					}
					if(runInfo == null){
						throw new Exception(String.format("获取模型[%s]任务[%s]详细信息时出现错误：返回为空",taskTypeName,task.getId()));
					}

					JSONObject obj = RestUtil.post(urlRunTrain, (JSONObject) JSON.toJSON(runInfo));
					int code = (int)obj.get("code");
					if(code == 0){
						log.info("The return data is {}",obj.toJSONString());
						// success running
						task.setStatus("3");
						LinkedHashMap<String,Object> result = (LinkedHashMap)obj.get("result");
						int shellRCode = (int)result.get("shell_rcode");
						if(shellRCode != 0){
							throw new Exception("error when starting the train task: shel return code is "+shellRCode);
						}
						String processId = (String)result.get("process");
						task.setProcessId(processId);
						String dirName = (String) result.get("dir_name");
						task.setDirName(dirName);

						if(isEval){
							int port = (Integer) result.get("port");
							task.setPort(port);
						}

						omTaskService.updateById(task);
						log.info("success to start the train task");
					}else{
						// error
						String msg = (String)obj.get("message");
						throw new Exception(msg);
					}
				}
			}
		}catch(Exception ex){
			log.error("error when handling model task",ex);
			if(task != null){
				try {
					// change the task to fail
					task.setStatus("6");
					task.setMessage(ex.getMessage());
					task.setEndTime(new Date());
					omTaskService.updateById(task);
				}catch(Exception exx){
					log.error("error when updating task to failed",ex);
				}
			}
		}

		log.info("Model Task **** OUT ****");
	}








}
