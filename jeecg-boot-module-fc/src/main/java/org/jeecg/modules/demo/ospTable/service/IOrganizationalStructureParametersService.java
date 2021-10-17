package org.jeecg.modules.demo.ospTable.service;

import org.jeecg.modules.demo.ospTable.entity.OrganizationalStructureParameters;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 组织结构参数
 * @Author: jeecg-boot
 * @Date:   2021-08-19
 * @Version: V1.0
 */
public interface IOrganizationalStructureParametersService extends IService<OrganizationalStructureParameters> {
    String getExportFields();
}
