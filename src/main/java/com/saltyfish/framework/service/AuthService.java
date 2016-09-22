package com.saltyfish.framework.service;

import com.saltyfish.common.utils.PasswordEncode;
import com.saltyfish.domain.entity.auth.UserEntity;
import com.saltyfish.domain.entity.other.NotificationEntity;
import com.saltyfish.domain.entity.project.WaterConservationEntity;
import com.saltyfish.domain.entity.unit.TownEntity;
import com.saltyfish.domain.repository.auth.RoleRepository;
import com.saltyfish.domain.repository.auth.UserRepository;
import com.saltyfish.domain.repository.other.NotificationRepository;
import com.saltyfish.domain.repository.project.WaterConservationRepository;
import com.saltyfish.domain.repository.unit.GroupRepository;
import com.saltyfish.domain.repository.unit.TownRepository;
import com.saltyfish.domain.repository.unit.VillageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by weck on 16/9/3.
 * <p>
 * authentication and authorization service
 */
@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TownRepository townRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private VillageRepository villageRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private WaterConservationRepository waterConservationRepository;


    /**
     * 检测是否管理员
     *
     * @param userId 用户id
     * @return bool
     */
    public Boolean checkAdmin(Integer userId) {
        return userRepository.findById(userId).getRole().getName().equals("admin");
    }

    /**
     * 根据id检测用户是否超级管理员
     *
     * @param id 用户id
     * @return bool
     */
    public Boolean checkSuperAdmin(Integer id) {
        return userRepository.findById(id).getIsSuperAdmin() == 1;
    }

    /**
     * 判断用户是否登录
     *
     * @param id    用户id
     * @param token 登录token
     * @return bool
     */
    public Boolean checkLogin(Integer id, String token) {
        return token.equals(userRepository.findById(id).getToken());
    }

    /**
     * 检测密码
     *
     * @param userEntity 用户对象
     * @param password   密码
     * @return bool
     */
    public Boolean verifyPassword(UserEntity userEntity, String password) {
        return PasswordEncode.verify(userEntity.getPassword(), password, userEntity.getSalt());
    }

    /**
     * 检测密码
     *
     * @param userId   用户id
     * @param password 密码
     * @return bool
     */
    public Boolean verifyPassword(Integer userId, String password) {
        UserEntity userEntity = userRepository.findById(userId);
        return PasswordEncode.verify(userEntity.getPassword(), password, userEntity.getSalt());
    }


    /**
     * 判断用户修改公告的权限
     *
     * @param notificationId 公告id
     * @param userId         用户id
     * @return bool
     */
    public Boolean checkModifyNotificationAccess(Integer notificationId, Integer userId) {
        NotificationEntity notificationEntity = notificationRepository.findById(notificationId);
        UserEntity userEntity = userRepository.findById(userId);
        return !((notificationEntity.getCategory() == 1 && !checkSuperAdmin(userId))
                || (notificationEntity.getCategory() == 2 &&
                !userEntity.getCounty().getId().equals(notificationEntity.getUser().getCounty().getId())));
    }

    /**
     * 判断用户对乡镇的权限
     *
     * @param userId 用户id
     * @param townId 乡镇id
     * @return bool
     */
    public Boolean checkUserTownAccess(Integer userId, Integer townId) {
        UserEntity userEntity = userRepository.findById(userId);
        TownEntity townEntity = townRepository.findById(townId);
        if (checkAdmin(userId)) {
            return userEntity.getCounty().getId().equals(townEntity.getCounty().getId());
        }
        return userEntity.getTowns().contains(townEntity);
    }

    /**
     * 检测用户是否guest
     *
     * @param userId 用户id
     * @return bool
     */
    public Boolean checkGuest(Integer userId) {
        return userRepository.findById(userId).getRole().getName().equals("guest");
    }

    /**
     * 检测城乡包含关系
     *
     * @param townId    乡镇id
     * @param villageId 村id
     * @param groupId   组id
     * @return bool
     */
    public Boolean unitContainingCheck(Integer townId, Integer villageId, Integer groupId) {
        if (groupId != null) {
            return villageRepository.findById(villageId).getTown().getId().equals(townId) && groupRepository.findById(groupId).getVillage().getId().equals(villageId);
        }
        return villageRepository.findById(villageId).getTown().getId().equals(townId);
    }

    /**
     * 检测工程所在乡镇用户是否有权限
     *
     * @param userId
     * @param projectId
     * @return
     */
    public boolean checkUserProjectTownAccess(Integer userId, Integer projectId) {
        UserEntity user = userRepository.findById(userId);
        WaterConservationEntity project = waterConservationRepository.findById(projectId);
        if (checkAdmin(userId)) {
            return user.getCounty().getId().equals(project.getTown().getCounty().getId());
        }
        return user.getTowns().contains(project.getTown());
    }
}
