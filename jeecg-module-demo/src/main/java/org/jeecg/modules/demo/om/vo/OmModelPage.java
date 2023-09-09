package org.jeecg.modules.demo.om.vo;

import java.util.List;
import org.jeecg.modules.demo.om.entity.OmModel;
import org.jeecg.modules.demo.om.entity.OmModelData;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelEntity;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 模型
 * @Author: jeecg-boot
 * @Date:   2023-08-31
 * @Version: V1.0
 */
@Data
@ApiModel(value="om_modelPage对象", description="模型")
public class OmModelPage {

	/**主键*/
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
	@Excel(name = "父模型", width = 15, dictTable = "om_model where model_train_status = 3 ", dicText = "model_name", dicCode = "id")
    @Dict(dictTable = "om_model where model_train_status = 3 ", dicText = "model_name", dicCode = "id")
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

	@ExcelCollection(name="模型训练数据")
	@ApiModelProperty(value = "模型训练数据")
	private List<OmModelData> omModelDataList;

}
