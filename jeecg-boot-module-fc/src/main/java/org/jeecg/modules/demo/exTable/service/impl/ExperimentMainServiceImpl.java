package org.jeecg.modules.demo.exTable.service.impl;

import org.jeecg.modules.demo.exTable.entity.ExperimentMain;
import org.jeecg.modules.demo.exTable.entity.OrgStrucParam;
import org.jeecg.modules.demo.exTable.entity.SyntheticProcess;
import org.jeecg.modules.demo.exTable.entity.PerformanceParam;
import org.jeecg.modules.demo.exTable.mapper.OrgStrucParamMapper;
import org.jeecg.modules.demo.exTable.mapper.SyntheticProcessMapper;
import org.jeecg.modules.demo.exTable.mapper.PerformanceParamMapper;
import org.jeecg.modules.demo.exTable.mapper.ExperimentMainMapper;
import org.jeecg.modules.demo.exTable.service.IExperimentMainService;
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
 * @Date:   2021-08-30
 * @Version: V1.0
 */
@Service
public class ExperimentMainServiceImpl extends ServiceImpl<ExperimentMainMapper, ExperimentMain> implements IExperimentMainService {

	@Autowired
	private ExperimentMainMapper experimentMainMapper;
	@Autowired
	private OrgStrucParamMapper orgStrucParamMapper;
	@Autowired
	private SyntheticProcessMapper syntheticProcessMapper;
	@Autowired
	private PerformanceParamMapper performanceParamMapper;
	
	@Override
	@Transactional
	public void saveMain(ExperimentMain experimentMain, List<OrgStrucParam> orgStrucParamList,List<SyntheticProcess> syntheticProcessList,List<PerformanceParam> performanceParamList) {
		experimentMainMapper.insert(experimentMain);
		if(orgStrucParamList!=null && orgStrucParamList.size()>0) {
			for(OrgStrucParam entity:orgStrucParamList) {
				//外键设置
				entity.setMainId(experimentMain.getId());
				orgStrucParamMapper.insert(entity);
			}
		}
		if(syntheticProcessList!=null && syntheticProcessList.size()>0) {
			for(SyntheticProcess entity:syntheticProcessList) {
				//外键设置
				entity.setMainId(experimentMain.getId());
				syntheticProcessMapper.insert(entity);
			}
		}
		if(performanceParamList!=null && performanceParamList.size()>0) {
			for(PerformanceParam entity:performanceParamList) {
				//外键设置
				entity.setMainId(experimentMain.getId());
				performanceParamMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void updateMain(ExperimentMain experimentMain,List<OrgStrucParam> orgStrucParamList,List<SyntheticProcess> syntheticProcessList,List<PerformanceParam> performanceParamList) {
		experimentMainMapper.updateById(experimentMain);
		
		//1.先删除子表数据
		orgStrucParamMapper.deleteByMainId(experimentMain.getId());
		syntheticProcessMapper.deleteByMainId(experimentMain.getId());
		performanceParamMapper.deleteByMainId(experimentMain.getId());
		
		//2.子表数据重新插入
		if(orgStrucParamList!=null && orgStrucParamList.size()>0) {
			for(OrgStrucParam entity:orgStrucParamList) {
				//外键设置
				entity.setMainId(experimentMain.getId());
				orgStrucParamMapper.insert(entity);
			}
		}
		if(syntheticProcessList!=null && syntheticProcessList.size()>0) {
			for(SyntheticProcess entity:syntheticProcessList) {
				//外键设置
				entity.setMainId(experimentMain.getId());
				syntheticProcessMapper.insert(entity);
			}
		}
		if(performanceParamList!=null && performanceParamList.size()>0) {
			for(PerformanceParam entity:performanceParamList) {
				//外键设置
				entity.setMainId(experimentMain.getId());
				performanceParamMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		orgStrucParamMapper.deleteByMainId(id);
		syntheticProcessMapper.deleteByMainId(id);
		performanceParamMapper.deleteByMainId(id);
		experimentMainMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			orgStrucParamMapper.deleteByMainId(id.toString());
			syntheticProcessMapper.deleteByMainId(id.toString());
			performanceParamMapper.deleteByMainId(id.toString());
			experimentMainMapper.deleteById(id);
		}
	}
	
}
