package com.saltyfish.domain.entity.project.device;

import com.saltyfish.domain.entity.base.SuperBean;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by weck on 16/8/30.
 * <p>
 * 变压器
 */
@Entity
@Table(name = "transformer", catalog = "exciting")
public class TransformerEntity extends SuperBean {

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
