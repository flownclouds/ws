package com.saltyfish.domain.repository.project.projectDetail;

import com.saltyfish.domain.entity.project.projectdetail.ApprovalEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by weck on 16/9/19.
 */
@Repository
public interface ApprovalRepository extends CrudRepository<ApprovalEntity, Integer> {
    ApprovalEntity findById(@Param("id") Integer approvalId);
}
