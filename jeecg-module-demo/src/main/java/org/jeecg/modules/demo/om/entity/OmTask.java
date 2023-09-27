package org.jeecg.modules.demo.om.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 模型训练记录
 * @Author: jeecg-boot
 * @Date:   2023-09-06
 * @Version: V1.0
 */
@Data
@TableName("om_task")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="om_task对象", description="模型训练记录")
public class OmTask implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
	/**开始时间*/
	@Excel(name = "开始时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "开始时间")
    private java.util.Date startTime;
	/**结束时间*/
	@Excel(name = "结束时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "结束时间")
    private java.util.Date endTime;
	/**任务名称*/
	@Excel(name = "任务名称", width = 15)
    @ApiModelProperty(value = "任务名称")
    private java.lang.String name;
	/**训练参数*/
	@Excel(name = "训练参数", width = 15)
    @ApiModelProperty(value = "训练参数")
    private java.lang.String params;
	/**环境变量*/
	@Excel(name = "环境变量", width = 15)
    @ApiModelProperty(value = "环境变量")
    private java.lang.String envs;
	/**任务状态*/
	@Excel(name = "任务状态", width = 15, dicCode = "model_run_status")
	@Dict(dicCode = "model_run_status")
    @ApiModelProperty(value = "任务状态")
    private java.lang.String status;
	/**任务信息*/
	@Excel(name = "任务信息", width = 15)
    @ApiModelProperty(value = "任务信息")
    private java.lang.String message;
	/**mlflow*/
	@Excel(name = "mlflow", width = 15)
    @ApiModelProperty(value = "mlflow")
    private java.lang.String mlflow;
	/**wandb*/
	@Excel(name = "wandb", width = 15)
    @ApiModelProperty(value = "wandb")
    private java.lang.String wandb;
	/**模型*/
	@Excel(name = "模型", width = 15, dictTable = "om_model", dicText = "model_name", dicCode = "id")
	@Dict(dictTable = "om_model", dicText = "model_name", dicCode = "id")
    @ApiModelProperty(value = "模型")
    private java.lang.String modelId;

    /**保存地址*/
    @Excel(name = "保存地址", width = 15)
    @ApiModelProperty(value = "保存地址")
    private java.lang.String dirName;

	/**进程id*/
	@Excel(name = "进程id", width = 15)
    @ApiModelProperty(value = "进程id")
    private java.lang.String processId;
	/**任务类型*/
	@Excel(name = "任务类型", width = 15, dicCode = "model_task_type")
	@Dict(dicCode = "model_task_type")
    @ApiModelProperty(value = "任务类型")
    private java.lang.String taskType;
    /**部署端口*/
    @Excel(name = "部署端口", width = 15)
    @ApiModelProperty(value = "部署端口")
    private java.lang.Integer port;

//    @Transient
//    public boolean isTrain() {
//      return "1".equals(taskType);
//    }
//
//    @Transient
//    public boolean isEval() {
//        return "2".equals(taskType);
//    }
//
//    @TableField(exist = false)
//    public boolean isDeploy() {
//        return "3".equals(taskType);
//    }

}
