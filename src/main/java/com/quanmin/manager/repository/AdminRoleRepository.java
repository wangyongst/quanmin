package com.quanmin.manager.repository;

import com.quanmin.manager.entity.AdminRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryDefinition(domainClass = AdminRole.class, idClass = Integer.class)
public interface AdminRoleRepository extends JpaRepository<AdminRole, Integer> , JpaSpecificationExecutor<AdminRole> {
}