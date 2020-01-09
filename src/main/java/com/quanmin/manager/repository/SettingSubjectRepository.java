package com.quanmin.manager.repository;

import com.quanmin.manager.entity.SettingSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryDefinition(domainClass = SettingSubject.class, idClass = Integer.class)
public interface SettingSubjectRepository extends JpaRepository<SettingSubject, Integer> , JpaSpecificationExecutor<SettingSubject> {
}