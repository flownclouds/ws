package com.saltyfish.domain.repository.conservation;

import com.saltyfish.domain.entity.conservation.ChannelEntity;
import com.saltyfish.domain.entity.conservation.PondEntity;
import com.saltyfish.domain.entity.unit.TownEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by weck on 16/9/8.
 */

@Repository
public interface PondRepository extends MongoRepository<PondEntity, String> {
    PondEntity findById(@Param("id") String projectId);
    List<PondEntity> findByIsDeleteAndTownEntityIn(@Param("is_delete") int isDelete,
                                                      @Param("townEntity") List<TownEntity> towns);

    List<PondEntity> findByIsDeleteAndTownEntity(@Param("is_delete") int isDelete,
                                                 @Param("townEntity") TownEntity town);
}
