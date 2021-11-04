package org.jeecg.modules.demo.exTableNormal.service;

import org.jeecg.modules.demo.exTableNormal.entity.OrgStrucParamNormal;
import org.jeecg.modules.demo.exTableNormal.entity.SyntheticProcessNormal;
import org.jeecg.modules.demo.exTableNormal.entity.PerformanceParamNormal;
import org.jeecg.modules.demo.exTableNormal.entity.ExperimentMainNormal;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 实验数据主表
 * @Author: jeecg-boot
 * @Date:   2021-08-21
 * @Version: V1.0
 */
public interface IExperimentMainNormalService extends IService<ExperimentMainNormal> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(ExperimentMainNormal experimentMainNormal,List<OrgStrucParamNormal> orgStrucParamNormalList,List<SyntheticProcessNormal> syntheticProcessNormalList,List<PerformanceParamNormal> performanceParamNormalList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(ExperimentMainNormal experimentMainNormal,List<OrgStrucParamNormal> orgStrucParamNormalList,List<SyntheticProcessNormal> syntheticProcessNormalList,List<PerformanceParamNormal> performanceParamNormalList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
