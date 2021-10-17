package org.jeecg.modules.demo.exTableERP.service.impl;

import org.jeecg.modules.demo.exTableERP.entity.OrgStrucParamERP;
import org.jeecg.modules.demo.exTableERP.mapper.OrgStrucParamERPMapper;
import org.jeecg.modules.demo.exTableERP.service.IOrgStrucParamERPService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 组织结构参数
 * @Author: jeecg-boot
 * @Date:   2021-08-21
 * @Version: V1.0
 */
@Service
public class OrgStrucParamERPServiceImpl extends ServiceImpl<OrgStrucParamERPMapper, OrgStrucParamERP> implements IOrgStrucParamERPService {
	
	@Autowired
	private OrgStrucParamERPMapper orgStrucParamERPMapper;
	
	@Override
	public List<OrgStrucParamERP> selectByMainId(String mainId) {
		return orgStrucParamERPMapper.selectByMainId(mainId);
	}
}
