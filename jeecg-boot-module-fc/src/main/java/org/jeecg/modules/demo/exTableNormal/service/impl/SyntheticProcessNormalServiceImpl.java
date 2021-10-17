package org.jeecg.modules.demo.exTableNormal.service.impl;

import org.jeecg.modules.demo.exTableNormal.entity.SyntheticProcessNormal;
import org.jeecg.modules.demo.exTableNormal.mapper.SyntheticProcessNormalMapper;
import org.jeecg.modules.demo.exTableNormal.service.ISyntheticProcessNormalService;
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
public class SyntheticProcessNormalServiceImpl extends ServiceImpl<SyntheticProcessNormalMapper, SyntheticProcessNormal> implements ISyntheticProcessNormalService {
	
	@Autowired
	private SyntheticProcessNormalMapper syntheticProcessNormalMapper;
	
	@Override
	public List<SyntheticProcessNormal> selectByMainId(String mainId) {
		return syntheticProcessNormalMapper.selectByMainId(mainId);
	}
}
