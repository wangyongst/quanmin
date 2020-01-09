package com.quanmin.manager.repository;

import com.quanmin.manager.entity.VideoCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryDefinition(domainClass = VideoCourse.class, idClass = Integer.class)
public interface VideoCourseRepository extends JpaRepository<VideoCourse, Integer> , JpaSpecificationExecutor<VideoCourse> {

}