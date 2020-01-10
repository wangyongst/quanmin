package com.quanmin.manager.service.impl;

import com.quanmin.manager.entity.AdminRole;
import com.quanmin.manager.entity.AdminUser;
import com.quanmin.manager.model.AdminPermissionParameter;
import com.quanmin.manager.repository.AdminPermissionRepository;
import com.quanmin.manager.repository.AdminRole2PermissionRepository;
import com.quanmin.manager.repository.AdminRoleRepository;
import com.quanmin.manager.repository.AdminUserRepository;
import com.quanmin.manager.service.AdminPermissionService;
import com.quanmin.manager.utils.db.TimeUtils;
import com.quanmin.manager.utils.result.Result;
import com.quanmin.manager.utils.result.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

;


@Service
@SuppressWarnings("All")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class, readOnly = false)
public class AdminPermissionServiceImpl implements AdminPermissionService {

    private static final Logger logger = LogManager.getLogger(AdminPermissionServiceImpl.class);

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Autowired
    private AdminRoleRepository adminRoleRepository;

    @Autowired
    private AdminPermissionRepository adminPermissionRepository;

    @Autowired
    private AdminRole2PermissionRepository adminRole2PermissionRepository;

    @Override
    public Result me() {
        AdminUser me = (AdminUser) SecurityUtils.getSubject().getPrincipal();
        return ResultUtil.okWithData(adminUserRepository.findById(me.getId()).get());
    }

    @Override
    public Result findByAdminUsername(String username) {
        List<AdminUser> AdminUserList = adminUserRepository.findByUsername(username);
        if (AdminUserList.size() == 1) return ResultUtil.okWithData(AdminUserList.get(0));
        return ResultUtil.errorWithMessage("查询失败！");
    }

    @Override
    public Result adminUserList(AdminPermissionParameter adminPermissionParameter) {
        Sort sort = new Sort(Sort.Direction.DESC, "createtime");
//        AdminUser adminUser = new AdminUser();
//        if (StringUtils.isNotBlank(adminPermissionParameter.g())) adminUser.setMobile(adminPermissionParameter.getMobile());
//        if (StringUtils.isNotBlank(adminPermissionParameter.getName())) adminUser.setName(adminPermissionParameter.getName());
//        ExampleMatcher matcher = ExampleMatcher.matching()
//                .withMatcher("mobile", match -> match.contains())
//                .withMatcher("name", match -> match.contains());
//        Example<AdminUser> example = Example.of(adminUser, matcher);
//        return ResultUtil.okWithData(AdminUserRepository.findAll(example, sort));
        return ResultUtil.okWithData(adminUserRepository.findAll());
    }

    @Override
    public Result adminRoleList(AdminPermissionParameter adminPermissionParameter) {
        List<AdminRole> roles = adminRoleRepository.findAll();
        AdminRole adminRole = adminRoleRepository.findById(13).get();
        roles.remove(adminRole);
        return ResultUtil.okWithData(roles);
    }

    @Override
    public Result adminUser(AdminPermissionParameter adminPermissionParameter) {
        return ResultUtil.okWithData(adminUserRepository.findById(adminPermissionParameter.getUserid()).get());
    }

    @Override
    public Result adminUserSud(AdminPermissionParameter adminPermissionParameter) {
        if (adminPermissionParameter.getSud() != 3) {
            if (StringUtils.isBlank(adminPermissionParameter.getName())) return ResultUtil.errorWithMessage("名称不能为空！");
            if (StringUtils.isBlank(adminPermissionParameter.getUsername())) return ResultUtil.errorWithMessage("用户名不能为空！");
            if (adminPermissionParameter.getRoleid() == 0) return ResultUtil.errorWithMessage("配置角色未选择！");
            if (StringUtils.isBlank(adminPermissionParameter.getMobile())) return ResultUtil.errorWithMessage("电话不能为空！");
            if (StringUtils.isBlank(adminPermissionParameter.getPassword())) return ResultUtil.errorWithMessage("密码不能为空！");

            if (adminPermissionParameter.getName().length() > 10) return ResultUtil.errorWithMessage("名称不能超过10个字！");
            if (adminPermissionParameter.getUsername().length() > 20) return ResultUtil.errorWithMessage("用户名不能超过20个字！");
            String regex = "^[0-9]+$";
            if (!adminPermissionParameter.getMobile().matches(regex)) return ResultUtil.errorWithMessage("电话只能是数字！");
            if (adminPermissionParameter.getMobile().length() != 11) return ResultUtil.errorWithMessage("电话只能是11位手机号！");
            if (adminPermissionParameter.getPassword().length() < 3 || adminPermissionParameter.getPassword().length() > 20)
                return ResultUtil.errorWithMessage("密码长度不正确，请重新输入（最短3个字符，最长20个字符）！");
            regex = "^[a-z0-9A-Z]+$";
            if (!adminPermissionParameter.getPassword().matches(regex)) return ResultUtil.errorWithMessage("密码只支持数字和英文！");

            AdminUser adminUser = null;
            if (adminPermissionParameter.getSud() == 1) {
                adminUser = new AdminUser();
                if (adminPermissionParameter.getPassword().equals(adminPermissionParameter.getPassword2())) return ResultUtil.errorWithMessage("两次输入的密码不一致！");
                AdminUser me = (AdminUser) SecurityUtils.getSubject().getPrincipal();
                adminUser.setCreateusername(me.getName());
                adminUser.setCreatetime(TimeUtils.format(System.currentTimeMillis()));
            } else if (adminPermissionParameter.getSud() == 2) {
                if (adminPermissionParameter.getUserid() == 0) return ResultUtil.errorWithMessage("用户ID不能为空！");
                adminUser = adminUserRepository.findById(adminPermissionParameter.getUserid()).get();
            }

            adminUser.setName(adminPermissionParameter.getName());
            adminUser.setUsername(adminPermissionParameter.getUsername());
            adminUser.setPassword(new Md5Hash(adminPermissionParameter.getPassword()).toHex());
            adminUser.setMobile(adminPermissionParameter.getMobile());
            adminUserRepository.save(adminUser);
        } else if (adminPermissionParameter.getSud() == 3) {
            AdminUser adminUser = adminUserRepository.findById(adminPermissionParameter.getUserid()).get();
            deleteAdminUser(adminUser);
        }
        return ResultUtil.ok();
    }

