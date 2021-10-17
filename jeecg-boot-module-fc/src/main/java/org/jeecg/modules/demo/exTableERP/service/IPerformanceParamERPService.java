package org.jeecg.modules.demo.exTableERP.service;

import org.jeecg.modules.demo.exTableERP.entity.PerformanceParamERP;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 性能参数
 * @Author: jeecg-boot
 * @Date:   2021-08-21
 * @Version: V1.0
 */
public interface IPerformanceParamERPService extends IService<PerformanceParamERP> {

	public List<PerformanceParamERP> selectByMainId(String mainId);
}
