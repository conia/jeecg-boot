package org.jeecg.modules.quartz.job;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jeecg.common.util.RestUtil;
import org.jeecg.modules.demo.om.entity.OmModelRun;
import org.jeecg.modules.demo.om.entity.OmTask;
import org.jeecg.modules.demo.om.entity.OmTaskCheckInfo;
import org.jeecg.modules.demo.om.service.IOmModelRunService;
import org.jeecg.modules.demo.om.service.IOmTaskService;
import org.jeecg.modules.demo.om.util.ModelUtil;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 示例带参定时任务
 * 
 * @Author Scott
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
@Slf4j
public class ModelCheckJob implements Job {

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
		log.info("Model Check Task **** IN ****");

		try {
			QueryWrapper<OmTask> qwGetTask = new QueryWrapper<>();
			qwGetTask.eq("status", "3").orderByAsc("create_time");
			List<OmTask> tasks = omTaskService.list(qwGetTask);
			if (tasks == null || tasks.size() == 0) {
				// no task need to be handled
				log.info("no task should be checked");
			} else {
				for (OmTask task : tasks) {
					String dirName = task.getDirName();
					if (task.isTrain()) {
						if (StringUtils.isNotBlank(dirName) && StringUtils.isBlank(task.getWandb())) {
							String urlChecker = ModelUtil.getTrainTaskCheckerUrl();
							OmTaskCheckInfo checkInfo = OmTaskCheckInfo.builder()
									.dirName(dirName)
									.taskType(task.getTaskType())
									.build();
							JSONObject obj = RestUtil.post(urlChecker, (JSONObject) JSON.toJSON(checkInfo));
							int code = (int) obj.get("code");
							if (code == 0) {
								log.info("The return data is {}", obj.toJSONString());
								LinkedHashMap<String, Object> result = (LinkedHashMap) obj.get("result");
								String wandbUrl = (String) result.get("wandb_url");
								task.setWandb(wandbUrl);
								omTaskService.updateById(task);
								log.info("success to update train task checker info");
							} else {
								// error
								String msg = (String) obj.get("message");
								log.error("error when check the train task info: {}, with msg: {}", task.getId(), msg);
							}
						}
					}
				}
			}
		}catch(Exception ex){
			log.error("error when checking model task",ex);
		}

		log.info("Model Check Task **** OUT ****");
	}








}
