package org.jeecg.modules.demo.exTableERP.service;

import org.jeecg.modules.demo.exTableERP.entity.OrgStrucParamERP;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 组织结构参数
 * @Author: jeecg-boot
 * @Date:   2021-08-21
 * @Version: V1.0
 */
public interface IOrgStrucParamERPService extends IService<OrgStrucParamERP> {

	public List<OrgStrucParamERP> selectByMainId(String mainId);
}
