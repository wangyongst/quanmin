package com.quanmin.manager.repository;

import com.quanmin.manager.entity.FrontUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryDefinition(domainClass = FrontUser.class, idClass = Integer.class)
public interface FrontUserRepository extends JpaRepository<FrontUser, Integer>, JpaSpecificationExecutor<FrontUser> {

    List<FrontUser> findByUsernameAndPassword(String username, String password);

    List<FrontUser> findByMobile(String mobile);

}