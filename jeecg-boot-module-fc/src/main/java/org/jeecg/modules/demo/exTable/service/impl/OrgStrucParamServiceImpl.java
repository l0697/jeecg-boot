package org.jeecg.modules.demo.exTable.service.impl;

import org.jeecg.modules.demo.exTable.entity.OrgStrucParam;
import org.jeecg.modules.demo.exTable.mapper.OrgStrucParamMapper;
import org.jeecg.modules.demo.exTable.service.IOrgStrucParamService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 组织结构参数
 * @Author: jeecg-boot
 * @Date:   2021-08-30
 * @Version: V1.0
 */
@Service
public class OrgStrucParamServiceImpl extends ServiceImpl<OrgStrucParamMapper, OrgStrucParam> implements IOrgStrucParamService {
	
	@Autowired
	private OrgStrucParamMapper orgStrucParamMapper;
	
	@Override
	public List<OrgStrucParam> selectByMainId(String mainId) {
		return orgStrucParamMapper.selectByMainId(mainId);
	}
}
