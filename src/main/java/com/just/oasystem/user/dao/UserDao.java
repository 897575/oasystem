package com.just.oasystem.user.dao;

import com.just.oasystem.user.model.UserInfo;
import org.apache.catalina.User;

import java.util.List;

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


    /**
     * 获取所有的用户
     * @return 所有用户信息
     */
    List<UserInfo> getAllUserInfo();

    /**
     * 根据用户编号获取用户
     * @param userNo 用户编号
     * @return 用户信息
     */
    UserInfo getUserInfoByNo(UserInfo userInfo);

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
}
