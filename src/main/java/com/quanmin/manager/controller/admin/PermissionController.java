package com.quanmin.manager.controller.admin;


import com.quanmin.manager.service.AdminPermissionService;
import com.quanmin.manager.utils.result.Result;
import com.quanmin.manager.model.AdminPermissionParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class PermissionController {

    @Autowired
    public AdminPermissionService adminPermissionService;

    //我
    @GetMapping("/me")
    public Result me(@ModelAttribute AdminPermissionParameter adminParameter) {
        return adminPermissionService.me();
    }

    //账号列表
    @GetMapping("/user/list")
    public Object userList(@ModelAttribute AdminPermissionParameter adminParameter) {
        return adminPermissionService.userList(adminParameter).getData();
    }

    //账号详情
    @GetMapping("/user")
    public Result user(@ModelAttribute AdminPermissionParameter adminParameter) {
        return adminPermissionService.user(adminParameter);
    }

    //账号增删改
    @PostMapping("/user/sud")
    public Result userSud(@ModelAttribute AdminPermissionParameter adminParameter) {
        return adminPermissionService.userSud(adminParameter);
    }

    //角色列表
    @GetMapping("/role/list")
    public Object roleList(@ModelAttribute AdminPermissionParameter adminParameter) {
        return adminPermissionService.roleList(adminParameter).getData();
    }

    //角色详情
    @GetMapping("/role")
    public Result role(@ModelAttribute AdminPermissionParameter adminParameter) {
        return adminPermissionService.role(adminParameter);
    }

    //角色增删改
    @PostMapping("/role/sud")
    public Result roleSud(@ModelAttribute AdminPermissionParameter adminParameter) {
        return adminPermissionService.roleSud(adminParameter);
    }

    //修改密码
    @PostMapping("/changepassword")
    public Result changePassword(@ModelAttribute AdminPermissionParameter adminParameter) {
        return adminPermissionService.changePassword(adminParameter);
    }
}
