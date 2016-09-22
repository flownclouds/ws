package com.saltyfish.domain.entity.project.device;

import com.saltyfish.domain.entity.base.SuperBean;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by weck on 16/8/31.
 * <p>
 * 电动机
 */
@Entity
@Table(name = "electric_motor", catalog = "exciting")
public class ElectricMotorEntity extends SuperBean {

    private static final Long serialVersionUID = -1987258246729432244L;

    private String model;           //型号

    private String power;            //单台功率*台数

    private String factoryDate;      //出厂年月

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getFactoryDate() {
        return factoryDate;
    }

    public void setFactoryDate(String factoryDate) {
        this.factoryDate = factoryDate;
    }
}
