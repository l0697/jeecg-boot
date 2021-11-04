package org.jeecg.modules.demo.exTableERP.service.impl;

import org.jeecg.modules.demo.exTableERP.entity.PerformanceParamERP;
import org.jeecg.modules.demo.exTableERP.mapper.PerformanceParamERPMapper;
import org.jeecg.modules.demo.exTableERP.service.IPerformanceParamERPService;
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
public class PerformanceParamERPServiceImpl extends ServiceImpl<PerformanceParamERPMapper, PerformanceParamERP> implements IPerformanceParamERPService {
	
	@Autowired
	private PerformanceParamERPMapper performanceParamERPMapper;
	
	@Override
	public List<PerformanceParamERP> selectByMainId(String mainId) {
		return performanceParamERPMapper.selectByMainId(mainId);
	}
}
