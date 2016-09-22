package com.saltyfish.domain.entity.project.conservation;

import com.saltyfish.domain.entity.base.SuperBean;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by weck on 16/8/30.
 * <p>
 * 水塘
 */
@Entity
@Table(name = "pond", catalog = "exciting")
public class PondEntity extends SuperBean {

    private static final Long serialVersionUID = -193848050958205434L;

    private String mainFunction;        //主要功能

    private String lastDredgingTime;     //上次疏浚时间

    private String waterArea;        //水面积

    private String waterCapacity;        //储水量

    private String image;           //照片

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getMainFunction() {
        return mainFunction;
    }

    public void setMainFunction(String mainFunction) {
        this.mainFunction = mainFunction;
    }

    public String getLastDredgingTime() {
        return lastDredgingTime;
    }

    public void setLastDredgingTime(String lastDredgingTime) {
        this.lastDredgingTime = lastDredgingTime;
    }

    public String getWaterArea() {
        return waterArea;
    }

    public void setWaterArea(String waterArea) {
        this.waterArea = waterArea;
    }

    public String getWaterCapacity() {
        return waterCapacity;
    }

    public void setWaterCapacity(String waterCapacity) {
        this.waterCapacity = waterCapacity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
