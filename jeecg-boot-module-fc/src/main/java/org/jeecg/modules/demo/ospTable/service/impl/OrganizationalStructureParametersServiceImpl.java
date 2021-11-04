package org.jeecg.modules.demo.ospTable.service.impl;

import java.lang.reflect.Field;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.modules.demo.ospTable.entity.OrganizationalStructureParameters;
import org.jeecg.modules.demo.ospTable.mapper.OrganizationalStructureParametersMapper;
import org.jeecg.modules.demo.ospTable.service.IOrganizationalStructureParametersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: 组织结构参数
 * @Author: jeecg-boot
 * @Date:   2021-08-19
 * @Version: V1.0
 */
@Service
public class OrganizationalStructureParametersServiceImpl extends ServiceImpl<OrganizationalStructureParametersMapper, OrganizationalStructureParameters> implements IOrganizationalStructureParametersService {
    @Autowired
    OrganizationalStructureParametersMapper organizationalStructureParametersMapper;
    public String getExportFields() {
        //获取当前登录人
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        //权限配置列导出示例
        List<String> noAuthList = new ArrayList<>();
        List<String> exportFieldsList = new ArrayList<>();
        //1.此前缀必须与列表字段权限控制前缀一致
        String permsPrefix = "osp:form:";
        //查询配置菜单有效字段
        List<String> allAuth = this.organizationalStructureParametersMapper.queryAllAuth(permsPrefix);
        //查询已授权字段
        List<String> userAuth = this.organizationalStructureParametersMapper.queryUserAuth(sysUser.getId(),permsPrefix);
        //列出未授权字段
        for(String perms : allAuth){
            if(!userAuth.contains(perms)){
                noAuthList.add(perms.substring(permsPrefix.length()));
            }
        }
        //实体类中字段与未授权字段比较，列出需导出字段
        Field[] fileds = OrganizationalStructureParameters.class.getDeclaredFields();
        List<Field> list = new ArrayList(Arrays.asList(fileds));
        for(Field field : list){
            if(!noAuthList.contains(field.getName())){
                exportFieldsList.add(field.getName());
            }
        }
        return exportFieldsList.size()>0 ? String.join(",", exportFieldsList) : "";
    }
}
