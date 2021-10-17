package org.jeecg.modules.FcUtils.api;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.List;

public interface FcAPI {
    /**
     * 获取用户有权限的字段，传入用户id，表的字段数组，表的字段权限编码前缀
     *
     * @param userId
     * @param tableFields
     * @param permsPrefix 查询可见的字段时：tableName:form:V-;查询可编辑的字段时参数三：tableName:form:E-
     * @return 字段名列表，为驼峰形式
     */
    List<String> getAuthFields(String userId, Field[] tableFields, String permsPrefix);

    List<String> getUnAuthFields(String userId, Field[] tableFields, String permsPrefix);

    /**
     * 将requset中的合法字段提取出来并进行转换
     *
     * @param request
     * @return
     */
    List<String> getLegalFields(HttpServletRequest request);

    void setObjProVal(Object obj, String property, Object value);

    String uploadLocal(MultipartFile mf, String bizPath);

    /**
     * 根据表名查询数据库ID
     *
     * @param table 表名
     * @return 数据库ID
     */
    String tableID(String table) throws Exception;

    /**
     * 查找table field字段所用的dict_code,dict_table,dict_text
     *
     * @param table 表名
     * @param field 字段名
     * @return 查找table field字段所用的dict_code,dict_table,dict_text;若table filed字段不存在则抛出异常
     */
    String[] dicInfo(String table, String field) throws Exception;

    /**
     * 校验value是否为合法值;若字典为空则默认合法
     *
     * @param table 表名
     * @param field 字段名
     * @param value 校验值
     * @return 合法情况
     */
    boolean verification(String table, String field, String value) throws Exception;
}
