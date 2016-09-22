package com.saltyfish.domain.repository.unit;

import com.saltyfish.domain.entity.unit.TownEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by weck on 16/8/31.
 * <p>
 * 乡镇仓库
 */
@Repository
public interface TownRepository extends JpaRepository<TownEntity, Integer> {
    List<TownEntity> findByCountyId(@Param("county_id") Integer countyId);

    TownEntity findById(@Param("id") Integer townId);
}
