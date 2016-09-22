package com.saltyfish.domain.repository.project.division;

import com.saltyfish.domain.entity.project.division.PipeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by weck on 16/9/8.
 */
@Repository
public interface PipeRepository extends CrudRepository<PipeEntity, Integer> {
}
