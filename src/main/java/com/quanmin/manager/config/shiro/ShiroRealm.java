package com.quanmin.manager.config.shiro;

import com.quanmin.manager.service.AdminPermissionService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
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
//        AdminUser adminUser = (AdminUser) principals.getPrimaryPrincipal();
//        AdminRole adminRole = adminUser.getRole();
//        authorizationInfo.addRole(adminRole.getName());
//        for (AdminRole2Permission p : adminRole.getRole2Permissions()) {
//            authorizationInfo.addStringPermission(p.getPermission().getName());
//        }
        return authorizationInfo;
    }

    //身份认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//        String username = (String) token.getPrincipal();
//        AdminUser adminUser = null;
//        Result result = adminService.findByUsername(username);
//        if (result.getStatus() == 1) user = (User) result.getData();
//        else throw new AuthenticationException();
//        if (user == null) {
//            throw new AuthenticationException();
//        }
//        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), getName());
//        return authenticationInfo;
        return null;
    }
}