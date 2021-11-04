package org.jeecg.modules.FcUtils.api.impl;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.common.util.CommonUtils;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.FcUtils.api.FcAPI;
import org.jeecg.modules.FcUtils.mapper.FcMapper;
import org.jeecg.modules.online.cgform.mapper.OnlCgformFieldMapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

@Slf4j
@Service
public class FcAPIImpl implements FcAPI {
    @Autowired
    ISysBaseAPI iSysBaseAPI;
    @Autowired
    FcMapper fcMapper;

    @Override
    public List<String> getAuthFields(String userId, Field[] tableFields, String permsPrefix) {
//        //获取当前登录人
//        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        //权限配置列导出示例
        List<String> noAuthList = new ArrayList<>();
        List<String> exportFieldsList = new ArrayList<>();
        //查询配置菜单有效字段
        List<String> allAuth = this.fcMapper.queryAllAuth(permsPrefix);
        //查询已授权字段
        List<String> userAuth = this.fcMapper.queryUserAuth(userId, permsPrefix);
        //列出未授权字段
        for (String perms : allAuth) {
            if (!userAuth.contains(perms)) {
                //截去前缀，只返回字段名
                noAuthList.add(perms.substring(permsPrefix.length()));
            }
        }
        //实体类中字段与未授权字段比较，列出需导出字段
        List<Field> list = new ArrayList(Arrays.asList(tableFields));
        for (Field field : list) {
            if (!noAuthList.contains(field.getName())) {
                exportFieldsList.add(field.getName());
            }
        }
        return exportFieldsList;
    }

    @Override
    public List<String> getUnAuthFields(String userId, Field[] tableFields, String permsPrefix) {
        List<String> noAuthList = new ArrayList<>();
        //查询配置菜单有效字段
        List<String> allAuth = this.fcMapper.queryAllAuth(permsPrefix);
        //查询已授权字段
        List<String> userAuth = this.fcMapper.queryUserAuth(userId, permsPrefix);
        //列出未授权字段
        for (String perms : allAuth) {
            if (!userAuth.contains(perms)) {
                //截去前缀，只返回字段名
                noAuthList.add(perms.substring(permsPrefix.length()));
            }
        }
        return noAuthList;
    }

    @Override
    public List<String> getLegalFields(HttpServletRequest request) {
        List<String> requestFields = new ArrayList<String>();
        String[] fields = request.getParameterMap().get("field")[0].split(",");
        for (String field : fields) {
            if (!field.equals("id") && !field.isEmpty() && !field.equals("action")) {
                if (field.contains("_dictText")) {
                    requestFields.add(field.substring(0, field.lastIndexOf("_dictText")));
                } else requestFields.add(field);
            }
        }
        return requestFields;
    }

    @Override
    public void setObjProVal(Object obj, String property, Object value) {
        int len = property.length();
        String methodName = "set" + property.substring(0, 1).toUpperCase() + property.substring(1, len);
        try {
            Method[] methods = obj.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    method.invoke(obj, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Value(value = "${jeecg.path.upload}")
    private String uploadpath;

    public String uploadLocal(MultipartFile mf, String bizPath) {
        try {
            String ctxPath = uploadpath;
            String fileName = null;
            File file = new File(ctxPath + File.separator + bizPath + File.separator);
            if (!file.exists()) {
                file.mkdirs();// 创建文件根目录
            }
            String orgName = mf.getOriginalFilename();// 获取文件名
            orgName = CommonUtils.getFileName(orgName);
            if (orgName.indexOf(".") != -1) {
                fileName = orgName.substring(0, orgName.lastIndexOf(".")) + "_" + System.currentTimeMillis() + orgName.substring(orgName.lastIndexOf("."));
            } else {
                fileName = orgName + "_" + System.currentTimeMillis();
            }
            String savePath = file.getPath() + File.separator + fileName;
            File savefile = new File(savePath);
            FileCopyUtils.copy(mf.getBytes(), savefile);
            String dbpath = null;
            if (oConvertUtils.isNotEmpty(bizPath)) {
                dbpath = bizPath + File.separator + fileName;
            } else {
                dbpath = fileName;
            }
            if (dbpath.contains("\\")) {
                dbpath = dbpath.replace("\\", "/");
            }
            return dbpath;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return "";
    }

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

    @Override
    public Map<String, Object> getQueryMap(HttpServletRequest request) {
        Map parameterMap = request.getParameterMap();
        HashMap hashMap = new HashMap();
        Iterator parameterIterator = parameterMap.entrySet().iterator();
        String key = "";
        String value = "";

        for (Object object = null; parameterIterator.hasNext(); hashMap.put(key, value)) {
            Map.Entry entry = (Map.Entry) parameterIterator.next();
            key = (String) entry.getKey();
            //Camel case to under score Case
            //key=CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, key);
            object = entry.getValue();
            if (!"_t".equals(key) && null != object) {
                if (!(object instanceof String[])) {
                    value = object.toString();
                } else {
                    String[] valueTmp = (String[]) ((String[]) object);

                    for (int index = 0; index < valueTmp.length; ++index) {
                        value = valueTmp[index] + ",";
                    }

                    value = value.substring(0, value.length() - 1);
                }
            } else {
                value = "";
            }
        }
        return hashMap;
    }
}
