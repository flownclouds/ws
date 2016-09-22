package com.saltyfish.domain.entity.project.conservation;

import com.saltyfish.domain.entity.base.SuperBean;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by weck on 16/8/30.
 * <p>
 * 水闸
 */
@Entity
@Table(name = "sluice", catalog = "exciting")
public class SluiceEntity extends SuperBean {

    private static final Long serialVersionUID = 2934598279259054234L;

    private String watercourseLocation;     //所在河道

    private String model;           //型号

    private String holeCount;          //水闸孔数

    private String door;            //闸门

    private String hoistTonnage;         //单孔启闭机吨位

    //单孔尺寸
    private String holeHeight;       //单孔高

    private String holeWidth;        //单孔宽

    //闸门尺寸
    private String doorHeight;       //闸门高

    private String doorWidth;        //闸门宽

    private String hoistModel;      //启闭机类型

    private String buildingSituation;       //建筑完好情况

    private String doorSituation;       //闸门完好情况

    private String hoistSituation;      //启闭设备完好情况

    private String image;       //照片

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getWatercourseLocation() {
        return watercourseLocation;
    }

    public void setWatercourseLocation(String watercourseLocation) {
        this.watercourseLocation = watercourseLocation;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getHoleCount() {
        return holeCount;
    }

    public void setHoleCount(String holeCount) {
        this.holeCount = holeCount;
    }

    public String getDoor() {
        return door;
    }

    public void setDoor(String door) {
        this.door = door;
    }

    public String getHoistTonnage() {
        return hoistTonnage;
    }

    public void setHoistTonnage(String hoistTonnage) {
        this.hoistTonnage = hoistTonnage;
    }

    public String getHoleHeight() {
        return holeHeight;
    }

    public void setHoleHeight(String holeHeight) {
        this.holeHeight = holeHeight;
    }

    public String getHoleWidth() {
        return holeWidth;
    }

    public void setHoleWidth(String holeWidth) {
        this.holeWidth = holeWidth;
    }

    public String getDoorHeight() {
        return doorHeight;
    }

    public void setDoorHeight(String doorHeight) {
        this.doorHeight = doorHeight;
    }

    public String getDoorWidth() {
        return doorWidth;
    }

    public void setDoorWidth(String doorWidth) {
        this.doorWidth = doorWidth;
    }

    public String getHoistModel() {
        return hoistModel;
    }

    public void setHoistModel(String hoistModel) {
        this.hoistModel = hoistModel;
    }

    public String getBuildingSituation() {
        return buildingSituation;
    }

    public void setBuildingSituation(String buildingSituation) {
        this.buildingSituation = buildingSituation;
    }

    public String getDoorSituation() {
        return doorSituation;
    }

    public void setDoorSituation(String doorSituation) {
        this.doorSituation = doorSituation;
    }

    public String getHoistSituation() {
        return hoistSituation;
    }

    public void setHoistSituation(String hoistSituation) {
        this.hoistSituation = hoistSituation;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
