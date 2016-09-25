package com.saltyfish.common.bean.device;

import java.io.Serializable;

/**
 * Created by weck on 16/8/30.
 * <p>
 * 变压器
 */
public class Transformer implements Serializable {

    private static final Long serialVersionUID = 7842794270759825424L;
    private String model;           //型号
    private String capacity;        //台数乘以单机容量

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }
}
