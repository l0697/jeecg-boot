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
 * @Description: 性能参数
 * @Author: jeecg-boot
 * @Date:   2021-08-30
 * @Version: V1.0
 */
@ApiModel(value="performance_param对象", description="性能参数")
@Data
@TableName("performance_param")
public class PerformanceParam implements Serializable {
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
	/**松装密度（g/mL）*/
	@Excel(name = "松装密度（g/mL）", width = 15)
    @ApiModelProperty(value = "松装密度（g/mL）")
    private Double bulkDensity;
	/**振实密度（g/mL）*/
	@Excel(name = "振实密度（g/mL）", width = 15)
    @ApiModelProperty(value = "振实密度（g/mL）")
    private Double tapDensity;
	/**比表面积(m2/g)*/
	@Excel(name = "比表面积(m2/g)", width = 15)
    @ApiModelProperty(value = "比表面积(m2/g)")
    private Double specSurfaceArea;
	/**烧损（110℃）(%)*/
	@Excel(name = "烧损（110℃）(%)", width = 15)
    @ApiModelProperty(value = "烧损（110℃）(%)")
    private Double burnLoss110;
	/**烧损（538℃）(%)*/
	@Excel(name = "烧损（538℃）(%)", width = 15)
    @ApiModelProperty(value = "烧损（538℃）(%)")
    private Double burnLoss538;
	/**纯度（%）*/
	@Excel(name = "纯度（%）", width = 15)
    @ApiModelProperty(value = "纯度（%）")
    private Double purity;
	/**实验号*/
    @ApiModelProperty(value = "实验号")
    private String mainId;
}
