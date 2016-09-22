package com.saltyfish.domain.entity.project.conservation;

import com.saltyfish.domain.entity.base.SuperBean;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by weck on 16/8/30.
 * <p>
 * 渡槽
 */
@Entity
@Table(name = "aqueduct", catalog = "exciting")
public class AqueductEntity extends SuperBean {

    private static final Long serialVersionUID = -3094094589759825424L;

    private String crossWatercourseLocation;        //所跨河道

    private String crossCount;             //跨数

    private String sectionSize;              //断面尺寸

    private String structureAndMaterial;         //槽身结构和材质

    private String crossLength;              //每跨长

    private String image;               //照片

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCrossWatercourseLocation() {
        return crossWatercourseLocation;
    }

    public void setCrossWatercourseLocation(String crossWatercourseLocation) {
        this.crossWatercourseLocation = crossWatercourseLocation;
    }

    public String getCrossCount() {
        return crossCount;
    }

    public void setCrossCount(String crossCount) {
        this.crossCount = crossCount;
    }

    public String getStructureAndMaterial() {
        return structureAndMaterial;
    }

    public void setStructureAndMaterial(String structureAndMaterial) {
        this.structureAndMaterial = structureAndMaterial;
    }

    public String getSectionSize() {
        return sectionSize;
    }

    public void setSectionSize(String sectionSize) {
        this.sectionSize = sectionSize;
    }

    public String getCrossLength() {
        return crossLength;
    }

    public void setCrossLength(String crossLength) {
        this.crossLength = crossLength;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
