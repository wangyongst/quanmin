package com.quanmin.manager.repository;

import com.quanmin.manager.entity.AdminRole;
import com.quanmin.manager.entity.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryDefinition(domainClass = AdminUser.class, idClass = Integer.class)
public interface AdminUserRepository extends JpaRepository<AdminUser, Integer>, JpaSpecificationExecutor<AdminUser> {

    List<AdminUser> findByUsernameAndPassword(String username, String password);

    List<AdminUser> findByUsername(String username);

    List<AdminUser> findByRole(AdminRole adminRole);
}