package com.quanmin.manager.config.shiro;

import com.quanmin.manager.entity.AdminRole;
import com.quanmin.manager.entity.AdminRole2Permission;
import com.quanmin.manager.entity.AdminUser;
import com.quanmin.manager.service.AdminPermissionService;
import com.quanmin.manager.utils.result.Result;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private AdminPermissionService adminService;

    //权限信息，包括角色以及权限
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        AdminUser adminUser = (AdminUser) principals.getPrimaryPrincipal();
        AdminRole adminRole = adminUser.getAdminRole();
        authorizationInfo.addRole(adminRole.getName());
        for (AdminRole2Permission p : adminRole.getAdminRole2PermissionSet()) {
            authorizationInfo.addStringPermission(p.getAdminPermission().getName());
        }
        return authorizationInfo;
    }

    //身份认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        AdminUser adminUser = null;
        Result result = adminService.findByAdminUsername(username);
        if (result.getStatus() == 1) adminUser = (AdminUser) result.getData();
        else throw new AuthenticationException();
        if (adminUser == null) {
            throw new AuthenticationException();
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(adminUser, adminUser.getPassword(), getName());
        return authenticationInfo;
    }
}