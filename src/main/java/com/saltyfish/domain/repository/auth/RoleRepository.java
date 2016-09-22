package com.saltyfish.domain.repository.auth;

import com.saltyfish.domain.entity.auth.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by weck on 16/8/31.
 * <p>
 * 角色仓库
 */
@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    RoleEntity findByName(@Param("name") String name);

    RoleEntity findById(@Param("id") Integer roleId);
}
