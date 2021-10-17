package org.jeecg.modules.demo.exTableERP.mapper;

import java.util.List;
import org.jeecg.modules.demo.exTableERP.entity.OrgStrucParamERP;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 组织结构参数
 * @Author: jeecg-boot
 * @Date:   2021-08-21
 * @Version: V1.0
 */
public interface OrgStrucParamERPMapper extends BaseMapper<OrgStrucParamERP> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<OrgStrucParamERP> selectByMainId(@Param("mainId") String mainId);

}
