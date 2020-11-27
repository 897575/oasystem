package com.just.oasystem.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 视图控制器
 *
 * @author luyu
 * @since 20201022
 * @version v1.0
 *
 * copyright 18994139782@63.com
 */
@Controller
public class VeiwController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/oaIndex")
    public String index(){
        return "oaIndex";
    }
}
