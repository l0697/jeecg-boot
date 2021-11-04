package org.jeecg.modules.demo.exTable.mapper;

import java.util.List;
import org.jeecg.modules.demo.exTable.entity.PerformanceParam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 性能参数
 * @Author: jeecg-boot
 * @Date:   2021-08-30
 * @Version: V1.0
 */
public interface PerformanceParamMapper extends BaseMapper<PerformanceParam> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<PerformanceParam> selectByMainId(@Param("mainId") String mainId);
}
