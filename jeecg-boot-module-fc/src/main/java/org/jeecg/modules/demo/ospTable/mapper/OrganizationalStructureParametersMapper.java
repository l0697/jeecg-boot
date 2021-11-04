package org.jeecg.modules.demo.ospTable.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.demo.ospTable.entity.OrganizationalStructureParameters;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 组织结构参数
 * @Author: jeecg-boot
 * @Date:   2021-08-19
 * @Version: V1.0
 */
public interface OrganizationalStructureParametersMapper extends BaseMapper<OrganizationalStructureParameters> {

    List<String> queryAllAuth(@Param("permsPrefix")String permsPrefix);
    List<String> queryUserAuth(@Param("userId")String userId,@Param("permsPrefix")String permsPrefix);

}
