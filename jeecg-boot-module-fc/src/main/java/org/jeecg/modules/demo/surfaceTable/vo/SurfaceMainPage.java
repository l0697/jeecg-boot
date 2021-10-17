package org.jeecg.modules.demo.surfaceTable.vo;

import java.util.List;
import org.jeecg.modules.demo.surfaceTable.entity.SurfaceMain;
import org.jeecg.modules.demo.surfaceTable.entity.SurfaceSon;
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
 * @Description: 金属界面数据主表
 * @Author: jeecg-boot
 * @Date:   2021-08-26
 * @Version: V1.0
 */
@Data
@ApiModel(value="surface_mainPage对象", description="金属界面数据主表")
public class SurfaceMainPage {

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
	/**所属部门*/
	@ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;
	/**atom_name*/
	@Excel(name = "atom_name", width = 15)
	@ApiModelProperty(value = "atom_name")
    private java.lang.String atomName;
	/**polymorph*/
	@Excel(name = "polymorph", width = 15)
	@ApiModelProperty(value = "polymorph")
    private java.lang.Integer polymorph;
	/**weighted_surface_energy_epa*/
	@Excel(name = "weighted_surface_energy_epa", width = 15)
	@ApiModelProperty(value = "weighted_surface_energy_epa")
    private java.math.BigDecimal weightedSurfaceEnergyEpa;
	/**pretty_formula*/
	@Excel(name = "pretty_formula", width = 15)
	@ApiModelProperty(value = "pretty_formula")
    private java.lang.String prettyFormula;
	/**material_id*/
	@Excel(name = "material_id", width = 15)
	@ApiModelProperty(value = "material_id")
    private java.lang.String materialId;
	/**weighted_surface_energy*/
	@Excel(name = "weighted_surface_energy", width = 15)
	@ApiModelProperty(value = "weighted_surface_energy")
    private java.math.BigDecimal weightedSurfaceEnergy;
	/**spacegroup_number*/
	@Excel(name = "spacegroup_number", width = 15)
	@ApiModelProperty(value = "spacegroup_number")
    private java.lang.Integer spacegroupNumber;
	/**eabove_hull*/
	@Excel(name = "eabove_hull", width = 15)
	@ApiModelProperty(value = "eabove_hull")
    private java.math.BigDecimal eaboveHull;
	/**surface_anisotropy*/
	@Excel(name = "surface_anisotropy", width = 15)
	@ApiModelProperty(value = "surface_anisotropy")
    private java.math.BigDecimal surfaceAnisotropy;
	/**shape_factor*/
	@Excel(name = "shape_factor", width = 15)
	@ApiModelProperty(value = "shape_factor")
    private java.math.BigDecimal shapeFactor;
	/**weighted_work_function*/
	@Excel(name = "weighted_work_function", width = 15)
	@ApiModelProperty(value = "weighted_work_function")
    private java.math.BigDecimal weightedWorkFunction;
	/**miller_units*/
	@Excel(name = "miller_units", width = 15)
	@ApiModelProperty(value = "miller_units")
    private java.lang.String millerUnits;
	/**has_reconstructed*/
	@Excel(name = "has_reconstructed", width = 15)
	@ApiModelProperty(value = "has_reconstructed")
    private java.lang.Integer hasReconstructed;
	/**file*/
	@Excel(name = "file", width = 15)
	@ApiModelProperty(value = "file")
    private java.lang.String file;

	@ExcelCollection(name="金属界面数据附表")
	@ApiModelProperty(value = "金属界面数据附表")
	private List<SurfaceSon> surfaceSonList;

}
