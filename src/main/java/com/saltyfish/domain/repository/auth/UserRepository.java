package com.saltyfish.domain.repository.auth;

import com.saltyfish.domain.entity.auth.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by weck on 16/8/31.
 * <p>
 * 用户仓库
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findById(@Param("id") Integer id);

    UserEntity findByName(@Param("name") String name);

    Page<UserEntity> findByCountyId(@Param("county_id") Integer countyId, Pageable pageable);

    Page<UserEntity> findByCountyIdAndIsDelete(@Param("county_id") Integer countyId,
                                               @Param("is_delete") Integer isDelete,
                                               Pageable pageable);
}
