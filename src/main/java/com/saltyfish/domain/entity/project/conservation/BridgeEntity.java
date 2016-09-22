package com.saltyfish.domain.entity.project.conservation;

import com.saltyfish.domain.entity.base.SuperBean;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by weck on 16/8/30.
 * <p>
 * 桥梁
 */

@Entity
@Table(name = "bridge", catalog = "exciting")
public class BridgeEntity extends SuperBean {

    private static final Long serialVersionUID = -7874927950228503234L;

    private String watercourseLocation;     //所在河道

    private String crossCount;             //桥跨数

    private String structureAndMaterial;           //梁板结构形式及材质

    private String loadStandard;                     //负载标准,T

    private String crossLength;              //每跨长度,m

    private String width;                    //桥面宽度,m

    private String length;                   //桥面总长,m

    private String image;                   //照片

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getWatercourseLocation() {
        return watercourseLocation;
    }

    public void setWatercourseLocation(String watercourseLocation) {
        this.watercourseLocation = watercourseLocation;
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

    public String getLoadStandard() {
        return loadStandard;
    }

    public void setLoadStandard(String loadStandard) {
        this.loadStandard = loadStandard;
    }

    public String getCrossLength() {
        return crossLength;
    }

    public void setCrossLength(String crossLength) {
        this.crossLength = crossLength;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
