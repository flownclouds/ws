package com.saltyfish.domain.entity.superbean;

import com.saltyfish.domain.entity.unit.CountyEntity;
import com.saltyfish.domain.entity.unit.GroupEntity;
import com.saltyfish.domain.entity.unit.TownEntity;
import com.saltyfish.domain.entity.unit.VillageEntity;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by weck on 16/9/24.
 */
@MappedSuperclass
@Document(collection = "conservation")
public class ConservationEntity extends BaseBean {

    private static final Long serialVersionUID = -1439134197984525370L;

    @Id
    private String id;

    private String code;            //编码

    private String name;            //名称

    private String remark;              //备注

    private String manageModel;     //管理模式

    private String situation;       //完好情况

    private String constructTime;       //建设/改造时间

    private String constructUnit;       //建设/改造单位

    private String propertyOwner;       //包含产权人,联系人,联系电话

    private String manager;             //包含管理人,联系人,联系电话

    private String category;        //工程种类,泵站或者什么的

    private String longitude;

    private String latitude;

    private TownEntity townEntity;

    private CountyEntity countyEntity;

    private VillageEntity villageEntity;

    private GroupEntity groupEntity;

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getManageModel() {
        return manageModel;
    }

    public void setManageModel(String manageModel) {
        this.manageModel = manageModel;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getConstructTime() {
        return constructTime;
    }

    public void setConstructTime(String constructTime) {
        this.constructTime = constructTime;
    }

    public String getConstructUnit() {
        return constructUnit;
    }

    public void setConstructUnit(String constructUnit) {
        this.constructUnit = constructUnit;
    }

    public String getPropertyOwner() {
        return propertyOwner;
    }

    public void setPropertyOwner(String propertyOwner) {
        this.propertyOwner = propertyOwner;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public TownEntity getTownEntity() {
        return townEntity;
    }

    public void setTownEntity(TownEntity townEntity) {
        this.townEntity = townEntity;
    }

    public CountyEntity getCountyEntity() {
        return countyEntity;
    }

    public void setCountyEntity(CountyEntity countyEntity) {
        this.countyEntity = countyEntity;
    }

    public VillageEntity getVillageEntity() {
        return villageEntity;
    }

    public void setVillageEntity(VillageEntity villageEntity) {
        this.villageEntity = villageEntity;
    }

    public GroupEntity getGroupEntity() {
        return groupEntity;
    }

    public void setGroupEntity(GroupEntity groupEntity) {
        this.groupEntity = groupEntity;
    }
}
