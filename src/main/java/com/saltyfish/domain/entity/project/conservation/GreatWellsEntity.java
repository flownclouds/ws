package com.saltyfish.domain.entity.project.conservation;

import com.saltyfish.domain.entity.base.SuperBean;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by weck on 16/8/30.
 * <p>
 * 大口井
 */
@Entity
@Table(name = "great_wells", catalog = "exciting")
public class GreatWellsEntity extends SuperBean {

    private static final Long serialVersionUID = -905482052785724952L;

    private String irrigateArea;     //灌溉面积

    private String waterCapacity;        //储水量

    private String size;             //井口尺寸

    private String depth;            //井深

    private String modelAndMaterial;         //井型和井壁材质

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

    public String getWaterCapacity() {
        return waterCapacity;
    }

    public void setWaterCapacity(String waterCapacity) {
        this.waterCapacity = waterCapacity;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public String getModelAndMaterial() {
        return modelAndMaterial;
    }

    public void setModelAndMaterial(String modelAndMaterial) {
        this.modelAndMaterial = modelAndMaterial;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
