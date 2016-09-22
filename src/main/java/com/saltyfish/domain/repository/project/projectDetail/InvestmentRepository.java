package com.saltyfish.domain.repository.project.projectDetail;

import com.saltyfish.domain.entity.project.projectdetail.InvestmentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by weck on 16/9/19.
 */
@Repository
public interface InvestmentRepository extends CrudRepository<InvestmentEntity, Integer> {
}
