package org.jeecg.modules.demo.ospTable.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @Description: 组织结构参数
 * @Author: jeecg-boot
 * @Date: 2021-08-19
 * @Version: V1.0
 */
@Data
@TableName("organizational_structure_parameters")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "organizational_structure_parameters对象", description = "组织结构参数")
public class OrganizationalStructureParameters implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
    /**
     * 创建人
     */
    @Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
    /**
     * 创建日期
     */
    @Excel(name = "创建日期", width = 15,exportFormat="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
    /**
     * 更新人
     */
    @Excel(name = "更新人", width = 15)
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
    /**
     * 更新日期
     */
    @Excel(name = "更新日期", width = 15)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
    /**
     * 所属部门
     */
    @Excel(name = "所属部门", width = 15)
    @ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;
    /**
     * 形貌
     */
    @Excel(name = "形貌", width = 15, dicCode = "appearance")
    @Dict(dicCode = "appearance")
    @ApiModelProperty(value = "形貌")
    private java.lang.String appearance;
    /**
     * 粒度分布
     */
    @Excel(name = "粒度分布", width = 15)
    @ApiModelProperty(value = "粒度分布")
    private java.lang.String particleSizeDistribution;
    /**
     * 晶型
     */
    @Excel(name = "晶型", width = 15)
    @ApiModelProperty(value = "晶型")
    private java.lang.String crystalForm;
}
