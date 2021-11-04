package org.jeecg.modules.demo.exTable.service;

import org.jeecg.modules.demo.exTable.entity.PerformanceParam;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 性能参数
 * @Author: jeecg-boot
 * @Date:   2021-08-30
 * @Version: V1.0
 */
public interface IPerformanceParamService extends IService<PerformanceParam> {

	public List<PerformanceParam> selectByMainId(String mainId);
}
