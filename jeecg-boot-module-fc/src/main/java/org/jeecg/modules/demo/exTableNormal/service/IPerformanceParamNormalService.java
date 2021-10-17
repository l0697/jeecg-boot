package org.jeecg.modules.demo.exTableNormal.service;

import org.jeecg.modules.demo.exTableNormal.entity.PerformanceParamNormal;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 性能参数
 * @Author: jeecg-boot
 * @Date:   2021-08-21
 * @Version: V1.0
 */
public interface IPerformanceParamNormalService extends IService<PerformanceParamNormal> {

	public List<PerformanceParamNormal> selectByMainId(String mainId);
}
