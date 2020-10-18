package com.just.oasystem.user.Controller;

import com.just.oasystem.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户信息控制层
 *
 * @author luyu
 * @since 20201017
 * @version v1.0
 *
 * copyright 18994139782@163.com
 */
@Controller
public class UserController {

    @Resource
    private UserService userService;

    @ResponseBody
    @GetMapping("/user/all")
    public Map<String,Object> getUserInfos(){
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("200",200);
        resultMap.put("data",userService.getAllUserInfo());
        return resultMap;
    }

}
