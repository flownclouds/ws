package com.saltyfish.domain.entity.project;

import com.saltyfish.domain.entity.base.BaseBean;
import com.saltyfish.domain.entity.location.LocationEntity;
import com.saltyfish.domain.entity.project.mark.ProjectMarkEntity;
import com.saltyfish.domain.entity.unit.CountyEntity;
import com.saltyfish.domain.entity.unit.GroupEntity;
import com.saltyfish.domain.entity.unit.TownEntity;
import com.saltyfish.domain.entity.unit.VillageEntity;

import javax.persistence.*;

/**
 * Created by weck on 16/8/29.
 * <p>
 * 水利设施
 */
@Entity
@Table(name = "water_conservation", catalog = "exciting")
public class WaterConservationEntity extends BaseBean {
    private static final Long serialVersionUID = -1439134197984525370L;

    @Column(nullable = false)
    private String code;            //编码

    @Column(nullable = false)
    private String name;            //名称

    private String remark;              //备注

    private String manageModel;     //管理模式

    private String situation;       //完好情况

    private String constructTime;       //建设/改造时间

    private String constructUnit;       //建设/改造单位

    private String propertyOwner;       //包含产权人,联系人,联系电话

    private String manager;             //包含管理人,联系人,联系电话

    private String category;        //工程种类,泵站或者什么的

    /*县*/
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "county_id", referencedColumnName = "id", nullable = false)
    private CountyEntity county;
    /*乡镇*/
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "town_id", referencedColumnName = "id", nullable = false)
    private TownEntity town;
    /*组*/
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private GroupEntity group;
    /*村*/
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "village_id", referencedColumnName = "id", nullable = false)
    private VillageEntity village;
    /*经纬度*/
    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private LocationEntity location;
    /*工程标记*/
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "project_mark_id", referencedColumnName = "id", nullable = false)
    private ProjectMarkEntity projectMark;

    public static Long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getManageModel() {
        return manageModel;
    }

    public void setManageModel(String manageModel) {
        this.manageModel = manageModel;
    }

    public ProjectMarkEntity getProjectMark() {
        return projectMark;
    }

    public void setProjectMark(ProjectMarkEntity projectMark) {
        this.projectMark = projectMark;
    }

    public CountyEntity getCounty() {
        return county;
    }

    public void setCounty(CountyEntity county) {
        this.county = county;
    }

    public TownEntity getTown() {
        return town;
    }

    public void setTown(TownEntity town) {
        this.town = town;
    }

    public GroupEntity getGroup() {
        return group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }

    public VillageEntity getVillage() {
        return village;
    }

    public void setVillage(VillageEntity village) {
        this.village = village;
    }

    public LocationEntity getLocation() {
        return location;
    }

    public void setLocation(LocationEntity location) {
        this.location = location;
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
}
