package org.jeecg.modules.demo.exTable.mapper;

import java.util.List;
import org.jeecg.modules.demo.exTable.entity.OrgStrucParam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 组织结构参数
 * @Author: jeecg-boot
 * @Date:   2021-08-30
 * @Version: V1.0
 */
public interface OrgStrucParamMapper extends BaseMapper<OrgStrucParam> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<OrgStrucParam> selectByMainId(@Param("mainId") String mainId);
}
