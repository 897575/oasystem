package com.just.oasystem.user.service;

import com.just.oasystem.user.model.UserInfo;
import org.apache.catalina.User;

import java.util.List;
import java.util.Map;

/**
 * 用户信息服务
 *
 * @author luyu
 * @since 20201017
 * @version v1.0
 *
 * copyright 18994139782@163.com
 */
public interface UserService {

    /**
     * 获取所有的用户
     * @return 所有用户信息
     */
    List<UserInfo> getAllUserInfo(Map<String,Object> param,int page,int limit);

    /**
     * 根据用户编号获取用户
     * @param userInfo 用户信息
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

    /**
     * 获取所有用户的姓名
     * @return 所有用户的姓名
     */
    List<Map<String,Object>> getUserInfo(String authority);

}
