package com.just.oasystem.user.Controller;

import com.just.oasystem.user.model.UserInfo;
import com.just.oasystem.user.service.MailService;
import com.just.oasystem.user.service.UserService;
import com.just.oasystem.util.ResponeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 邮箱测试
 */
@RestController
public class MailController {

    @Autowired
    private MailService mailService;
    @Autowired
    private UserService userService;

    @PostMapping("/mail/sendMessage")
    public Map<String, Object> sendSimpleMailMessage(String email) {
        UserInfo info = new UserInfo();
        info.setEmail(email);
        info=userService.getUserInfoByNo(info);
        if(info==null){
            return ResponeUtil.respondError("没有该邮箱");
        }
        String subject = "OA系统用户密码重置";
        String context = "尊敬的"+info.getUserName()+"用户你好。\n" +
                "密码修改的链接为http:/10.136.158.124:8080/user/modifyPassword";
        String message = mailService.sendSimpleMailMessge(email, subject, context);
        if(!"".equals(message)){
            return ResponeUtil.respondError(message);
        }
        return ResponeUtil.respondSuccess();
    }
}
