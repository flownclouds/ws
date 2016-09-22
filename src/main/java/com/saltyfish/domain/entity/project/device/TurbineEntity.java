package com.saltyfish.domain.entity.project.device;

import com.saltyfish.domain.entity.base.SuperBean;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by weck on 16/8/31.
 * <p>
 * 水轮机
 */

@Entity
@Table(name = "turbine", catalog = "exciting")
public class TurbineEntity extends SuperBean {

    private static final Long serialVersionUID = -975132493522824424L;

    private String model;               //型号

    private String count;               //台数

    private String turnsOrFlow;              //转数/流量

    private String factoryDate;          //出厂年月

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getTurnsOrFlow() {
        return turnsOrFlow;
    }

    public void setTurnsOrFlow(String turnsOrFlow) {
        this.turnsOrFlow = turnsOrFlow;
    }

    public String getFactoryDate() {
        return factoryDate;
    }

    public void setFactoryDate(String factoryDate) {
        this.factoryDate = factoryDate;
    }
}
