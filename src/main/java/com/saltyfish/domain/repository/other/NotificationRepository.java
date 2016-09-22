package com.saltyfish.domain.repository.other;

import com.saltyfish.domain.entity.other.NotificationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by weck on 16/8/31.
 * <p>
 * 公告仓库
 */
@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Integer> {

    Page<NotificationEntity> findByIsDeleteAndCategory(@Param("isDelete") Integer isDelete,
                                                       @Param("category") Integer category,
                                                       Pageable pageable);

    Page<NotificationEntity> findByCountyIdAndIsDeleteAndCategory(@Param("countyId") Integer countyId,
                                                                  @Param("isDelete") Integer isDelete,
                                                                  @Param("category") Integer category,
                                                                  Pageable pageable);

    NotificationEntity findById(@Param("id") Integer notificationId);
}
