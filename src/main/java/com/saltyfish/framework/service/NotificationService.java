package com.saltyfish.framework.service;

import com.saltyfish.domain.entity.auth.UserEntity;
import com.saltyfish.domain.entity.other.NotificationEntity;
import com.saltyfish.domain.repository.auth.UserRepository;
import com.saltyfish.domain.repository.other.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * Created by weck on 16/9/2.
 * <p>
 * 公告操作
 */
@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private UserRepository userRepository;

    /**
     * 分页查询首页公告
     *
     * @param page 页码
     * @param size 每页数量
     * @return 公告page
     */
    public Page<NotificationEntity> getIndexNotifications(Integer page, Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        Pageable pageable = new PageRequest(page - 1, size, sort);
        return notificationRepository.findByIsDeleteAndCategory(0, 1, pageable);
    }

    /**
     * 分页查询县公告
     *
     * @param page   页码
     * @param size   每页数量
     * @param userId 用户id
     * @return 公告page
     */
    public Page<NotificationEntity> getCountyNotifications(Integer page, Integer size, Integer userId) {
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        Pageable pageable = new PageRequest(page - 1, size, sort);
        return notificationRepository.findByCountyIdAndIsDeleteAndCategory(
                userRepository.findById(userId).getCounty().getId(), 0, 2, pageable);
    }

    /**
     * 新增公告
     *
     * @param title     标题
     * @param content   内容
     * @param timeStamp 时间戳
     * @param userId    用户id
     * @param category  类别
     */
    public void addNotification(String title, String content, Long timeStamp, Integer userId, Integer category) {
        UserEntity userEntity = userRepository.findById(userId);
        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setCreateTime(new Timestamp(timeStamp));
        notificationEntity.setUpdateTime(new Timestamp(timeStamp));
        notificationEntity.setCategory(category);
        if (category == 2) {
            notificationEntity.setCounty(userEntity.getCounty());
        }
        notificationEntity.setContent(content);
        notificationEntity.setUser(userEntity);
        notificationEntity.setTitle(title);
        notificationEntity.setIsDelete(0);
        notificationRepository.save(notificationEntity);
    }

    /**
     * 更新公告
     *
     * @param id        公告id
     * @param content   内容
     * @param timeStamp 时间戳
     * @param title     标题
     * @param userId    用户id
     */
    public void updateNotification(Integer id, String content, Long timeStamp, String title, Integer userId) {
        UserEntity userEntity = userRepository.findById(userId);
        NotificationEntity notification = notificationRepository.findById(id);
        notification.setTitle(title);
        notification.setContent(content);
        notification.setUpdateTime(new Timestamp(timeStamp));
        notification.setUser(userEntity);
        notificationRepository.save(notification);
    }


    /**
     * 删除公告
     *
     * @param notificationId 公告id
     * @param userId         用户id
     * @param timeStamp      时间戳
     */
    public void deleteNotification(Integer notificationId, Integer userId, Long timeStamp) {
        UserEntity userEntity = userRepository.findById(userId);
        NotificationEntity notification = notificationRepository.findById(notificationId);
        notification.setIsDelete(1);
        notification.setUpdateTime(new Timestamp(timeStamp));
        notification.setUser(userEntity);
        notificationRepository.save(notification);
    }
}
