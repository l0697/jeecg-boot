package org.jeecg.modules.demo.exTable.service;

import org.jeecg.modules.demo.exTable.entity.OrgStrucParam;
import org.jeecg.modules.demo.exTable.entity.SyntheticProcess;
import org.jeecg.modules.demo.exTable.entity.PerformanceParam;
import org.jeecg.modules.demo.exTable.entity.ExperimentMain;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 实验数据主表
 * @Author: jeecg-boot
 * @Date:   2021-08-30
 * @Version: V1.0
 */
public interface IExperimentMainService extends IService<ExperimentMain> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(ExperimentMain experimentMain,List<OrgStrucParam> orgStrucParamList,List<SyntheticProcess> syntheticProcessList,List<PerformanceParam> performanceParamList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(ExperimentMain experimentMain,List<OrgStrucParam> orgStrucParamList,List<SyntheticProcess> syntheticProcessList,List<PerformanceParam> performanceParamList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
