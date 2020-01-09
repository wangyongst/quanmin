package com.quanmin.manager.repository;

import com.quanmin.manager.entity.AdminPermission;
import com.quanmin.manager.entity.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryDefinition(domainClass = AdminPermission.class, idClass = Integer.class)
public interface AdminPermissionRepository extends JpaRepository<AdminPermission, Integer>, JpaSpecificationExecutor<AdminPermission> {
}