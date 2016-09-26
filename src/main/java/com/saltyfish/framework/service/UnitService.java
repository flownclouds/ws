package com.saltyfish.framework.service;

import com.saltyfish.domain.entity.auth.UserEntity;
import com.saltyfish.domain.entity.superbean.UnitBean;
import com.saltyfish.domain.entity.unit.CountyEntity;
import com.saltyfish.domain.entity.unit.GroupEntity;
import com.saltyfish.domain.entity.unit.TownEntity;
import com.saltyfish.domain.entity.unit.VillageEntity;
import com.saltyfish.domain.repository.auth.UserRepository;
import com.saltyfish.domain.repository.unit.CountyRepository;
import com.saltyfish.domain.repository.unit.GroupRepository;
import com.saltyfish.domain.repository.unit.TownRepository;
import com.saltyfish.domain.repository.unit.VillageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by weck on 16/9/4.
 * <p>
 * 行政单位操作
 */
@Service
public class UnitService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TownRepository townRepository;

    @Autowired
    private CountyRepository countyRepository;

    @Autowired
    private VillageRepository villageRepository;

    @Autowired
    private GroupRepository groupRepository;

    /**
     * 根据用户id获取当前县信息
     *
     * @param userId 用户id
     * @return 县对象
     */
    public CountyEntity getCountyByUserId(Integer userId) {
        return userRepository.findById(userId).getCounty();
    }

    /**
     * 根据用户id获取权限范围内的乡镇信息
     *
     * @param userId 用户id
     * @return 乡镇列表
     */
    public List<TownEntity> getAccessedTowns(Integer userId) {
        UserEntity user = userRepository.findById(userId);
        return user.getTowns();
    }

    /**
     * 新增县
     *
     * @param countyName 县名
     * @param longitude  经度
     * @param latitude   纬度
     * @param timeStamp  时间戳
     */
    public void addCounty(String countyName, String longitude, String latitude, Long timeStamp) {
        CountyEntity county = new CountyEntity();
        county.setCreateTime(timeStamp);
        county.setIsDelete(0);
        county.setUpdateTime(timeStamp);
        county.setIsActive(1);
        county.setName(countyName);
        county.setLatitude(latitude);
        county.setLongitude(longitude);
        countyRepository.save(county);
    }

    /**
     * 修改县
     *
     * @param countyId   当前修改县id
     * @param countyName 县名
     * @param longitude  经度
     * @param latitude   纬度
     * @param timeStamp  时间戳
     */
    public void updateCounty(Integer countyId, String countyName, String longitude, String latitude, Long timeStamp) {
        CountyEntity county = countyRepository.findById(countyId);
        county.setLongitude(longitude);
        county.setLatitude(latitude);
        county.setUpdateTime(timeStamp);
        county.setName(countyName);
        countyRepository.save(county);
    }

    /**
     * 获取县的列表
     *
     * @return 县列表
     */
    public List<CountyEntity> getCounties() {
        return countyRepository.findByIsDelete(0);
    }

    /**
     * 根据乡镇获取村list
     *
     * @param townId 乡镇id
     * @return 村庄列表
     */
    public List<VillageEntity> getVillagesByTown(Integer townId) {
        return villageRepository.findByTownId(townId);
    }

    /**
     * 根据id获取村庄
     *
     * @param villageId 村庄id
     * @return 村庄对象
     */
    public VillageEntity getVillageById(Integer villageId) {
        return villageRepository.findById(villageId);
    }

    /**
     * 根据村庄获取组list
     *
     * @param villageId 村庄id
     * @return 组列表
     */
    public List<GroupEntity> getGroupsByVillage(Integer villageId) {
        return groupRepository.findByVillageId(villageId);
    }

    /**
     * 添加乡镇
     *
     * @param userId    用户id
     * @param townName  乡镇名称
     * @param timeStamp 时间戳
     */
    public void addTown(Integer userId, String townName, Long timeStamp) {
        TownEntity town = new TownEntity();
        town.setName(townName);
        town.setCreateTime(timeStamp);
        town.setUpdateTime(timeStamp);
        town.setIsActive(1);
        town.setIsDelete(0);
        town.setCounty(userRepository.findById(userId).getCounty());
        townRepository.save(town);
    }

    /**
     * 修改乡镇
     *
     * @param townName  乡镇名称
     * @param townId    乡镇id
     * @param timeStamp 时间戳
     */
    public void modifyTown(String townName, Integer townId, Long timeStamp) {
        TownEntity town = townRepository.findById(townId);
        town.setName(townName);
        town.setUpdateTime(timeStamp);
        townRepository.save(town);
    }

    /**
     * 添加组
     *
     * @param userId    用户id
     * @param groupName 组名
     * @param timeStamp 时间戳
     * @param villageId 村庄id
     */
    public void addGroup(Integer userId, String groupName, Long timeStamp, Integer villageId) {
        UserEntity user = userRepository.findById(userId);
        VillageEntity village = villageRepository.findById(villageId);
        GroupEntity group = new GroupEntity();
        group.setName(groupName);
        group.setIsActive(1);
        group.setCreateTime(timeStamp);
        group.setUpdateTime(timeStamp);
        group.setIsDelete(0);
        group.setVillage(village);
        groupRepository.save(group);
    }

    /**
     * 添加村庄
     *
     * @param userId      用户id
     * @param townId      乡镇id
     * @param villageName 村庄名称
     * @param timeStamp   时间戳
     */
    public void addVillage(Integer userId, Integer townId, String villageName, Long timeStamp) {
        UserEntity user = userRepository.findById(userId);
        VillageEntity village = new VillageEntity();
        village.setName(villageName);
        village.setTown(townRepository.findById(townId));
        village.setUpdateTime(timeStamp);
        village.setIsActive(1);
        village.setCreateTime(timeStamp);
        village.setIsDelete(0);
        villageRepository.save(village);
    }

    /**
     * 修改村庄信息
     *
     * @param villageName 村庄名称
     * @param timeStamp   时间戳
     * @param villageId   村庄id
     */
    public void modifyVillage(String villageName, Long timeStamp, Integer villageId) {
        VillageEntity village = villageRepository.findById(villageId);
        village.setName(villageName);
        village.setUpdateTime(timeStamp);
        villageRepository.save(village);
    }


    /**
     * 根据id获取组
     *
     * @param groupId 组id
     * @return 组对象
     */
    public GroupEntity getByGroupId(Integer groupId) {
        return groupRepository.findById(groupId);
    }

    /**
     * 修改组
     *
     * @param groupName 组名称
     * @param groupId   组id
     * @param timeStamp 时间戳
     */
    public void modifyGroup(String groupName, Integer groupId, Long timeStamp) {
        Date time = new Date(timeStamp);
        GroupEntity group = groupRepository.findById(groupId);
        group.setName(groupName);
        group.setUpdateTime(timeStamp);
        groupRepository.save(group);
    }

    /**
     * 获取用户权限范围内的乡镇id集合
     *
     * @param userId 用户id
     * @return 乡镇id集合
     */
    public List<Integer> getAccessedTownIds(Integer userId) {
        UserEntity user = userRepository.findById(userId);
        List<Integer> townIds = new ArrayList<>();
        List<TownEntity> towns = user.getTowns();
        townIds.addAll(towns.stream().map(UnitBean::getId).collect(Collectors.toList()));
        return townIds;
    }

    public List<TownEntity> getTowns(Integer userId) {
        return townRepository.findByCountyId(userRepository.findById(userId).getCounty().getId());
    }
}
