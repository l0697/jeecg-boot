package org.jeecg.modules.demo.exTableNormal.service;

import org.jeecg.modules.demo.exTableNormal.entity.SyntheticProcessNormal;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 合成工艺
 * @Author: jeecg-boot
 * @Date:   2021-08-21
 * @Version: V1.0
 */
public interface ISyntheticProcessNormalService extends IService<SyntheticProcessNormal> {

	public List<SyntheticProcessNormal> selectByMainId(String mainId);
}
