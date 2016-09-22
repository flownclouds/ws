package com.saltyfish.domain.repository.unit;

import com.saltyfish.domain.entity.unit.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by weck on 16/8/31.
 * <p>
 * 组仓库
 */
@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, Integer> {
    List<GroupEntity> findByVillageId(@Param("villageId") Integer villageId);

    GroupEntity findById(@Param("id") Integer groupId);
}
