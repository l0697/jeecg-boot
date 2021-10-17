package org.jeecg.modules.demo.exTableNormal.mapper;

import java.util.List;
import org.jeecg.modules.demo.exTableNormal.entity.SyntheticProcessNormal;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 合成工艺
 * @Author: jeecg-boot
 * @Date:   2021-08-21
 * @Version: V1.0
 */
public interface SyntheticProcessNormalMapper extends BaseMapper<SyntheticProcessNormal> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<SyntheticProcessNormal> selectByMainId(@Param("mainId") String mainId);
}