    @Override
    public Result adminRole(AdminPermissionParameter adminPermissionParameter) {
        return ResultUtil.okWithData(adminRoleRepository.findById(adminPermissionParameter.getRoleid()).get());
    }

    @Override
    public Result adminRoleSud(AdminPermissionParameter adminPermissionParameter) {
        if (StringUtils.isBlank(adminPermissionParameter.getName())) return ResultUtil.errorWithMessage("角色名称不能为空！");
        if (adminPermissionParameter.getName().length() > 10) return ResultUtil.errorWithMessage("角色名称最多10个字！");

//        if (adminPermissionParameter.getPermission() != null && adminPermissionParameter.getPermission().size() > 0) {
//            adminPermissionParameter.getPermission().forEach(e -> {
//                Role2Permission role2Permission = new Role2Permission();
//                role2Permission.setRole(saveedRole);
//                role2Permission.setPermission(permissionRepository.findById(e).get());
//                adminRole2PermissionRepository.save(role2Permission);
//            });
//        }
        return ResultUtil.ok();
    }

    @Override
    public Result permissionList(AdminPermissionParameter adminPermissionParameter) {
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        return ResultUtil.okWithData(adminPermissionRepository.findAll(sort));
    }

    @Override
    public Result changeAdminPassword(AdminPermissionParameter adminPermissionParameter) {
        AdminUser adminUser = (AdminUser) SecurityUtils.getSubject().getPrincipal();
        if (StringUtils.isBlank(adminPermissionParameter.getPassword())) return ResultUtil.errorWithMessage("登录密码不能为空！");
        if (StringUtils.isBlank(adminPermissionParameter.getNewpassword())) return ResultUtil.errorWithMessage("新密码不能为空不能为空！");
        if (!adminPermissionParameter.getNewpassword().equals(adminPermissionParameter.getNewpassword2()))
            return ResultUtil.errorWithMessage("两次密码输入不一致，请重新输入！");
        if (adminPermissionParameter.getPassword().equals(adminPermissionParameter.getNewpassword()))
            return ResultUtil.errorWithMessage("新密码与原密码相同，请重新输入！");
        if (adminPermissionParameter.getNewpassword().length() < 3 || adminPermissionParameter.getNewpassword().length() > 20)
            return ResultUtil.errorWithMessage("密a码长度不正确，请重新输入（最短3个字符，最长20个字符）！");
        String regex = "^[a-z0-9A-Z]+$";
        if (!adminPermissionParameter.getNewpassword().matches(regex)) return ResultUtil.errorWithMessage("密码只能包含数字和英文！");
        if (adminUserRepository.findById(adminUser.getId()).get().getPassword().equals(new Md5Hash(adminPermissionParameter.getPassword()).toHex())) {
            adminUser.setPassword(new Md5Hash(adminPermissionParameter.getNewpassword()).toHex());
            adminUser.setIslock(0);
            adminUserRepository.save(adminUser);
            return ResultUtil.ok();
        } else {
            return ResultUtil.errorWithMessage("原密码错误！");
        }
    }

    public void deleteRole(AdminRole adminRole) {
        adminUserRepository.findByAdminRole(adminRole).forEach(e -> {
            e.setAdminRole(null);
            adminUserRepository.save(e);
        });
        adminRole2PermissionRepository.deleteAllByAdminRole(adminRole);
        adminRoleRepository.delete(adminRole);
    }

    public void deleteAdminUser(AdminUser adminUser) {
        adminUserRepository.delete(adminUser);
    }
}
