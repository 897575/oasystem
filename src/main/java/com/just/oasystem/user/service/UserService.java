package com.just.oasystem.user.service;

import com.just.oasystem.user.model.UserInfo;

import java.util.List;

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


    List<UserInfo> getAllUserInfo();

    UserInfo getUserInfoByNo(String userNo);

    void saveUserInfo(UserInfo userInfo);

    void updateUserInfo(UserInfo userInfo);


}
