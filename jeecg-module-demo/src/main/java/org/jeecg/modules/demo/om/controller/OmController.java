package org.jeecg.modules.demo.om.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.modules.demo.om.entity.OmModel;
import org.jeecg.modules.demo.om.entity.OmTask;
import org.jeecg.modules.demo.om.service.IOmService;
import org.jeecg.modules.demo.om.service.IOmTaskService;
import org.jeecg.modules.demo.om.vo.OmModelTask;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @Description: 模型及训练记录
* @Author: hejx
* @Date:   2023-11-17
* @Version: V1.0
*/
@Api(tags="模型及任务")
@RestController
@RequestMapping("/om/mix")
@Slf4j
public class OmController extends JeecgController<OmTask, IOmTaskService> {
   @Autowired
   private IOmService omService;



   /**
    *   添加
    *
    * @param omModelTask
    * @return
    */
   @AutoLog(value = "模型及训练记录-添加")
   @ApiOperation(value="模型及训练记录-添加", notes="模型及训练记录-添加")
   @RequiresPermissions("om:om_task:add")
   @PostMapping(value = "/add")
   public Result<String> add(@RequestBody OmModelTask omModelTask) {

       OmModel omModel = new OmModel();
       BeanUtils.copyProperties(omModelTask.getModel(), omModel);
       omService.saveModelAndTask(omModel,omModelTask.getModel().getOmModelDataList(),omModelTask.getTask());

       return Result.OK("添加成功！");
   }




}
