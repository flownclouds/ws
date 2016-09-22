package com.saltyfish.domain.repository;

import com.saltyfish.domain.entity.location.LocationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by weck on 16/9/4.
 */
@Repository
public interface LocationRepository extends CrudRepository<LocationEntity, Integer> {
}
