package com.just.oasystem.user.Controller;

import com.just.oasystem.user.model.UserInfo;
import com.just.oasystem.user.service.UserService;
import com.just.oasystem.util.OaUtil;
import com.just.oasystem.util.RedisUtil;
import com.just.oasystem.util.ResponeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户信息控制层
 *
 * @author luyu
 * @version v1.0
 * <p>
 * copyright 18994139782@163.com
 * @since 20201017
 */
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/usr/add")
    public Map<String, Object> registerInfo(UserInfo userInfo) {
        try {
            if (OaUtil.checkUserInfo(userInfo,"save")) {
                return ResponeUtil.respondError("注册信息不完备");
            }
            UserInfo resultUserInfo = userService.getUserInfoByNo(userInfo);
            if (resultUserInfo != null) {
                return ResponeUtil.respondError("信息已经存在，请查看电话、邮箱和学号");
            }
//            String password = OaUtil.transformPass(userInfo.getPassword());
//            userInfo.setPassword(password);
            userService.saveUserInfo(userInfo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            //TODO 异常记录
            return ResponeUtil.respondError("保存失败");
        }
        return ResponeUtil.respondSuccess();
    }


    @PostMapping("/user/check")
    public Map<String, Object> checkUser(String email, String password,  HttpSession httpSession) {
        if (email == null || "".equals(email) || password == null || "".equals(password)) {
            return ResponeUtil.respondError("参数缺失");
        }
        try {
            Object userNo = httpSession.getAttribute("userNo");
            if(userNo!=null){
                return ResponeUtil.respondSuccess();
            }
            //先查数据库，后期可以考虑放入redis中
            UserInfo param = new UserInfo();
            param.setEmail(email);
            UserInfo userInfo = userService.getUserInfoByNo(param);
            if (userInfo == null) {
                return ResponeUtil.respondError("用户不存在");
            }
            if (!OaUtil.transformPass(password).equals(userInfo.getPassword())) {
                return ResponeUtil.respondError("密码错误");
            }

            httpSession.setAttribute("userNo",userInfo.getUserNo());
            //将数据存入redis中30分钟与session一致
//            if(redisUtil.get(userInfo.getUserNo())==null){
//                redisUtil.set(userInfo.getUserNo(),JSON.toJSONString(userInfo),-1);
//            }
            return ResponeUtil.respondSuccessData(userInfo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            //TODO 日志
            return ResponeUtil.respondError("登录失败");
        }
    }

    @PostMapping("/user/modifyBaseInfo")
    public Map<String, Object> modifyUserInfo(UserInfo userInfo) {
        if (OaUtil.checkUserInfo(userInfo,"modify")) {
            return ResponeUtil.respondError("请输入必要参数");
        }
        userService.updateUserInfo(userInfo);
        return ResponeUtil.respondSuccess();
    }

//    @PostMapping("/user/modifyPassword")
//    public Map<String, Object> modifyPassword(String email, String oldPassword, String newPassword) {
//
//
//
//
//        return null;
//    }

    @PostMapping("/user/queryInfoByParam")
    public Map<String,Object> queryAllUser(UserInfo userInfo){
        try {
            int page =1;
            int limit = 10;
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("page", page);
            resultMap.put("limit", limit);
            Map<String,Object> param =OaUtil.objectToMap(userInfo);
            List<UserInfo> userInfos = userService.getAllUserInfo(param,page, limit);
            resultMap.put("code", "0");
            resultMap.put("data", userInfos);
            return resultMap;
        }catch (Exception e){
            return null;
        }

    }

    @PostMapping("/user/delete")
    public Map<String,Object> deleteByNo(String[] ids){
        userService.deleteInfos(ids);
        return ResponeUtil.respondSuccess();
    }


    @GetMapping("/user/queryAll")
    public Map<String,Object> queryAllUser(int page,int limit){
        try {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("page", page);
            resultMap.put("limit", limit);
            Map<String,Object> param = new HashMap<>();
            List<UserInfo> userInfos = userService.getAllUserInfo(param,page, limit);
            resultMap.put("code", "0");
            resultMap.put("count", userService.countAllInfo());
            resultMap.put("data", userInfos);
            return resultMap;
        }catch (Exception e){
            return null;
        }
    }

    @GetMapping("/user/queryAllName")
    public Map<String,Object> queryAllUserName(){
        List<Map<String,Object>> studentInfos = userService.getUserInfo("学生");
        List<Map<String,Object>> teacherInfos = userService.getUserInfo("老师");
        studentInfos.addAll(teacherInfos);
        Map<String,Object> param = new HashMap<>();
        param.put("student",studentInfos);
//        param.put("teacher",teacherInfos);
        return param;
    }
}
