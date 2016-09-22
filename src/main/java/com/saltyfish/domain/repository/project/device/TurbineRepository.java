package com.saltyfish.domain.repository.project.device;

import com.saltyfish.domain.entity.project.device.TurbineEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by weck on 16/9/8.
 */
@Repository
public interface TurbineRepository extends CrudRepository<TurbineEntity, Integer> {
}
