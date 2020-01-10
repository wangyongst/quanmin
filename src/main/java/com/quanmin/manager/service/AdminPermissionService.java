package com.quanmin.manager.service;

import com.quanmin.manager.model.AdminPermissionParameter;
import com.quanmin.manager.utils.result.Result;

public interface AdminPermissionService {

    Result me();

    Result findByAdminUsername(String username);

    Result adminUserList(AdminPermissionParameter adminPermissionParameter);

    Result adminRoleList(AdminPermissionParameter adminPermissionParameter);

    Result adminUser(AdminPermissionParameter adminPermissionParameter);

    Result adminUserSud(AdminPermissionParameter adminPermissionParameter);

    Result adminRole(AdminPermissionParameter adminPermissionParameter);

    Result adminRoleSud(AdminPermissionParameter adminPermissionParameter);

    Result permissionList(AdminPermissionParameter adminPermissionParameter);

    Result changeAdminPassword(AdminPermissionParameter adminPermissionParameter);
}
