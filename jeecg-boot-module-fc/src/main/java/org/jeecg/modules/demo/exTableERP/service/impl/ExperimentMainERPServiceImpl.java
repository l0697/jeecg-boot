package org.jeecg.modules.demo.exTableERP.service.impl;

import org.jeecg.modules.demo.exTableERP.entity.ExperimentMainERP;
import org.jeecg.modules.demo.exTableERP.entity.OrgStrucParamERP;
import org.jeecg.modules.demo.exTableERP.entity.SyntheticProcessERP;
import org.jeecg.modules.demo.exTableERP.entity.PerformanceParamERP;
import org.jeecg.modules.demo.exTableERP.mapper.OrgStrucParamERPMapper;
import org.jeecg.modules.demo.exTableERP.mapper.SyntheticProcessERPMapper;
import org.jeecg.modules.demo.exTableERP.mapper.PerformanceParamERPMapper;
import org.jeecg.modules.demo.exTableERP.mapper.ExperimentMainERPMapper;
import org.jeecg.modules.demo.exTableERP.service.IExperimentMainERPService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 实验数据主表
 * @Author: jeecg-boot
 * @Date:   2021-08-21
 * @Version: V1.0
 */
@Service
public class ExperimentMainERPServiceImpl extends ServiceImpl<ExperimentMainERPMapper, ExperimentMainERP> implements IExperimentMainERPService {

	@Autowired
	private ExperimentMainERPMapper experimentMainERPMapper;
	@Autowired
	private OrgStrucParamERPMapper orgStrucParamERPMapper;
	@Autowired
	private SyntheticProcessERPMapper syntheticProcessERPMapper;
	@Autowired
	private PerformanceParamERPMapper performanceParamERPMapper;
	
	@Override
	@Transactional
	public void delMain(String id) {
		orgStrucParamERPMapper.deleteByMainId(id);
		syntheticProcessERPMapper.deleteByMainId(id);
		performanceParamERPMapper.deleteByMainId(id);
		experimentMainERPMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			orgStrucParamERPMapper.deleteByMainId(id.toString());
			syntheticProcessERPMapper.deleteByMainId(id.toString());
			performanceParamERPMapper.deleteByMainId(id.toString());
			experimentMainERPMapper.deleteById(id);
		}
	}
	
}
