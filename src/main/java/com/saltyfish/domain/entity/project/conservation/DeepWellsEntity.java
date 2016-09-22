package com.saltyfish.domain.entity.project.conservation;

import com.saltyfish.domain.entity.base.SuperBean;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by weck on 16/8/30.
 * <p>
 * 深水井
 */
@Entity
@Table(name = "deep_wells", catalog = "exciting")
public class DeepWellsEntity extends SuperBean {

    private static final Long serialVersionUID = -7391847456894534L;

    private String irrigateArea;     //灌溉面积

    private String deepPump;        //深水泵

    private String diameter;         //井口直径

    private String depth;            //井深

    private String material;        //井管材质

    private String image;           //照片

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getIrrigateArea() {
        return irrigateArea;
    }

    public void setIrrigateArea(String irrigateArea) {
        this.irrigateArea = irrigateArea;
    }

    public String getDeepPump() {
        return deepPump;
    }

    public void setDeepPump(String deepPump) {
        this.deepPump = deepPump;
    }

    public String getDiameter() {
        return diameter;
    }

    public void setDiameter(String diameter) {
        this.diameter = diameter;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
