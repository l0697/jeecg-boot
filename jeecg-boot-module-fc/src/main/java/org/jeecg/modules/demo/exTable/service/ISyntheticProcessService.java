package org.jeecg.modules.demo.exTable.service;

import org.jeecg.modules.demo.exTable.entity.SyntheticProcess;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 合成工艺
 * @Author: jeecg-boot
 * @Date:   2021-08-30
 * @Version: V1.0
 */
public interface ISyntheticProcessService extends IService<SyntheticProcess> {

	public List<SyntheticProcess> selectByMainId(String mainId);
}
