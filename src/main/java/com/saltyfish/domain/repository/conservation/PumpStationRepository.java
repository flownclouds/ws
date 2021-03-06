package com.saltyfish.domain.repository.conservation;

import com.saltyfish.domain.entity.conservation.PumpStationEntity;
import com.saltyfish.domain.entity.unit.TownEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by weck on 16/9/8.
 */
@Repository
public interface PumpStationRepository extends MongoRepository<PumpStationEntity, String> {
    PumpStationEntity findById(@Param("id") String projectId);

    List<PumpStationEntity> findByIsDeleteAndTownEntityIn(@Param("is_delete") int isDelete,
                                                          @Param("townEntity") List<TownEntity> towns);

    List<PumpStationEntity> findByIsDeleteAndTownEntity(@Param("is_delete") int isDelete,
                                                        @Param("townEntity") TownEntity town);
}
