package com.saltyfish.domain.repository.project;

import com.saltyfish.domain.entity.project.WaterConservationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by weck on 16/9/8.
 */
@Repository
public interface WaterConservationRepository extends JpaRepository<WaterConservationEntity, Integer> {
    Page<WaterConservationEntity> findByCategoryAndIsDeleteAndTownIdIn(@Param("category") String category,
                                                                       @Param("is_delete") Integer isDelete,
                                                                       @Param("town_id") List<Integer> townIds,
                                                                       Pageable pageable);

    Page<WaterConservationEntity> findByCategoryAndTownIdAndIsDelete(@Param("category") String category,
                                                                     @Param("town_id") Integer townId,
                                                                     @Param("is_delete") Integer isDelete,
                                                                     Pageable pageable);

    Page<WaterConservationEntity> findByCategoryAndVillageIdAndIsDelete(@Param("category") String category,
                                                                        @Param("village_id") Integer villageId,
                                                                        @Param("is_delete") Integer isDelete,
                                                                        Pageable pageable);

    Page<WaterConservationEntity> findByCategoryAndGroupIdAndIsDelete(@Param("category") String category,
                                                                      @Param("group_id") Integer groupId,
                                                                      @Param("is_delete") Integer isDelete,
                                                                      Pageable pageable);

    Page<WaterConservationEntity> findByManageModelAndCategoryAndIsDeleteAndTownIdIn(@Param("manage_model") String manageModel,
                                                                                     @Param("category") String category,
                                                                                     @Param("is_delete") Integer isDelete,
                                                                                     @Param("town_id") List<Integer> townIds,
                                                                                     Pageable pageable);

    Page<WaterConservationEntity> findByManageModelAndCategoryAndTownIdAndIsDelete(@Param("manage_model") String manageModel,
                                                                                   @Param("category") String category,
                                                                                   @Param("town_id") Integer townId,
                                                                                   @Param("is_delete") Integer isDelete,
                                                                                   Pageable pageable);

    Page<WaterConservationEntity> findByManageModelAndCategoryAndVillageIdAndIsDelete(@Param("manage_model") String manageModel,
                                                                                      @Param("category") String category,
                                                                                      @Param("village_id") Integer villageId,
                                                                                      @Param("is_delete") Integer isDelete,
                                                                                      Pageable pageable);

    Page<WaterConservationEntity> findByManageModelAndCategoryAndGroupIdAndIsDelete(@Param("manage_model") String manageModel,
                                                                                    @Param("category") String category,
                                                                                    @Param("group_id") Integer groupId,
                                                                                    @Param("is_delete") Integer isDelete,
                                                                                    Pageable pageable);

    Page<WaterConservationEntity> findBySituationAndCategoryAndIsDeleteAndTownIdIn(@Param("situation") String situation,
                                                                                   @Param("category") String category,
                                                                                   @Param("is_delete") Integer isDelete,
                                                                                   @Param("town_id") List<Integer> townIds,
                                                                                   Pageable pageable);

    Page<WaterConservationEntity> findBySituationAndCategoryAndTownIdAndIsDelete(@Param("situation") String situation,
                                                                                 @Param("category") String category,
                                                                                 @Param("town_id") Integer townId,
                                                                                 @Param("is_delete") Integer isDelete,
                                                                                 Pageable pageable);

    Page<WaterConservationEntity> findBySituationAndCategoryAndVillageIdAndIsDelete(@Param("situation") String situation,
                                                                                    @Param("category") String category,
                                                                                    @Param("village_id") Integer villageId,
                                                                                    @Param("is_delete") Integer isDelete,
                                                                                    Pageable pageable);

    Page<WaterConservationEntity> findBySituationAndCategoryAndGroupIdAndIsDelete(@Param("situation") String situation,
                                                                                  @Param("category") String category,
                                                                                  @Param("group_id") Integer groupId,
                                                                                  @Param("is_delete") Integer isDelete,
                                                                                  Pageable pageable);

    Page<WaterConservationEntity> findByManageModelAndSituationAndCategoryAndIsDeleteAndTownIdIn(@Param("manage_model") String manageModel,
                                                                                                 @Param("situation") String situation,
                                                                                                 @Param("category") String category,
                                                                                                 @Param("is_delete") Integer isDelete,
                                                                                                 @Param("town_id") List<Integer> townIds,
                                                                                                 Pageable pageable);

    Page<WaterConservationEntity> findByManageModelAndSituationAndCategoryAndTownIdAndIsDelete(@Param("manage_model") String manageModel,
                                                                                               @Param("situation") String situation,
                                                                                               @Param("category") String category,
                                                                                               @Param("town_id") Integer townId,
                                                                                               @Param("is_delete") Integer isDelete,
                                                                                               Pageable pageable);

    Page<WaterConservationEntity> findByManageModelAndSituationAndCategoryAndVillageIdAndIsDelete(@Param("manage_model") String manageModel,
                                                                                                  @Param("situation") String situation,
                                                                                                  @Param("category") String category,
                                                                                                  @Param("village_id") Integer villageId,
                                                                                                  @Param("is_delete") Integer isDelete,
                                                                                                  Pageable pageable);

    Page<WaterConservationEntity> findByManageModelAndSituationAndCategoryAndGroupIdAndIsDelete(@Param("manage_model") String manageModel,
                                                                                                @Param("situation") String situation,
                                                                                                @Param("category") String category,
                                                                                                @Param("group_id") Integer groupId,
                                                                                                @Param("is_delete") Integer isDelete,
                                                                                                Pageable pageable);

    Page<WaterConservationEntity> findByCategoryAndIsDeleteAndTownIdInAndUpdateTimeBetween(@Param("category") String category,
                                                                                           @Param("is_delete") Integer isDelete,
                                                                                           @Param("town_id") List<Integer> townIds,
                                                                                           @Param("update_time") Timestamp startTime,
                                                                                           @Param("update_time") Timestamp endTime,
                                                                                           Pageable pageable);

    Page<WaterConservationEntity> findByCategoryAndIsDeleteAndTownIdInAndCodeLike(@Param("category") String category,
                                                                                  @Param("is_delete") Integer isDelete,
                                                                                  @Param("town_id") List<Integer> townIds,
                                                                                  @Param("code") String code,
                                                                                  Pageable pageable);

    Page<WaterConservationEntity> findByCategoryAndIsDeleteAndTownIdInAndNameLike(@Param("category") String category,
                                                                                  @Param("is_delete") Integer isDelete,
                                                                                  @Param("town_id") List<Integer> townIds,
                                                                                  @Param("name") String name,
                                                                                  Pageable pageable);

    Page<WaterConservationEntity> findByIsDeleteAndTownIdIn(@Param("is_delete") Integer isDelete,
                                                            @Param("town_id") List<Integer> townIds,
                                                            Pageable pageable);

    WaterConservationEntity findById(@Param("id") Integer projectId);

    List<WaterConservationEntity> findByCategoryAndIsDeleteAndTownIdIn(@Param("category") String category,
                                                                       @Param("is_delete") Integer isDelete,
                                                                       @Param("town_id") List<Integer> accessedTownIds);
}
