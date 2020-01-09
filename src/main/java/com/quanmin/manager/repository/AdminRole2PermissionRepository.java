package com.quanmin.manager.repository;

import com.quanmin.manager.entity.AdminRole;
import com.quanmin.manager.entity.AdminRole2Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryDefinition(domainClass = AdminRole2Permission.class, idClass = Integer.class)
public interface AdminRole2PermissionRepository extends JpaRepository<AdminRole2Permission, Integer> , JpaSpecificationExecutor<AdminRole2Permission>{

        void deleteAllByRole(AdminRole adminRole);
}