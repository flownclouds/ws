package com.saltyfish.domain.repository.project.conservation;

import com.saltyfish.domain.entity.project.conservation.PumpStationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by weck on 16/9/8.
 */
@Repository
public interface PumpStationRepository extends CrudRepository<PumpStationEntity, Integer> {
}
