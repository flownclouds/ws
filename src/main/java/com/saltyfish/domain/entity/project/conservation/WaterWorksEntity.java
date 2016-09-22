package com.saltyfish.domain.entity.project.conservation;

import com.saltyfish.domain.entity.base.SuperBean;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by weck on 16/8/30.
 * <p>
 * 水厂
 */
@Entity
@Table(name = "waterworks", catalog = "exciting")
public class WaterWorksEntity extends SuperBean {

    private static final Long serialVersionUID = 129845788980745834L;

    private String provideAmount;        //供水能力

    private String waterModel;          //水源类型

    private String haveCleaner;        //有无净化设施

    private String isRegularCheck;     //水质是否定期检测

    private String dayProvideAmount;     //现状日供水量

    private String provideVillageCount;    //现状供水村数

    private String providePopulation;      //现状用水人口

    private String haveProtectArea;        //是否建立水源保护区

    private String image;

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getProvideAmount() {
        return provideAmount;
    }

    public void setProvideAmount(String provideAmount) {
        this.provideAmount = provideAmount;
    }

    public String getWaterModel() {
        return waterModel;
    }

    public void setWaterModel(String waterModel) {
        this.waterModel = waterModel;
    }

    public String getHaveCleaner() {
        return haveCleaner;
    }

    public void setHaveCleaner(String haveCleaner) {
        this.haveCleaner = haveCleaner;
    }

    public String getIsRegularCheck() {
        return isRegularCheck;
    }

    public void setIsRegularCheck(String isRegularCheck) {
        this.isRegularCheck = isRegularCheck;
    }

    public String getDayProvideAmount() {
        return dayProvideAmount;
    }

    public void setDayProvideAmount(String dayProvideAmount) {
        this.dayProvideAmount = dayProvideAmount;
    }

    public String getProvideVillageCount() {
        return provideVillageCount;
    }

    public void setProvideVillageCount(String provideVillageCount) {
        this.provideVillageCount = provideVillageCount;
    }

    public String getProvidePopulation() {
        return providePopulation;
    }

    public void setProvidePopulation(String providePopulation) {
        this.providePopulation = providePopulation;
    }

    public String getHaveProtectArea() {
        return haveProtectArea;
    }

    public void setHaveProtectArea(String haveProtectArea) {
        this.haveProtectArea = haveProtectArea;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
