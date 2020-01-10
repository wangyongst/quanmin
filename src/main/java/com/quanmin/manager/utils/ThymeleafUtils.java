package com.quanmin.manager.utils;

import com.quanmin.manager.entity.AdminRole;
import com.quanmin.manager.entity.AdminRole2Permission;

import java.util.ArrayList;
import java.util.List;

public class ThymeleafUtils {

    public boolean contains(int id, AdminRole adminRole) {
        List<Integer> ids = new ArrayList<>();
        for (AdminRole2Permission r2p : adminRole.getAdminRole2PermissionSet()) {
            ids.add(r2p.getAdminPermission().getId());
        }
        return (ids.contains(id));
    }

    public String contact(String str1, String str2) {
        return str1 + str2;
    }
}
