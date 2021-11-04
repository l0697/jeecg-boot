package org.jeecg.modules.demo.exTableNormal.mapper;

import java.util.List;
import org.jeecg.modules.demo.exTableNormal.entity.PerformanceParamNormal;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 性能参数
 * @Author: jeecg-boot
 * @Date:   2021-08-21
 * @Version: V1.0
 */
public interface PerformanceParamNormalMapper extends BaseMapper<PerformanceParamNormal> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<PerformanceParamNormal> selectByMainId(@Param("mainId") String mainId);
}
