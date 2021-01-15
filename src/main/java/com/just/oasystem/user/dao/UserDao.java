package com.just.oasystem.user.dao;

import com.just.oasystem.user.model.UserInfo;
import org.apache.catalina.User;

import java.util.List;
import java.util.Map;

/**
 * 人员管理交互层
 *
 * @author luyu
 * @since 20201017
 * @version v1.0
 *
 * copyright 18994139782@163.com
 *
 */
public interface UserDao {


    List<Map<String,Object>> getUserInfo(String authority);

    /**
     * 获取所有的用户
     * @return 所有用户信息
     */
    List<UserInfo> getAllUserInfo(Map<String,Object> param);

    /**
     * 根据用户编号获取用户
     * @param param 用户编号
     * @return 用户信息
     */
    UserInfo getUserInfoByNo(UserInfo param);

    /**
     * 保存用户信息
     * @param userInfo 用户信息
     */
    void saveUserInfo(UserInfo userInfo);

    /**
     * 更新用户信息
     * @param userInfo 用户更新信息
     */
    void updateUserInfo(UserInfo userInfo);

    /**
     * 删除元素
     * @param ids no
     */
    void deleteInfos(String[] ids);

    /**
     * 统计总条数
     * @return
     */
    int countAllInfo();


}
