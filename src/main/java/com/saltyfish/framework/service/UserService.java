package com.saltyfish.framework.service;

import com.saltyfish.common.utils.PasswordEncode;
import com.saltyfish.common.utils.UUIDGenerator;
import com.saltyfish.domain.entity.auth.UserEntity;
import com.saltyfish.domain.entity.unit.CountyEntity;
import com.saltyfish.domain.entity.unit.TownEntity;
import com.saltyfish.domain.repository.auth.RoleRepository;
import com.saltyfish.domain.repository.auth.UserRepository;
import com.saltyfish.domain.repository.unit.CountyRepository;
import com.saltyfish.domain.repository.unit.TownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by weck on 16/9/2.
 * <p>
 * 用户操作
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CountyRepository countyRepository;

    @Autowired
    private TownRepository townRepository;

    /**
     * 根据用户名查找用户
     *
     * @param userName 用户名
     * @return 用户对象
     */
    public UserEntity findByName(String userName) {
        return userRepository.findByName(userName);
    }

    /**
     * 登录设置token
     *
     * @param userEntity 用户对象
     */
    public void setToken(UserEntity userEntity) {
        userEntity.setToken(UUIDGenerator.getUUID());
        userEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        userRepository.save(userEntity);
    }

    /**
     * 添加用户
     *
     * @param userId   操作者用户id
     * @param name     用户名
     * @param password 明文密码
     * @param phone    手机号
     * @param email    邮箱
     * @param realName 真实姓名
     * @param roleId   角色id
     * @param isActive 是否启用
     */
    public void addUser(Integer userId, String name, String password, String phone, String email, String realName, Integer roleId, Integer isActive, Long timeStamp, List<Integer> townIds) {
        UserEntity user = new UserEntity();
        List<TownEntity> towns = new ArrayList<>();
        Iterator<Integer> it = townIds.iterator();
        while (it.hasNext()) {
            TownEntity town = townRepository.findById(it.next());
            towns.add(town);
        }
        Timestamp time = new Timestamp(timeStamp);
        String salt = PasswordEncode.generateSalt();
        user.setSalt(salt);
        user.setCounty(userRepository.findById(userId).getCounty());
        user.setName(name);
        user.setIsActive(isActive);
        user.setCreateTime(time);
        user.setUpdateTime(time);
        user.setEmail(email);
        user.setPassword(PasswordEncode.getHashedPassword(password, salt));
        user.setPhone(phone);
        user.setRealName(realName);
        user.setRole(roleRepository.findById(roleId));
        user.setIsDelete(0);
        user.setTowns(towns);
        userRepository.save(user);
    }

    /**
     * 超级管理员添加管理员
     *
     * @param name      用户名
     * @param password  明文密码
     * @param phone     手机号
     * @param email     邮箱
     * @param realName  真实姓名
     * @param isActive  是否启用
     * @param timeStamp 时间戳
     * @param countyId  所属县id
     */
    public void addAdmin(String name, String password, String phone, String email, String realName, Integer isActive, Long timeStamp, Integer countyId) {
        UserEntity user = new UserEntity();
        CountyEntity county = countyRepository.findById(countyId);
        Timestamp time = new Timestamp(timeStamp);
        String salt = PasswordEncode.generateSalt();
        user.setSalt(salt);
        user.setPassword(PasswordEncode.getHashedPassword(password, salt));
        user.setName(name);
        user.setRealName(realName);
        user.setPhone(phone);
        user.setEmail(email);
        user.setIsDelete(0);
        user.setIsActive(isActive);
        user.setCounty(county);
        user.setCreateTime(time);
        user.setUpdateTime(time);
        user.setRole(roleRepository.findByName("admin"));
        userRepository.save(user);
    }

    /**
     * 管理员修改用户信息
     *
     * @param targetUserId 目标用户id
     * @param timeStamp    时间戳
     * @param roleId       用户id
     * @param townIds      乡镇id
     */
    public void modifyUser(Integer targetUserId, String password, String phone, String email, String realName, Integer isActive, Long timeStamp, Integer roleId, List<Integer> townIds) {
        UserEntity user = userRepository.findById(targetUserId);
        Timestamp time = new Timestamp(timeStamp);
        List<TownEntity> towns = new ArrayList<>();
        Iterator<Integer> it = townIds.iterator();
        while (it.hasNext()) {
            TownEntity town = townRepository.findById(it.next());
            towns.add(town);
        }
        user.setRole(roleRepository.findById(roleId));
        user.setPassword(PasswordEncode.getHashedPassword(password, user.getSalt()));
        user.setPhone(phone);
        user.setEmail(email);
        user.setRealName(realName);
        user.setIsActive(isActive);
        user.setTowns(towns);
        user.setUpdateTime(time);
        userRepository.save(user);
    }

    /**
     * 修改密码
     *
     * @param userId    用户id
     * @param password  新密码
     * @param timeStamp 时间戳
     */
    public void modifyPassword(Integer userId, String password, Long timeStamp) {
        UserEntity user = userRepository.findById(userId);
        String salt = PasswordEncode.generateSalt();
        user.setSalt(salt);
        user.setPassword(PasswordEncode.getHashedPassword(password, salt));
        user.setUpdateTime(new Timestamp(timeStamp));
        userRepository.save(user);
    }

    /**
     * 获取用户列表
     *
     * @param userId 操作者用户id
     * @param page   页码
     * @param size   每页数量
     * @return
     */
    public Page<UserEntity> getUserList(Integer userId, Integer page, Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Pageable pageable = new PageRequest(page - 1, size, sort);
        return userRepository.findByCountyIdAndIsDelete(userRepository.findById(userId).getCounty().getId(), 0, pageable);
    }

    /**
     * 删除用户
     *
     * @param targetUserId 目标用户id
     * @param timeStamp    时间戳
     */
    public void deleteUser(Integer targetUserId, Long timeStamp) {
        UserEntity user = userRepository.findById(targetUserId);
        user.setIsDelete(1);
        user.setUpdateTime(new Timestamp(timeStamp));
        userRepository.save(user);
    }

    /**
     * 登出
     *
     * @param userId 用户id
     */
    public void logout(Integer userId, Long timeStamp) {
        UserEntity user = userRepository.findById(userId);
        user.setToken("");
        user.setUpdateTime(new Timestamp(timeStamp));
        userRepository.save(user);
    }

    /**
     * 用户修改个人信息
     *
     * @param userId    用户id
     * @param name      用户名
     * @param realName  真实姓名
     * @param email     邮箱
     * @param phone     手机
     * @param timeStamp 时间戳
     */
    public void modifyUserInfo(Integer userId, String name, String realName, String email, String phone, Long timeStamp) {
        UserEntity user = userRepository.findById(userId);
        Timestamp time = new Timestamp(timeStamp);
        user.setRealName(realName);
        user.setName(name);
        user.setPhone(phone);
        user.setEmail(email);
        user.setUpdateTime(time);
        userRepository.save(user);
    }
}
