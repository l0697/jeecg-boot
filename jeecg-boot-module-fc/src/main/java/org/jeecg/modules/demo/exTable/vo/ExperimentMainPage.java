package org.jeecg.modules.demo.exTable.vo;

import java.util.List;

import org.jeecg.modules.demo.exTable.entity.OrgStrucParam;
import org.jeecg.modules.demo.exTable.entity.SyntheticProcess;
import org.jeecg.modules.demo.exTable.entity.PerformanceParam;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 实验数据主表
 * @Author: jeecg-boot
 * @Date:   2021-08-30
 * @Version: V1.0
 */
@Data
@ApiModel(value="experiment_mainPage对象", description="实验数据主表")
public class ExperimentMainPage {

	/**主键*/
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
	/**附件*/
	@Excel(name = "附件", width = 15)
	@ApiModelProperty(value = "附件")
    private String file;

	@ExcelCollection(name="组织结构参数")
	@ApiModelProperty(value = "组织结构参数")
	private List<OrgStrucParam> orgStrucParamList;
	@ExcelCollection(name="合成工艺")
	@ApiModelProperty(value = "合成工艺")
	private List<SyntheticProcess> syntheticProcessList;
	@ExcelCollection(name="性能参数")
	@ApiModelProperty(value = "性能参数")
	private List<PerformanceParam> performanceParamList;

}
