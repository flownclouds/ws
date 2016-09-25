package com.saltyfish.domain.repository;

import com.saltyfish.domain.entity.superbean.ConservationEntity;
import com.saltyfish.domain.entity.unit.TownEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by weck on 16/9/24.
 */
@Repository
public interface ConservationRepository extends MongoRepository<ConservationEntity, String> {
    ConservationEntity findById(@Param("id") String projectId);

    Page<ConservationEntity> findByIsDeleteAndTownEntityIn(@Param("is_delete") int isDelete,
                                                           @Param("townEntity") List<TownEntity> towns,
                                                           Pageable pageable);

    Page<ConservationEntity> findByIsDeleteAndCategoryAndTownEntityIn(@Param("is_delete") int isDelete,
                                                                      @Param("category") String category,
                                                                      @Param("townEntity") List<TownEntity> towns,
                                                                      Pageable pageable);

    List<ConservationEntity> findByIsDeleteAndCategoryAndTownEntityIn(@Param("is_delete") int isDelete,
                                                                      @Param("category") String category,
                                                                      @Param("townEntity") List<TownEntity> towns);
}
