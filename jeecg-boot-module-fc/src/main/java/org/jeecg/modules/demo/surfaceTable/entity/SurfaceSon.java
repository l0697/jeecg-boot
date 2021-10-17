package org.jeecg.modules.demo.surfaceTable.entity;

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
 * @Description: 金属界面数据附表
 * @Author: jeecg-boot
 * @Date:   2021-08-26
 * @Version: V1.0
 */
@ApiModel(value="surface_son对象", description="金属界面数据附表")
@Data
@TableName("surface_son")
public class SurfaceSon implements Serializable {
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
	/**主表id*/
    @ApiModelProperty(value = "主表id")
    private java.lang.String mainId;
	/**surface_energy*/
	@Excel(name = "surface_energy", width = 15)
    @ApiModelProperty(value = "surface_energy")
    private java.math.BigDecimal surfaceEnergy;
	/**is_reconstructed*/
	@Excel(name = "is_reconstructed", width = 15)
    @ApiModelProperty(value = "is_reconstructed")
    private java.lang.Integer isReconstructed;
	/**structure*/
	@Excel(name = "structure", width = 15)
    @ApiModelProperty(value = "structure")
    private java.lang.String structure;
	/**initial_structure*/
	@Excel(name = "initial_structure", width = 15)
    @ApiModelProperty(value = "initial_structure")
    private java.lang.String initialStructure;
	/**work_function*/
	@Excel(name = "work_function", width = 15)
    @ApiModelProperty(value = "work_function")
    private java.math.BigDecimal workFunction;
	/**locpot_along_c*/
	@Excel(name = "locpot_along_c", width = 15)
    @ApiModelProperty(value = "locpot_along_c")
    private java.lang.String locpotAlongC;
	/**efermi*/
	@Excel(name = "efermi", width = 15)
    @ApiModelProperty(value = "efermi")
    private java.math.BigDecimal efermi;
	/**area_fraction*/
	@Excel(name = "area_fraction", width = 15)
    @ApiModelProperty(value = "area_fraction")
    private java.math.BigDecimal areaFraction;
	/**has_wulff*/
	@Excel(name = "has_wulff", width = 15)
    @ApiModelProperty(value = "has_wulff")
    private java.lang.Integer hasWulff;
}
