package org.jeecg.modules.demo.exTableERP.service;

import org.jeecg.modules.demo.exTableERP.entity.OrgStrucParamERP;
import org.jeecg.modules.demo.exTableERP.entity.SyntheticProcessERP;
import org.jeecg.modules.demo.exTableERP.entity.PerformanceParamERP;
import org.jeecg.modules.demo.exTableERP.entity.ExperimentMainERP;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 实验数据主表
 * @Author: jeecg-boot
 * @Date:   2021-08-21
 * @Version: V1.0
 */
public interface IExperimentMainERPService extends IService<ExperimentMainERP> {

	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);


}
