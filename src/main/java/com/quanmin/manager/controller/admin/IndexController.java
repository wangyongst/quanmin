package com.quanmin.manager.controller.admin;

import com.quanmin.manager.service.AdminPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @Autowired
    public AdminPermissionService adminService;

    @RequestMapping("/")
    public String index(Model model) {
        return "index";
    }

    @RequestMapping("page-login")
    public String login() {
        return "page-login";
    }
}
