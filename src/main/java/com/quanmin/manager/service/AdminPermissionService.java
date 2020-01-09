package com.quanmin.manager.service;

import com.quanmin.manager.model.AdminPermissionParameter;
import com.quanmin.manager.utils.result.Result;

public interface AdminPermissionService {

    Result me();

    Result findByUsername(String username);

    Result userList(AdminPermissionParameter adminPermissionParameter);

    Result roleList(AdminPermissionParameter adminPermissionParameter);

    Result user(AdminPermissionParameter adminPermissionParameter);

    Result userSud(AdminPermissionParameter adminPermissionParameter);

    Result role(AdminPermissionParameter adminPermissionParameter);

    Result roleSud(AdminPermissionParameter adminPermissionParameter);

    Result permissionList(AdminPermissionParameter adminPermissionParameter);

    Result changePassword(AdminPermissionParameter adminPermissionParameter);
}
