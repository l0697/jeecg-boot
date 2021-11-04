package org.jeecg.modules.demo.surfaceTable.mapper;

import java.util.List;
import org.jeecg.modules.demo.surfaceTable.entity.SurfaceSon;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 金属界面数据附表
 * @Author: jeecg-boot
 * @Date:   2021-08-26
 * @Version: V1.0
 */
public interface SurfaceSonMapper extends BaseMapper<SurfaceSon> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<SurfaceSon> selectByMainId(@Param("mainId") String mainId);
}
