package org.jeecg.modules.demo.exTableERP.service;

import org.jeecg.modules.demo.exTableERP.entity.SyntheticProcessERP;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 合成工艺
 * @Author: jeecg-boot
 * @Date:   2021-08-21
 * @Version: V1.0
 */
public interface ISyntheticProcessERPService extends IService<SyntheticProcessERP> {

	public List<SyntheticProcessERP> selectByMainId(String mainId);
}
