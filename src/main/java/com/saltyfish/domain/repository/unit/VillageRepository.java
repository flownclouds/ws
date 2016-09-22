package com.saltyfish.domain.repository.unit;

import com.saltyfish.domain.entity.unit.VillageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by weck on 16/8/31.
 * <p>
 * 村庄仓库
 */
@Repository
public interface VillageRepository extends JpaRepository<VillageEntity, Integer> {
    List<VillageEntity> findByTownId(@Param("town_id") Integer townId);

    VillageEntity findById(@Param("id") Integer villageId);
}
