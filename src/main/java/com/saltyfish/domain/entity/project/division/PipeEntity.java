package com.saltyfish.domain.entity.project.division;

import com.saltyfish.domain.entity.base.SuperBean;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by weck on 16/8/31.
 * <p>
 * 管滴灌管道
 */

@Entity
@Table(name = "pipe", catalog = "exciting")
public class PipeEntity extends SuperBean {

    private static final Long serialVersionUID = -2874592742389725424L;

    private String model;       //管道类型

    private String length;           //长度

    private String diameter;         //直径

    private String material;            //材质

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getDiameter() {
        return diameter;
    }

    public void setDiameter(String diameter) {
        this.diameter = diameter;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}
