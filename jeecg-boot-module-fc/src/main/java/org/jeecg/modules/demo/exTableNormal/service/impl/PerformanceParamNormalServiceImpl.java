package org.jeecg.modules.demo.exTableNormal.service.impl;

import org.jeecg.modules.demo.exTableNormal.entity.PerformanceParamNormal;
import org.jeecg.modules.demo.exTableNormal.mapper.PerformanceParamNormalMapper;
import org.jeecg.modules.demo.exTableNormal.service.IPerformanceParamNormalService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 性能参数
 * @Author: jeecg-boot
 * @Date:   2021-08-21
 * @Version: V1.0
 */
@Service
public class PerformanceParamNormalServiceImpl extends ServiceImpl<PerformanceParamNormalMapper, PerformanceParamNormal> implements IPerformanceParamNormalService {
	
	@Autowired
	private PerformanceParamNormalMapper performanceParamNormalMapper;
	
	@Override
	public List<PerformanceParamNormal> selectByMainId(String mainId) {
		return performanceParamNormalMapper.selectByMainId(mainId);
	}
}
