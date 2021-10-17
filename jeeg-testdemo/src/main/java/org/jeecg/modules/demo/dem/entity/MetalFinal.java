package org.jeecg.modules.demo.dem.entity;

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
 * @Description: 真实数据
 * @Author: jeecg-boot
 * @Date:   2021-10-16
 * @Version: V1.0
 */
@Data
@TableName("metal_final")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="metal_final对象", description="真实数据")
public class MetalFinal implements Serializable {
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
	/**空间群组*/
	@Excel(name = "空间群组", width = 15)
    @ApiModelProperty(value = "空间群组")
    private java.lang.Integer spacegroup;
	/**弥勒指数*/
	@Excel(name = "弥勒指数", width = 15)
    @ApiModelProperty(value = "弥勒指数")
    private java.lang.String millerIndex;
	/**晶系*/
	@Excel(name = "晶系", width = 15)
    @ApiModelProperty(value = "晶系")
    private java.lang.Integer valenceElectrons;
	/**原子半径*/
	@Excel(name = "原子半径", width = 15)
    @ApiModelProperty(value = "原子半径")
    private java.lang.Double atomicRadius;
	/**表面能*/
	@Excel(name = "表面能", width = 15)
    @ApiModelProperty(value = "表面能")
    private java.lang.Double surfaceEnergy;
	/**功函数*/
	@Excel(name = "功函数", width = 15)
    @ApiModelProperty(value = "功函数")
    private java.lang.Double workFunction;
}
