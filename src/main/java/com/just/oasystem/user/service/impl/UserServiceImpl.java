package com.just.oasystem.user.service.impl;

import com.just.oasystem.user.dao.UserDao;
import com.just.oasystem.user.model.UserInfo;
import com.just.oasystem.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public List<UserInfo> getAllUserInfo(Map<String,Object> param,int page,int limit) {
        param.put("page",(page - 1)*10);
        param.put("limit",limit);
        return userDao.getAllUserInfo(param);
    }

    @Override
    public UserInfo getUserInfoByNo(UserInfo param) {
        return userDao.getUserInfoByNo(param);
    }

    @Override
    public void saveUserInfo(UserInfo userInfo) {
        userDao.saveUserInfo(userInfo);
    }

    @Override
    public void updateUserInfo(UserInfo userInfo) {
        userDao.updateUserInfo(userInfo);
    }

    @Override
    public void deleteInfos(String[] ids) {
        userDao.deleteInfos(ids);
    }

    @Override
    public int countAllInfo() {
        return userDao.countAllInfo();
    }

    @Override
    public List<Map<String,Object>> getUserInfo(String authority) {

        return userDao.getUserInfo(authority);
    }
}
