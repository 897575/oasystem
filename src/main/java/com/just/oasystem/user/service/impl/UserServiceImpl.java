package com.just.oasystem.user.service.impl;

import com.just.oasystem.user.dao.UserDao;
import com.just.oasystem.user.model.UserInfo;
import com.just.oasystem.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public List<UserInfo> getAllUserInfo() {
        return userDao.getAllUserInfo();
    }

    @Override
    public UserInfo getUserInfoByNo(UserInfo userInfo) {
        return userDao.getUserInfoByNo(userInfo);
    }

    @Override
    public void saveUserInfo(UserInfo userInfo) {
        userDao.saveUserInfo(userInfo);
    }

    @Override
    public void updateUserInfo(UserInfo userInfo) {
        userDao.updateUserInfo(userInfo);
    }
}
