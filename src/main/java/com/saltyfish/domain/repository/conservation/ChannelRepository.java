package com.saltyfish.domain.repository.conservation;

import com.saltyfish.domain.entity.conservation.ChannelEntity;
import com.saltyfish.domain.entity.unit.TownEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by weck on 16/9/8.
 */
@Repository
public interface ChannelRepository extends MongoRepository<ChannelEntity, String> {
    ChannelEntity findById(@Param("id") String projectId);

    List<ChannelEntity> findByIsDeleteAndTownEntityIn(@Param("is_delete") int isDelete,
                                                      @Param("townEntity") List<TownEntity> towns);

    List<ChannelEntity> findByIsDeleteAndTownEntity(@Param("is_delete") int isDelete,
                                                    @Param("townEntity") TownEntity town);
}
