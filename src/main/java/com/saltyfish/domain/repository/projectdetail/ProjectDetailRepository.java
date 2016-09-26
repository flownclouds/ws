package com.saltyfish.domain.repository.projectdetail;

import com.saltyfish.domain.entity.projectdetail.ProjectDetailEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by weck on 16/9/26.
 */

@Repository
public interface ProjectDetailRepository extends MongoRepository<ProjectDetailEntity, String> {

    ProjectDetailEntity findById(@Param("id") String projectDetailId);

    Page<ProjectDetailEntity> findByIsDelete(@Param("is_delete") int isDelete,
                                             Pageable pageable);
}
