package com.saltyfish.domain.repository.unit;

import com.saltyfish.domain.entity.unit.CountyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by weck on 16/8/31.
 * <p>
 * 县仓库
 */
@Repository
public interface CountyRepository extends JpaRepository<CountyEntity, Integer> {
    CountyEntity findById(@Param("id") Integer countyId);

    List<CountyEntity> findByIsDelete(@Param("isDelete") Integer isDelete);
}
