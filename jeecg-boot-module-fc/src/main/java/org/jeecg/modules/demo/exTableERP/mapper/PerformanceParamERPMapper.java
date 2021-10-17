package org.jeecg.modules.demo.exTableERP.mapper;

import java.util.List;
import org.jeecg.modules.demo.exTableERP.entity.PerformanceParamERP;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 性能参数
 * @Author: jeecg-boot
 * @Date:   2021-08-21
 * @Version: V1.0
 */
public interface PerformanceParamERPMapper extends BaseMapper<PerformanceParamERP> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<PerformanceParamERP> selectByMainId(@Param("mainId") String mainId);

}
