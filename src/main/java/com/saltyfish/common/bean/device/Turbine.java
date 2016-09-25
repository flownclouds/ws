package com.saltyfish.common.bean.device;

import java.io.Serializable;

/**
 * Created by weck on 16/8/31.
 * <p>
 * 水轮机
 */

public class Turbine implements Serializable {

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
