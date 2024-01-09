package org.jeecg.modules.demo.om.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecg.modules.demo.om.entity.OmTask;

/**
 * @title: OmModelTask
 * @description:
 * @author: hejunxiang
 * @date: 2023.11.17 14:04
 */
@Data
@ApiModel(value="model_and_task", description="同时进行模型和任务的相关操作")
public class OmModelTask {
    @ApiModelProperty(value = "模型")
    private OmModelPage model;
    @ApiModelProperty(value = "任务")
    private OmTask task;
}
