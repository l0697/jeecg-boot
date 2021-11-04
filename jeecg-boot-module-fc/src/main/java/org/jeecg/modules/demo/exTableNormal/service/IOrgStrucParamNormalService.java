package org.jeecg.modules.demo.exTableNormal.service;

import org.jeecg.modules.demo.exTableNormal.entity.OrgStrucParamNormal;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 组织结构参数
 * @Author: jeecg-boot
 * @Date:   2021-08-21
 * @Version: V1.0
 */
public interface IOrgStrucParamNormalService extends IService<OrgStrucParamNormal> {

	public List<OrgStrucParamNormal> selectByMainId(String mainId);
}
