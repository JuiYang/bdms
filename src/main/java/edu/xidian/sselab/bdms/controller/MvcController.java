package edu.xidian.sselab.bdms.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MvcController {
    
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "桥梁检测管理系统");
        return "index";
    }
    
    @GetMapping("/user")
    public String user(Model model) {
        model.addAttribute("title", "用户管理");
        return "user";
    }

    @GetMapping("/login")
    public String login(Model model, String error) {
        if (StringUtils.isNotEmpty(error)) {
            model.addAttribute("errorMsg", "登录失败！");
        }
        model.addAttribute("title", "登录");
        return "login";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }

    @GetMapping("/404")
    public String error404() {
        return "/error/404";
    }

}
