package org.jeecg.modules.demo.exTable.service;

import org.jeecg.modules.demo.exTable.entity.OrgStrucParam;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 组织结构参数
 * @Author: jeecg-boot
 * @Date:   2021-08-30
 * @Version: V1.0
 */
public interface IOrgStrucParamService extends IService<OrgStrucParam> {

	public List<OrgStrucParam> selectByMainId(String mainId);
}
