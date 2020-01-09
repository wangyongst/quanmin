package com.quanmin.manager.repository;

import com.quanmin.manager.entity.VideoLession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryDefinition(domainClass = VideoLession.class, idClass = Integer.class)
public interface VideoLessionRepository extends JpaRepository<VideoLession, Integer>, JpaSpecificationExecutor<VideoLession> {
}