package org.jeecg.modules.demo.exTableERP.service.impl;

import org.jeecg.modules.demo.exTableERP.entity.SyntheticProcessERP;
import org.jeecg.modules.demo.exTableERP.mapper.SyntheticProcessERPMapper;
import org.jeecg.modules.demo.exTableERP.service.ISyntheticProcessERPService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 合成工艺
 * @Author: jeecg-boot
 * @Date:   2021-08-21
 * @Version: V1.0
 */
@Service
public class SyntheticProcessERPServiceImpl extends ServiceImpl<SyntheticProcessERPMapper, SyntheticProcessERP> implements ISyntheticProcessERPService {
	
	@Autowired
	private SyntheticProcessERPMapper syntheticProcessERPMapper;
	
	@Override
	public List<SyntheticProcessERP> selectByMainId(String mainId) {
		return syntheticProcessERPMapper.selectByMainId(mainId);
	}
}
