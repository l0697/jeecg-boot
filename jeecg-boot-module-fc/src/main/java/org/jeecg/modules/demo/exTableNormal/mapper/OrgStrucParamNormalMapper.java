package org.jeecg.modules.demo.exTableNormal.mapper;

import java.util.List;
import org.jeecg.modules.demo.exTableNormal.entity.OrgStrucParamNormal;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 组织结构参数
 * @Author: jeecg-boot
 * @Date:   2021-08-21
 * @Version: V1.0
 */
public interface OrgStrucParamNormalMapper extends BaseMapper<OrgStrucParamNormal> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<OrgStrucParamNormal> selectByMainId(@Param("mainId") String mainId);
}
