package org.jeecg.modules.demo.exTable.service.impl;

import org.jeecg.modules.demo.exTable.entity.PerformanceParam;
import org.jeecg.modules.demo.exTable.mapper.PerformanceParamMapper;
import org.jeecg.modules.demo.exTable.service.IPerformanceParamService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 性能参数
 * @Author: jeecg-boot
 * @Date:   2021-08-30
 * @Version: V1.0
 */
@Service
public class PerformanceParamServiceImpl extends ServiceImpl<PerformanceParamMapper, PerformanceParam> implements IPerformanceParamService {
	
	@Autowired
	private PerformanceParamMapper performanceParamMapper;
	
	@Override
	public List<PerformanceParam> selectByMainId(String mainId) {
		return performanceParamMapper.selectByMainId(mainId);
	}
}
