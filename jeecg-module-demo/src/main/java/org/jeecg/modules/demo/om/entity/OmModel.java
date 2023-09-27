package org.jeecg.modules.demo.om.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 模型
 * @Author: jeecg-boot
 * @Date:   2023-08-31
 * @Version: V1.0
 */
@ApiModel(value="om_model对象", description="模型")
@Data
@TableName("om_model")
public class OmModel implements Serializable {
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
	/**名称*/
	@Excel(name = "名称", width = 15)
    @ApiModelProperty(value = "名称")
    private java.lang.String modelName;
	/**本地路径*/
	@Excel(name = "本地路径", width = 15)
    @ApiModelProperty(value = "本地路径")
    private java.lang.String modelPath;
	/**模型来源*/
	@Excel(name = "模型来源", width = 15, dicCode = "model_src_type")
    @Dict(dicCode = "model_src_type")
    @ApiModelProperty(value = "模型来源")
    private java.lang.String modelSrc;
	/**类型*/
	@Excel(name = "类型", width = 15, dicCode = "model_type")
    @Dict(dicCode = "model_type")
    @ApiModelProperty(value = "类型")
    private java.lang.String modelType;
	/**参数大小*/
	@Excel(name = "参数大小", width = 15)
    @ApiModelProperty(value = "参数大小")
    private java.lang.String paramSize;
	/**上下文长度*/
	@Excel(name = "上下文长度", width = 15)
    @ApiModelProperty(value = "上下文长度")
    private java.lang.String tokenSize;
	/**模型大小*/
	@Excel(name = "模型大小", width = 15)
    @ApiModelProperty(value = "模型大小")
    private java.lang.String totalSize;
    /**父模型*/
    @Excel(name = "父模型", width = 15, dictTable = "om_model m left join om_task t on m.train_task_id = t.id  where t.status = 5 or m.model_src = 1", dicText = "model_name", dicCode = "m.id")
    @Dict(dictTable = "om_model m left join om_task t on m.train_task_id = t.id  where t.status = 5 or m.model_src = 1", dicText = "model_name", dicCode = "m.id")
    @ApiModelProperty(value = "父模型")
    private java.lang.String baseModeId;
	/**训练状态*/
	@Excel(name = "训练状态", width = 15, dicCode = "model_train_status")
    @Dict(dicCode = "model_train_status")
    @ApiModelProperty(value = "训练状态")
    private java.lang.String modelTrainStatus;
	/**发布状态*/
	@Excel(name = "发布状态", width = 15, dicCode = "model_deploy_status")
    @Dict(dicCode = "model_deploy_status")
    @ApiModelProperty(value = "发布状态")
    private java.lang.String modelStatus;
    /**训练任务ID*/
    @Excel(name = "训练任务ID", width = 15)
    @Dict(dictTable = "om_task", dicText = "status", dicCode = "id")
    @ApiModelProperty(value = "训练任务ID")
    private java.lang.String trainTaskId;
    /**评估任务ID*/
    @Excel(name = "评估任务ID", width = 15)
    @Dict(dictTable = "om_task", dicText = "status", dicCode = "id")
    @ApiModelProperty(value = "评估任务ID")
    private java.lang.String evalTaskId;
    /**发布任务ID*/
    @Excel(name = "发布任务ID", width = 15)
    @Dict(dictTable = "om_task", dicText = "status", dicCode = "id")
    @ApiModelProperty(value = "发布任务ID")
    private java.lang.String deployTaskId;
}
