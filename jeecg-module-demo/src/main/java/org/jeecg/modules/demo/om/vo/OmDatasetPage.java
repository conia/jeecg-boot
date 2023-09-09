package org.jeecg.modules.demo.om.vo;

import java.util.List;
import org.jeecg.modules.demo.om.entity.OmDataset;
import org.jeecg.modules.demo.om.entity.OmFile;
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
 * @Description: 数据集
 * @Author: jeecg-boot
 * @Date:   2023-08-31
 * @Version: V1.0
 */
@Data
@ApiModel(value="om_datasetPage对象", description="数据集")
public class OmDatasetPage {

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
	/**数据集名称*/
	@Excel(name = "数据集名称", width = 15)
	@ApiModelProperty(value = "数据集名称")
    private java.lang.String name;
	/**数据集格式*/
	@Excel(name = "数据集格式", width = 15, dicCode = "dataset_format")
    @Dict(dicCode = "dataset_format")
	@ApiModelProperty(value = "数据集格式")
    private java.lang.String dsFormat;
	/**描述*/
	@Excel(name = "描述", width = 15)
	@ApiModelProperty(value = "描述")
    private java.lang.String description;
	/**是否删除*/
	@Excel(name = "是否删除", width = 15)
	@ApiModelProperty(value = "是否删除")
    private java.lang.String delFlag;

	@ExcelCollection(name="数据集内数据文件")
	@ApiModelProperty(value = "数据集内数据文件")
	private List<OmFile> omFileList;

}
