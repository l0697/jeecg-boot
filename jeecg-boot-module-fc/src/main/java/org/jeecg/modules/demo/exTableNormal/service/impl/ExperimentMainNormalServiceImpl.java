package org.jeecg.modules.demo.exTableNormal.service.impl;

import org.jeecg.modules.demo.exTableNormal.entity.ExperimentMainNormal;
import org.jeecg.modules.demo.exTableNormal.entity.OrgStrucParamNormal;
import org.jeecg.modules.demo.exTableNormal.entity.SyntheticProcessNormal;
import org.jeecg.modules.demo.exTableNormal.entity.PerformanceParamNormal;
import org.jeecg.modules.demo.exTableNormal.mapper.OrgStrucParamNormalMapper;
import org.jeecg.modules.demo.exTableNormal.mapper.SyntheticProcessNormalMapper;
import org.jeecg.modules.demo.exTableNormal.mapper.PerformanceParamNormalMapper;
import org.jeecg.modules.demo.exTableNormal.mapper.ExperimentMainNormalMapper;
import org.jeecg.modules.demo.exTableNormal.service.IExperimentMainNormalService;
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
public class ExperimentMainNormalServiceImpl extends ServiceImpl<ExperimentMainNormalMapper, ExperimentMainNormal> implements IExperimentMainNormalService {

	@Autowired
	private ExperimentMainNormalMapper experimentMainNormalMapper;
	@Autowired
	private OrgStrucParamNormalMapper orgStrucParamNormalMapper;
	@Autowired
	private SyntheticProcessNormalMapper syntheticProcessNormalMapper;
	@Autowired
	private PerformanceParamNormalMapper performanceParamNormalMapper;
	
	@Override
	@Transactional
	public void saveMain(ExperimentMainNormal experimentMainNormal, List<OrgStrucParamNormal> orgStrucParamNormalList,List<SyntheticProcessNormal> syntheticProcessNormalList,List<PerformanceParamNormal> performanceParamNormalList) {
		experimentMainNormalMapper.insert(experimentMainNormal);
		if(orgStrucParamNormalList!=null && orgStrucParamNormalList.size()>0) {
			for(OrgStrucParamNormal entity:orgStrucParamNormalList) {
				//外键设置
				entity.setMainId(experimentMainNormal.getId());
				orgStrucParamNormalMapper.insert(entity);
			}
		}
		if(syntheticProcessNormalList!=null && syntheticProcessNormalList.size()>0) {
			for(SyntheticProcessNormal entity:syntheticProcessNormalList) {
				//外键设置
				entity.setMainId(experimentMainNormal.getId());
				syntheticProcessNormalMapper.insert(entity);
			}
		}
		if(performanceParamNormalList!=null && performanceParamNormalList.size()>0) {
			for(PerformanceParamNormal entity:performanceParamNormalList) {
				//外键设置
				entity.setMainId(experimentMainNormal.getId());
				performanceParamNormalMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void updateMain(ExperimentMainNormal experimentMainNormal,List<OrgStrucParamNormal> orgStrucParamNormalList,List<SyntheticProcessNormal> syntheticProcessNormalList,List<PerformanceParamNormal> performanceParamNormalList) {
		experimentMainNormalMapper.updateById(experimentMainNormal);
		
		//1.先删除子表数据
		orgStrucParamNormalMapper.deleteByMainId(experimentMainNormal.getId());
		syntheticProcessNormalMapper.deleteByMainId(experimentMainNormal.getId());
		performanceParamNormalMapper.deleteByMainId(experimentMainNormal.getId());
		
		//2.子表数据重新插入
		if(orgStrucParamNormalList!=null && orgStrucParamNormalList.size()>0) {
			for(OrgStrucParamNormal entity:orgStrucParamNormalList) {
				//外键设置
				entity.setMainId(experimentMainNormal.getId());
				orgStrucParamNormalMapper.insert(entity);
			}
		}
		if(syntheticProcessNormalList!=null && syntheticProcessNormalList.size()>0) {
			for(SyntheticProcessNormal entity:syntheticProcessNormalList) {
				//外键设置
				entity.setMainId(experimentMainNormal.getId());
				syntheticProcessNormalMapper.insert(entity);
			}
		}
		if(performanceParamNormalList!=null && performanceParamNormalList.size()>0) {
			for(PerformanceParamNormal entity:performanceParamNormalList) {
				//外键设置
				entity.setMainId(experimentMainNormal.getId());
				performanceParamNormalMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		orgStrucParamNormalMapper.deleteByMainId(id);
		syntheticProcessNormalMapper.deleteByMainId(id);
		performanceParamNormalMapper.deleteByMainId(id);
		experimentMainNormalMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			orgStrucParamNormalMapper.deleteByMainId(id.toString());
			syntheticProcessNormalMapper.deleteByMainId(id.toString());
			performanceParamNormalMapper.deleteByMainId(id.toString());
			experimentMainNormalMapper.deleteById(id);
		}
	}
	
}
