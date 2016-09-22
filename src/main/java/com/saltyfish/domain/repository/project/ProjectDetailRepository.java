package com.saltyfish.domain.repository.project;

import com.saltyfish.domain.entity.project.ProjectDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by weck on 16/9/19.
 */

@Repository
public interface ProjectDetailRepository extends JpaRepository<ProjectDetailEntity, Integer> {
    ProjectDetailEntity findById(@Param("id") Integer projectDetailId);
}
