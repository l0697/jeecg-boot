package org.jeecg.modules.enhance.component;


import com.alibaba.fastjson.JSONObject;
import org.jeecg.modules.enhance.service.NormalService;
import org.jeecg.modules.online.cgform.enhance.CgformEnhanceJavaInter;
import org.jeecg.modules.online.config.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component("OSPTableCheckAppearance")
public class OSPTableCheckAppearance implements CgformEnhanceJavaInter {
    @Autowired
    NormalService normalService;

    @Override
    public int execute(String s, Map<String, Object> map) throws BusinessException {
        return 0;
    }

    @Override
    public int execute(String s, JSONObject jsonObject) throws BusinessException {
        //校验appearance字段
        boolean flag;//名称与值，导入时给名称，这里给出的是值
        try {
            flag = normalService.verification(s, "appearance", jsonObject.getString("appearance"));
        } catch (Exception e) {
            throw new BusinessException(e.toString());
        }
        if (!flag) {
            throw new BusinessException("形貌错误");
        }
        return 0;
    }
}
