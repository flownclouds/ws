package com.saltyfish.domain.repository.conservation;


import com.saltyfish.domain.entity.conservation.AqueductEntity;
import com.saltyfish.domain.entity.unit.TownEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by weck on 16/9/7.
 */
@Repository
public interface AqueductRepository extends MongoRepository<AqueductEntity, String> {
    AqueductEntity findById(@Param("id") String projectId);

    List<AqueductEntity> findByIsDeleteAndTownEntityIn(@Param("is_delete") int isDelete,
                                                       @Param("townEntity") List<TownEntity> towns);

    List<AqueductEntity> findByIsDeleteAndTownEntity(@Param("is_delete") int isDelete,
                                                     @Param("townEntity") TownEntity town);
}
