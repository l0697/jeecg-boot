package org.jeecg.modules.enhance.service;

import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.modules.online.cgform.mapper.OnlCgformFieldMapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

@Service
public class NormalService {
    @Autowired
    ISysBaseAPI iSysBaseAPI;
    /**
     * 根据表名查询数据库ID
     *
     * @param table 表名
     * @return 数据库ID
     */
    @Nullable
    public String tableID(String table) throws Exception {
        OnlCgformFieldMapper onlCgformFieldMapper = SpringContextUtils.getBean(OnlCgformFieldMapper.class);
        String sqlDicIdStr = String.format("select id from onl_cgform_head where table_name='%s'", table);
        List<Map<String, Object>> list = onlCgformFieldMapper.queryListBySql(sqlDicIdStr);
        if (list.isEmpty()) {
            throw new Exception(String.format("can't find table named '%s'", table));
        }
        System.out.println("~~~~~~~~~~~~" + list.get(0).get("id").toString());
        return list.get(0).get("id").toString();
    }

    /**
     * 查找table field字段所用的dict_code,dict_table,dict_text
     *
     * @param table 表名
     * @param field 字段名
     * @return 查找table field字段所用的dict_code,dict_table,dict_text;若table filed字段不存在则抛出异常
     */
    @NotNull
    public String[] dicInfo(String table, String field) throws Exception {
        String tableId = tableID(table);//查找数据库ID
        OnlCgformFieldMapper onlCgformFieldMapper = SpringContextUtils.getBean(OnlCgformFieldMapper.class);
        String sqlDicIdStr = String.format(//查找table filed字段所使用的dict_code dict_table dict_text
                "select dict_field,dict_table,dict_text " +
                        "from onl_cgform_field " +
                        "where cgform_head_id='%s' and db_field_name='%s'", tableId, field);
        List<Map<String, Object>> list = onlCgformFieldMapper.queryListBySql(sqlDicIdStr);
        if (list.isEmpty()) {
            //table 没有 field字段
            throw new Exception(String.format("table '%s' don's has a filed named '%s'", table, field));
        }
        //可能为空
        String[] res = new String[3];
        res[0] = list.get(0).get("dict_field").toString().isEmpty() ? null : list.get(0).get("dict_field").toString();
        res[1] = list.get(0).get("dict_table").toString().isEmpty() ? null : list.get(0).get("dict_table").toString();
        res[2] = list.get(0).get("dict_text").toString().isEmpty() ? null : list.get(0).get("dict_text").toString();
        return res;
    }

    /**
     * 校验value是否为合法值;若字典为空则默认合法
     *
     * @param table 表名
     * @param field 字段名
     * @param value 校验值
     * @return 合法情况
     */
    public boolean verification(String table, String field, String value) throws Exception {
        String[] dict_info = dicInfo(table, field);
        if (dict_info[0] == null && dict_info[1] == null && dict_info[2] == null) {
            //未使用字典
            return true;
        }
        List<DictModel> words;
        if (dict_info[0] != null && dict_info[1] != null && dict_info[2] != null) {
            //使用了字典表
            words = iSysBaseAPI.queryTableDictItemsByCode(dict_info[1], dict_info[2], dict_info[0]);
        } else if (dict_info[0] != null && dict_info[1] == null && dict_info[2] == null) {
            words = iSysBaseAPI.queryDictItemsByCode(dict_info[0]);
        } else {
            throw new Exception(String.format(" the dict config of table '%s' '%s' is wrong", table, field));
        }
        for (DictModel dictModel : words) {
            if (dictModel.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
