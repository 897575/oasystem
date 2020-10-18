package com.just.oasystem.user.Controller;

import com.just.oasystem.user.model.UserInfo;
import com.just.oasystem.user.service.UserService;
import com.just.oasystem.util.OaUtil;
import com.just.oasystem.util.ResponeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
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
@Controller
public class UserController {

    @Resource
    private UserService userService;


    @ResponseBody
    @PostMapping("/usr/add")
    public Map<String, Object> registerInfo(UserInfo userInfo) {
        try {
            if (OaUtil.checkUserInfo(userInfo,"save")) {
                return ResponeUtil.respondError("注册信息不完备");
            }
            UserInfo resultUserInfo = userService.getUserInfoByNo(userInfo.getUserNo());
            if (resultUserInfo != null) {
                return ResponeUtil.respondError("信息已经存在");
            }
            String password = OaUtil.transformPass(userInfo.getPassword());
            userInfo.setPassword(password);
            userService.saveUserInfo(userInfo);
        } catch (Exception e) {
//            System.out.println(e.getMessage());
            //TODO 异常记录
            return ResponeUtil.respondError("保存失败");
        }
        return ResponeUtil.respondSuccess();
    }


    @ResponseBody
    @PostMapping("/user/check")
    public Map<String, Object> checkUser(String userNo, String password, String code, HttpSession httpSession) {
        if (userNo == null || "".equals(userNo) || password == null || "".equals(password) || code == null
                || "".equals(code)) {
            return ResponeUtil.respondError("参数缺失");
        }
        httpSession.setAttribute("code", "897575");
        try {
            String currentCode = httpSession.getAttribute("code").toString();
            if (!currentCode.equals(code)) {
                return ResponeUtil.respondError("验证码错误");
            }
            //先查数据库，后期可以考虑放入redis中
            UserInfo userInfo = userService.getUserInfoByNo(userNo);
            if (userInfo == null) {
                return ResponeUtil.respondError("用户不存在");
            }
            if (!OaUtil.transformPass(password).equals(userInfo.getPassword())) {
                return ResponeUtil.respondError("密码错误");
            }
            httpSession.setAttribute("userNo", userInfo.getUserNo());
            httpSession.setAttribute("authority", userInfo.getAuthority());
            return ResponeUtil.respondSuccessData(userInfo);
        } catch (Exception e) {
            //TODO 日志
            System.out.println(e.getMessage());
            return ResponeUtil.respondError("登录失败");
        }
    }

    @ResponseBody
    @PostMapping("/user/modifyBaseInfo")
    public Map<String, Object> modifyUserInfo(UserInfo userInfo) {
        if (OaUtil.checkUserInfo(userInfo,"modify")) {
            return ResponeUtil.respondError("请输入必要参数");
        }
        userService.updateUserInfo(userInfo);
        return ResponeUtil.respondSuccess();
    }

    @ResponseBody
    @PostMapping("/user/modifyPassword")
    public Map<String, Object> modifyPassword(String userNo, String oldPassword, String newPassword) {
        if (userNo == null || "".equals(userNo) || oldPassword == null || "".equals(oldPassword) ||
                newPassword == null || "".equals(newPassword)) {
            return ResponeUtil.respondError("请输入合适的参数");
        }
        try {
            UserInfo userInfo = userService.getUserInfoByNo(userNo);
            String str = OaUtil.transformPass(oldPassword);
            if (!str.equals(userInfo.getPassword())) {
                return ResponeUtil.respondError("原密码错误");
            }
            userInfo.setPassword(OaUtil.transformPass(newPassword));
            userService.updateUserInfo(userInfo);
            return ResponeUtil.respondSuccess();
        } catch (Exception e) {
            return ResponeUtil.respondError("修改密码失败");
        }
    }
}
