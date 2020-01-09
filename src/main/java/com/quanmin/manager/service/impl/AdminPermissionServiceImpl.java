package com.quanmin.manager.service.impl;

import com.quanmin.manager.entity.AdminUser;
import com.quanmin.manager.model.AdminPermissionParameter;
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
    private AdminRole2PermissionRepository adminRole2PermissionRepository;

    @Override
    public Result me() {
        AdminUser me = (AdminUser) SecurityUtils.getSubject().getPrincipal();
        return ResultUtil.okWithData(adminUserRepository.findById(me.getId()).get());
    }

    @Override
    public Result findByUsername(String username) {
        List<AdminUser> AdminUserList = adminUserRepository.findByUsername(username);
        if (AdminUserList.size() == 1) return ResultUtil.okWithData(AdminUserList.get(0));
        return null;
    }

    @Override
    public Result userList(AdminPermissionParameter adminPermissionParameter) {
        Sort sort = new Sort(Sort.Direction.DESC, "createtime");
        AdminUser adminUser = new AdminUser();
        if (StringUtils.isNotBlank(adminPermissionParameter.g())) adminUser.setMobile(adminPermissionParameter.getMobile());
        if (StringUtils.isNotBlank(adminPermissionParameter.getName())) adminUser.setName(adminPermissionParameter.getName());
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("mobile", match -> match.contains())
                .withMatcher("name", match -> match.contains());
        Example<AdminUser> example = Example.of(AdminUser, matcher);
        return ResultUtil.okWithData(AdminUserRepository.findAll(example, sort));
    }

    @Override
    public Result roleList(AdminPermissionParameter adminPermissionParameter) {
        List<Role> roles = roleRepository.findAll();
        Role role = roleRepository.findById(13).get();
        roles.remove(role);
        return ResultUtil.okWithData(roles);
    }

    @Override
    public Result AdminUser(AdminPermissionParameter adminPermissionParameter) {
        return ResultUtil.okWithData(AdminUserRepository.findById(adminPermissionParameter.getAdminUserid()).get());
    }

    @Override
    public Result AdminUserSud(AdminPermissionParameter adminPermissionParameter) {
        AdminUser AdminUser = null;
        if (adminPermissionParameter.getAdminUserid() == 0) {
            if (StringUtils.isBlank(adminPermissionParameter.getMobile())) return ResultUtil.errorWithMessage("电话不能为空！");
            if (AdminUserRepository.findByMobile(adminPermissionParameter.getMobile()).size() > 0)
                return ResultUtil.errorWithMessage("电话已经存在！");
            AdminUser = new AdminUser();
            AdminUser.setCreatetime(TimeUtils.format(System.currentTimeMillis()));
            AdminUser.setIschange(0);
            AdminUser me = (AdminUser) SecurityUtils.getSubject().getPrincipal();
            AdminUser.setCreateAdminUsername(me.getName());
        } else {
            AdminUser = AdminUserRepository.findById(adminPermissionParameter.getAdminUserid()).get();
            if (adminPermissionParameter.getDelete() != 0) {
                if (AdminUser.getId() == ((AdminUser) SecurityUtils.getSubject().getPrincipal()).getId())
                    SecurityUtils.getSubject().logout();
                deleteAdminUser(AdminUser);
                return ResultUtil.ok();
            }
        }
        if (StringUtils.isBlank(adminPermissionParameter.getMobile())) return ResultUtil.errorWithMessage("电话不能为空！");
        if (StringUtils.isBlank(adminPermissionParameter.getName())) return ResultUtil.errorWithMessage("登录姓名不能为空！");
        if (adminPermissionParameter.getName().length() > 10) return ResultUtil.errorWithMessage("登录姓名不能超过10个字！");
        String regex = "^[0-9]+$";
        if (!adminPermissionParameter.getMobile().matches(regex)) return ResultUtil.errorWithMessage("电话只能是数字！");
        if (adminPermissionParameter.getMobile().length() != 11) return ResultUtil.errorWithMessage("电话只能是11位数字！");
        if (StringUtils.isBlank(adminPermissionParameter.getPassword())) return ResultUtil.errorWithMessage("密码不能为空！");
        regex = "^[a-z0-9A-Z]+$";
        if (!adminPermissionParameter.getPassword().matches(regex)) return ResultUtil.errorWithMessage("密码只支持数字和英文！");
        if (adminPermissionParameter.getRoleid() == 0) return ResultUtil.errorWithMessage("配置角色未选择！");
        if (adminPermissionParameter.getRoleid() == -1) {
            if (adminPermissionParameter.getSupplierid() == 0) return ResultUtil.errorWithMessage("供应商未选择！");
            AdminUser.setSupplier(supplierRepository.findById(adminPermissionParameter.getSupplierid()).get());
            AdminUser.setRole(null);
            AdminUser.setDeliver(null);
        } else if (adminPermissionParameter.getRoleid() == -2) {
            AdminUser.setDeliver(1);
            AdminUser.setSupplier(null);
            AdminUser.setRole(null);
        } else {
            AdminUser.setSupplier(null);
            AdminUser.setDeliver(null);
            AdminUser.setRole(roleRepository.findById(adminPermissionParameter.getRoleid()).get());
        }
        AdminUser.setName(adminPermissionParameter.getName());
        AdminUser.setPassword(new Md5Hash(adminPermissionParameter.getPassword()).toHex());
        AdminUser.setMobile(adminPermissionParameter.getMobile());
        AdminUserRepository.save(AdminUser);
        return ResultUtil.ok();
    }

    @Override
    public Result role(AdminPermissionParameter adminPermissionParameter) {
        return ResultUtil.okWithData(roleRepository.findById(adminPermissionParameter.getRoleid()).get());
    }

    @Override
    public Result roleSud(AdminPermissionParameter adminPermissionParameter) {
        Role role = null;
        if (adminPermissionParameter.getRoleid() == 0) {
            role = new Role();
        } else {
            role = roleRepository.findById(adminPermissionParameter.getRoleid()).get();
            if (adminPermissionParameter.getDelete() != 0) {
                deleteRole(role);
                return ResultUtil.ok();
            }
        }
        if (StringUtils.isBlank(adminPermissionParameter.getName())) return ResultUtil.errorWithMessage("角色名称不能为空！");
        if (adminPermissionParameter.getName().length() > 10) return ResultUtil.errorWithMessage("角色名称最多10个字！");
        role.setName(adminPermissionParameter.getName());
        if (adminPermissionParameter.getProjectid() != 0)
            role.setProjectid(projectRepository.findById(adminPermissionParameter.getProjectid()).get().getId());
        Role saveedRole = roleRepository.save(role);
        role2PermissionRepository.deleteAllByRole(role);
        if (adminPermissionParameter.getPermission() != null && adminPermissionParameter.getPermission().size() > 0) {
            adminPermissionParameter.getPermission().forEach(e -> {
                Role2Permission role2Permission = new Role2Permission();
                role2Permission.setRole(saveedRole);
                role2Permission.setPermission(permissionRepository.findById(e).get());
                role2PermissionRepository.save(role2Permission);
            });
        }
        return ResultUtil.ok();
    }

    @Override
    public Result permissionList(AdminPermissionParameter adminPermissionParameter) {
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        return ResultUtil.okWithData(permissionRepository.findAll(sort));
    }

    @Override
    public Result changePassword(AdminPermissionParameter adminPermissionParameter) {
        AdminUser AdminUser = (AdminUser) SecurityUtils.getSubject().getPrincipal();
        if (StringUtils.isBlank(adminPermissionParameter.getPassword())) return ResultUtil.errorWithMessage("原录密码不能为空！");
        if (StringUtils.isBlank(adminPermissionParameter.getNewpassword())) return ResultUtil.errorWithMessage("新密码不能为空不能为空！");
        if (!adminPermissionParameter.getNewpassword().equals(adminPermissionParameter.getNewpassword2()))
            return ResultUtil.errorWithMessage("两次密码输入不一致，请重新输入！");
        if (adminPermissionParameter.getPassword().equals(adminPermissionParameter.getNewpassword()))
            return ResultUtil.errorWithMessage("新密码与原密码相同，请重新输入！");
        if (adminPermissionParameter.getNewpassword().length() < 3 || adminPermissionParameter.getNewpassword().length() > 20)
            return ResultUtil.errorWithMessage("密码长度不正确，请重新输入（最短3个字符，最长20个字符）（！");
        String regex = "^[a-z0-9A-Z]+$";
        if (!adminPermissionParameter.getNewpassword().matches(regex)) return ResultUtil.errorWithMessage("密码只包含数字和英文,其他字符不能输入！");
        if (AdminUserRepository.findById(AdminUser.getId()).get().getPassword().equals(new Md5Hash(adminPermissionParameter.getPassword()).toHex())) {
            AdminUser.setPassword(new Md5Hash(adminPermissionParameter.getNewpassword()).toHex());
            AdminUser.setIschange(1);
            AdminUserRepository.save(AdminUser);
            return ResultUtil.ok();
        } else {
            return ResultUtil.errorWithMessage("原密码错误！");
        }
    }

    @Override
    public Result setting(AdminPermissionParameter adminPermissionParameter) {
        return ResultUtil.okWithData(settingRepository.findByType(adminPermissionParameter.getType()).get(0));
    }

    @Override
    public Result settingSud(AdminPermissionParameter adminPermissionParameter) {
        if (adminPermissionParameter.getType() == 1) {
            if (StringUtils.isBlank(adminPermissionParameter.getValue())) return ResultUtil.errorWithMessage("价格系数不能为空！");
            if (!adminPermissionParameter.getValue().matches("^(([1-9]\\d{0,9})|0)(\\.\\d{1,2})?$"))
                return ResultUtil.errorWithMessage("价格系数只能是两位以内小数或整数！");
            Setting setting = settingRepository.findByType(adminPermissionParameter.getType()).get(0);
            setting.setValue(BigDecimal.valueOf(Double.parseDouble(adminPermissionParameter.getValue())));
            settingRepository.save(setting);
            return ResultUtil.ok();
        } else if (adminPermissionParameter.getType() == 2) {
            if (StringUtils.isBlank(adminPermissionParameter.getAccepttime())) return ResultUtil.errorWithMessage("接单时间不能为空！");
            if (StringUtils.isBlank(adminPermissionParameter.getPricetime())) return ResultUtil.errorWithMessage("询价时间不能为空！");
            String regex = "^[0-9]+$";
            if (!adminPermissionParameter.getAccepttime().matches(regex)) return ResultUtil.errorWithMessage("接单时间只能是数字！");
            if (!adminPermissionParameter.getPricetime().matches(regex)) return ResultUtil.errorWithMessage("询价时间只能是数字！");
            Setting setting2 = settingRepository.findByType(2).get(0);
            setting2.setValue(new BigDecimal(adminPermissionParameter.getPricetime()));
            settingRepository.save(setting2);
            Setting setting3 = settingRepository.findByType(3).get(0);
            setting3.setValue(new BigDecimal(adminPermissionParameter.getAccepttime()));
            settingRepository.save(setting2);
        }
        return ResultUtil.ok();
    }

    @Override
    public Result upload(MultipartFile file, AdminPermissionParameter adminPermissionParameter) {
        String fileName = adminPermissionParameter.getResourceid() + "-" + file.getOriginalFilename();
        String path = uploadPath + fileName;
        File dest = new File(path);
        try {
            file.transferTo(dest);
            Resource resource = resourceRepository.findById(adminPermissionParameter.getResourceid()).get();
            resource.setFile(fileName);
            resourceRepository.save(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResultUtil.errorWithMessage("文件上传失败！");
        } finally {
            return ResultUtil.okWithData(fileName);
        }
    }

    @Override
    public void deleteRole(Role role) {
        AdminUserRepository.findByRole(role).forEach(e -> {
            e.setRole(null);
            AdminUserRepository.save(e);
        });
        role2PermissionRepository.deleteAllByRole(role);
        roleRepository.delete(role);
    }

    public void deleteAdminUser(AdminUser AdminUser) {
        AdminUserRepository.delete(AdminUser);
    }
}
