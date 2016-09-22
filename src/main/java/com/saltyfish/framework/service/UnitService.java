package com.saltyfish.framework.service;

import com.saltyfish.domain.entity.auth.UserEntity;
import com.saltyfish.domain.entity.location.LocationEntity;
import com.saltyfish.domain.entity.unit.CountyEntity;
import com.saltyfish.domain.entity.unit.GroupEntity;
import com.saltyfish.domain.entity.unit.TownEntity;
import com.saltyfish.domain.entity.unit.VillageEntity;
import com.saltyfish.domain.repository.LocationRepository;
import com.saltyfish.domain.repository.auth.UserRepository;
import com.saltyfish.domain.repository.unit.CountyRepository;
import com.saltyfish.domain.repository.unit.GroupRepository;
import com.saltyfish.domain.repository.unit.TownRepository;
import com.saltyfish.domain.repository.unit.VillageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
    private LocationRepository locationRepository;

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
        Timestamp time = new Timestamp(timeStamp);
        LocationEntity location = new LocationEntity();
        location.setCreateTime(time);
        location.setUpdateTime(time);
        location.setIsDelete(0);
        location.setLongitude(longitude);
        location.setLatitude(latitude);
        locationRepository.save(location);
        CountyEntity county = new CountyEntity();
        county.setCreateTime(time);
        county.setIsDelete(0);
        county.setUpdateTime(time);
        county.setIsActive(1);
        county.setName(countyName);
        county.setLocation(location);
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
        Timestamp time = new Timestamp(timeStamp);
        CountyEntity county = countyRepository.findById(countyId);
        if (!county.getLocation().getLatitude().equals(latitude) || !county.getLocation().getLongitude().equals(longitude)) {
            locationRepository.delete(county.getLocation());
            LocationEntity location = new LocationEntity();
            location.setCreateTime(time);
            location.setUpdateTime(time);
            location.setIsDelete(0);
            location.setLongitude(longitude);
            location.setLatitude(latitude);
            locationRepository.save(location);
            county.setLocation(location);
        }
        county.setUpdateTime(time);
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
        Timestamp time = new Timestamp(timeStamp);
        town.setName(townName);
        town.setCreateTime(time);
        town.setUpdateTime(time);
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
        Timestamp time = new Timestamp(timeStamp);
        town.setName(townName);
        town.setUpdateTime(time);
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
        Timestamp time = new Timestamp(timeStamp);
        group.setName(groupName);
        group.setIsActive(1);
        group.setCounty(user.getCounty());
        group.setCreateTime(time);
        group.setUpdateTime(time);
        group.setIsDelete(0);
        group.setTown(village.getTown());
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
        Timestamp time = new Timestamp(timeStamp);
        village.setCounty(user.getCounty());
        village.setName(villageName);
        village.setTown(townRepository.findById(townId));
        village.setUpdateTime(time);
        village.setIsActive(1);
        village.setCreateTime(time);
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
        Timestamp time = new Timestamp(timeStamp);
        VillageEntity village = villageRepository.findById(villageId);
        village.setName(villageName);
        village.setUpdateTime(time);
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
        Timestamp time = new Timestamp(timeStamp);
        GroupEntity group = groupRepository.findById(groupId);
        group.setName(groupName);
        group.setUpdateTime(time);
        groupRepository.save(group);
    }

    /**
     * 获取用户权限范围内的乡镇id集合
     *
     * @param userId 用户id
     * @return 乡镇id集合
     */
    public List<Integer> getAccessedTownIds(Integer userId) {
        List<TownEntity> towns = getAccessedTowns(userId);
        List<Integer> townIds = new ArrayList<>();
        Iterator<TownEntity> it = towns.iterator();
        while (it.hasNext()) {
            townIds.add(it.next().getId());
        }
        return townIds;
    }
}
