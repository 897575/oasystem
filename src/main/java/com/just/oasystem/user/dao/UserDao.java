package com.just.oasystem.user.dao;

import com.just.oasystem.user.model.UserInfo;

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


    List<UserInfo> getAllUserInfo();

    UserInfo getUserInfoByNo(String userNo);
}
