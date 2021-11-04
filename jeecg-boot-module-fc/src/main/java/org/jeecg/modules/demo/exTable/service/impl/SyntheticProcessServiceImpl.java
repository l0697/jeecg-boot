package org.jeecg.modules.demo.exTable.service.impl;

import org.jeecg.modules.demo.exTable.entity.SyntheticProcess;
import org.jeecg.modules.demo.exTable.mapper.SyntheticProcessMapper;
import org.jeecg.modules.demo.exTable.service.ISyntheticProcessService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 合成工艺
 * @Author: jeecg-boot
 * @Date:   2021-08-30
 * @Version: V1.0
 */
@Service
public class SyntheticProcessServiceImpl extends ServiceImpl<SyntheticProcessMapper, SyntheticProcess> implements ISyntheticProcessService {
	
	@Autowired
	private SyntheticProcessMapper syntheticProcessMapper;
	
	@Override
	public List<SyntheticProcess> selectByMainId(String mainId) {
		return syntheticProcessMapper.selectByMainId(mainId);
	}
}
