package org.jeecg.modules.demo.exTable.entity;

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

/**
 * @Description: 合成工艺
 * @Author: jeecg-boot
 * @Date:   2021-08-30
 * @Version: V1.0
 */
@ApiModel(value="synthetic_process对象", description="合成工艺")
@Data
@TableName("synthetic_process")
public class SyntheticProcess implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private Date updateTime;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private String sysOrgCode;
	/**反应溶剂及其体积*/
	@Excel(name = "反应溶剂及其体积", width = 15)
    @ApiModelProperty(value = "反应溶剂及其体积")
    private String reactantVolume;
	/**还原剂及其浓度*/
	@Excel(name = "还原剂及其浓度", width = 15)
    @ApiModelProperty(value = "还原剂及其浓度")
    private String redagentCon;
	/**前驱体及其浓度*/
	@Excel(name = "前驱体及其浓度", width = 15)
    @ApiModelProperty(value = "前驱体及其浓度")
    private String precursorCon;
	/**保护剂及其浓度*/
	@Excel(name = "保护剂及其浓度", width = 15)
    @ApiModelProperty(value = "保护剂及其浓度")
    private String proagentCon;
	/**助剂及其浓度*/
	@Excel(name = "助剂及其浓度", width = 15)
    @ApiModelProperty(value = "助剂及其浓度")
    private String addContra;
	/**反应温度（℃）*/
	@Excel(name = "反应温度（℃）", width = 15)
    @ApiModelProperty(value = "反应温度（℃）")
    private Double reflexTem;
	/**反应时间（分钟）*/
	@Excel(name = "反应时间（分钟）", width = 15)
    @ApiModelProperty(value = "反应时间（分钟）")
    private Integer reflexTime;
	/**搅拌条件（转/分钟）*/
	@Excel(name = "搅拌条件（转/分钟）", width = 15)
    @ApiModelProperty(value = "搅拌条件（转/分钟）")
    private Integer stirMethod;
	/**反应溶液滴加速度（升/分钟）*/
	@Excel(name = "反应溶液滴加速度（升/分钟）", width = 15)
    @ApiModelProperty(value = "反应溶液滴加速度（升/分钟）")
    private Double reactantAddSpeed;
	/**反应溶液 pH 值*/
	@Excel(name = "反应溶液 pH 值", width = 15)
    @ApiModelProperty(value = "反应溶液 pH 值")
    private Double reactantPh;
	/**实验号*/
    @ApiModelProperty(value = "实验号")
    private String mainId;
}
