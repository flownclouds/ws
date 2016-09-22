package com.saltyfish.domain.entity.project.device;

import com.saltyfish.domain.entity.base.SuperBean;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by weck on 16/8/30.
 * <p>
 * 水泵
 */
@Entity
@Table(name = "pump", catalog = "exciting")
public class PumpEntity extends SuperBean {

    private static final Long serialVersionUID = 975489279582824424L;

    private String count;      //台数

    private String liftOrFlow;     //铭牌扬程/流量

    private String factoryDate;      //出厂年月

    private String model;           //型号

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getLiftOrFlow() {
        return liftOrFlow;
    }

    public void setLiftOrFlow(String liftOrFlow) {
        this.liftOrFlow = liftOrFlow;
    }

    public String getFactoryDate() {
        return factoryDate;
    }

    public void setFactoryDate(String factoryDate) {
        this.factoryDate = factoryDate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
