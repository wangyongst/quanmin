package com.quanmin.manager.repository;

import com.quanmin.manager.entity.SettingClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryDefinition(domainClass = SettingClass.class, idClass = Integer.class)
public interface SettingClassRepository extends JpaRepository<SettingClass, Integer>, JpaSpecificationExecutor<SettingClass> {
}