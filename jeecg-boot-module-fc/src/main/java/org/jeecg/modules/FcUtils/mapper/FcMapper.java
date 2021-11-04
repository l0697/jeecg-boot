package org.jeecg.modules.FcUtils.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FcMapper {
    /**
     * 根据权限编码前缀，查询所有权限编码
     * @param permsPrefix
     * @return
     */
    public List<String> queryAllAuth(@Param("permsPrefix")String permsPrefix);

    /**
     * 根据权限编码前缀和用户id，查询所有用户拥有的权限编码
     * @param userId
     * @param permsPrefix
     * @return
     */
    public List<String> queryUserAuth(@Param("userId")String userId,@Param("permsPrefix")String permsPrefix);

}
