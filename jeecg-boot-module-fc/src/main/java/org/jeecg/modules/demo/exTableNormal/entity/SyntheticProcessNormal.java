package org.jeecg.modules.demo.exTableNormal.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.UnsupportedEncodingException;

/**
 * @Description: 合成工艺
 * @Author: jeecg-boot
 * @Date:   2021-08-21
 * @Version: V1.0
 */
@ApiModel(value="synthetic_process对象", description="合成工艺")
@Data
@TableName("synthetic_process")
public class SyntheticProcessNormal implements Serializable {
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
	/**反应溶剂及其体积*/
	@Excel(name = "反应溶剂及其体积", width = 15)
    @ApiModelProperty(value = "反应溶剂及其体积")
    private java.lang.String reactantVolume;
	/**还原剂及其浓度*/
	@Excel(name = "还原剂及其浓度", width = 15)
    @ApiModelProperty(value = "还原剂及其浓度")
    private java.lang.String redagentCon;
	/**前驱体及其浓度*/
	@Excel(name = "前驱体及其浓度", width = 15)
    @ApiModelProperty(value = "前驱体及其浓度")
    private java.lang.String precursorCon;
	/**保护剂及其浓度*/
	@Excel(name = "保护剂及其浓度", width = 15)
    @ApiModelProperty(value = "保护剂及其浓度")
    private java.lang.String proagentCon;
	/**助剂及其浓度*/
	@Excel(name = "助剂及其浓度", width = 15)
    @ApiModelProperty(value = "助剂及其浓度")
    private java.lang.String addContra;
	/**反应温度（℃）*/
	@Excel(name = "反应温度（℃）", width = 15)
    @ApiModelProperty(value = "反应温度（℃）")
    private java.lang.Double reflexTem;
	/**反应时间（分钟）*/
	@Excel(name = "反应时间（分钟）", width = 15)
    @ApiModelProperty(value = "反应时间（分钟）")
    private java.lang.Integer reflexTime;
	/**搅拌条件（转/分钟）*/
	@Excel(name = "搅拌条件（转/分钟）", width = 15)
    @ApiModelProperty(value = "搅拌条件（转/分钟）")
    private java.lang.Integer stirMethod;
	/**反应溶液滴加速度（升/分钟）*/
	@Excel(name = "反应溶液滴加速度（升/分钟）", width = 15)
    @ApiModelProperty(value = "反应溶液滴加速度（升/分钟）")
    private java.lang.Double reactantAddSpeed;
	/**反应溶液 pH 值*/
	@Excel(name = "反应溶液 pH 值", width = 15)
    @ApiModelProperty(value = "反应溶液 pH 值")
    private java.lang.Double reactantPh;
	/**实验号*/
    @ApiModelProperty(value = "实验号")
    private java.lang.String mainId;
}
