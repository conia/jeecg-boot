package org.jeecg.modules.demo.conia.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
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
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 测试请假表
 * @Author: jeecg-boot
 * @Date:   2023-08-29
 * @Version: V1.0
 */
@Data
@TableName("ceshi_note")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ceshi_note对象", description="测试请假表")
public class CeshiNote implements Serializable {
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
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;
	/**用户名*/
	@Excel(name = "用户名", width = 15)
    @ApiModelProperty(value = "用户名")
    private java.lang.String name;
	/**性别*/
	@Excel(name = "性别", width = 15, dicCode = "sex")
	@Dict(dicCode = "sex")
    @ApiModelProperty(value = "性别")
    private java.lang.String sex;
	/**年龄*/
	@Excel(name = "年龄", width = 15)
    @ApiModelProperty(value = "年龄")
    private java.lang.Integer age;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private java.lang.String remark;
	/**生日*/
	@Excel(name = "生日", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "生日")
    private java.util.Date birthday;
	/**下单时间*/
	@Excel(name = "下单时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "下单时间")
    private java.util.Date xiaTime;
	/**薪资*/
	@Excel(name = "薪资", width = 15)
    @ApiModelProperty(value = "薪资")
    private java.lang.Double salary;
	/**大文本*/
	@Excel(name = "大文本", width = 15)
    @ApiModelProperty(value = "大文本")
    private java.lang.String logTxt;
	/**弹出报表*/
	@Excel(name = "弹出报表", width = 15)
    @ApiModelProperty(value = "弹出报表")
    private java.lang.String popCc;
	/**用户*/
	@Excel(name = "用户", width = 15, dictTable = "sys_user", dicText = "realname", dicCode = "username")
	@Dict(dictTable = "sys_user", dicText = "realname", dicCode = "username")
    @ApiModelProperty(value = "用户")
    private java.lang.String userId;
	/**部门*/
	@Excel(name = "部门", width = 15, dictTable = "sys_depart", dicText = "depart_name", dicCode = "org_code")
	@Dict(dictTable = "sys_depart", dicText = "depart_name", dicCode = "org_code")
    @ApiModelProperty(value = "部门")
    private java.lang.String depId;
	/**开关*/
	@Excel(name = "开关", width = 15)
    @ApiModelProperty(value = "开关")
    private java.lang.String kaiGuan;
	/**文件*/
	@Excel(name = "文件", width = 15)
    @ApiModelProperty(value = "文件")
    private java.lang.String file2;
	/**图片*/
	@Excel(name = "图片", width = 15)
    @ApiModelProperty(value = "图片")
    private java.lang.String pic;
	/**省市区*/
	@Excel(name = "省市区", width = 15)
    @ApiModelProperty(value = "省市区")
    private java.lang.String shengshiqu;
	/**富文本*/
	@Excel(name = "富文本", width = 15)
    @ApiModelProperty(value = "富文本")
    private java.lang.String accc;
	/**物料分类*/
	@Excel(name = "物料分类", width = 15)
    @ApiModelProperty(value = "物料分类")
    private java.lang.String daaType;
	/**单选框*/
	@Excel(name = "单选框", width = 15, dicCode = "sex")
	@Dict(dicCode = "sex")
    @ApiModelProperty(value = "单选框")
    private java.lang.String ccc;
	/**aa*/
	@Excel(name = "aa", width = 15)
    @ApiModelProperty(value = "aa")
    private java.lang.String aaa;
}
