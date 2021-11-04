package org.jeecg.modules.demo.exTable.mapper;

import java.util.List;
import org.jeecg.modules.demo.exTable.entity.SyntheticProcess;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 合成工艺
 * @Author: jeecg-boot
 * @Date:   2021-08-30
 * @Version: V1.0
 */
public interface SyntheticProcessMapper extends BaseMapper<SyntheticProcess> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<SyntheticProcess> selectByMainId(@Param("mainId") String mainId);
}
