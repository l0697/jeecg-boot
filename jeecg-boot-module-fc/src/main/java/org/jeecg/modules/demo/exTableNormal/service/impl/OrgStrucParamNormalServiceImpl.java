package org.jeecg.modules.demo.exTableNormal.service.impl;

import org.jeecg.modules.demo.exTableNormal.entity.OrgStrucParamNormal;
import org.jeecg.modules.demo.exTableNormal.mapper.OrgStrucParamNormalMapper;
import org.jeecg.modules.demo.exTableNormal.service.IOrgStrucParamNormalService;
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
public class OrgStrucParamNormalServiceImpl extends ServiceImpl<OrgStrucParamNormalMapper, OrgStrucParamNormal> implements IOrgStrucParamNormalService {
	
	@Autowired
	private OrgStrucParamNormalMapper orgStrucParamNormalMapper;
	
	@Override
	public List<OrgStrucParamNormal> selectByMainId(String mainId) {
		return orgStrucParamNormalMapper.selectByMainId(mainId);
	}
}
