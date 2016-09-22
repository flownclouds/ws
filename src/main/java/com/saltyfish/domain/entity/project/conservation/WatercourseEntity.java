package com.saltyfish.domain.entity.project.conservation;

import com.saltyfish.domain.entity.base.SuperBean;
import com.saltyfish.domain.entity.location.LocationEntity;

import javax.persistence.*;

/**
 * Created by weck on 16/8/30.
 * <p>
 * 河道
 */
@Entity
@Table(name = "watercourse", catalog = "exciting")
public class WatercourseEntity extends SuperBean {

    private static final Long serialVersionUID = 8745279287298234L;

    private String length;                   //长度,km

    private String lastDredgingTime;     //上次疏浚时间

    private String estuaryWidth;             //河口宽度 m

    private String hediWidth;                    //河底宽度,m

    private String leftWidth;                //左岸总宽,m

    private String estuaryHeight;            //河口高程,m

    private String hediHeight;               //河底高程,m

    private String rightWidth;               //右岸总宽,m

    private String flowVillages;            //流经村组

    private String nature;                  //河道性质

    private String sectionImage;                   //断面图

    /**
     * 终点坐标,water_conservation的默认起点坐标
     */
    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "endpoint_location_id", referencedColumnName = "id")
    private LocationEntity endpointLocation;

    private String startImage;          //照片头段

    private String middleImage;         //照片中段

    private String endImage;            //照片尾段

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }


    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getLastDredgingTime() {
        return lastDredgingTime;
    }

    public void setLastDredgingTime(String lastDredgingTime) {
        this.lastDredgingTime = lastDredgingTime;
    }

    public String getEstuaryWidth() {
        return estuaryWidth;
    }

    public void setEstuaryWidth(String estuaryWidth) {
        this.estuaryWidth = estuaryWidth;
    }

    public String getHediWidth() {
        return hediWidth;
    }

    public void setHediWidth(String hediWidth) {
        this.hediWidth = hediWidth;
    }

    public String getLeftWidth() {
        return leftWidth;
    }

    public void setLeftWidth(String leftWidth) {
        this.leftWidth = leftWidth;
    }

    public String getEstuaryHeight() {
        return estuaryHeight;
    }

    public void setEstuaryHeight(String estuaryHeight) {
        this.estuaryHeight = estuaryHeight;
    }

    public String getHediHeight() {
        return hediHeight;
    }

    public void setHediHeight(String hediHeight) {
        this.hediHeight = hediHeight;
    }

    public String getRightWidth() {
        return rightWidth;
    }

    public void setRightWidth(String rightWidth) {
        this.rightWidth = rightWidth;
    }

    public String getFlowVillages() {
        return flowVillages;
    }

    public void setFlowVillages(String flowVillages) {
        this.flowVillages = flowVillages;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getSectionImage() {
        return sectionImage;
    }

    public void setSectionImage(String sectionImage) {
        this.sectionImage = sectionImage;
    }

    public LocationEntity getEndpointLocation() {
        return endpointLocation;
    }

    public void setEndpointLocation(LocationEntity endpointLocation) {
        this.endpointLocation = endpointLocation;
    }

    public String getStartImage() {
        return startImage;
    }

    public void setStartImage(String startImage) {
        this.startImage = startImage;
    }

    public String getMiddleImage() {
        return middleImage;
    }

    public void setMiddleImage(String middleImage) {
        this.middleImage = middleImage;
    }

    public String getEndImage() {
        return endImage;
    }

    public void setEndImage(String endImage) {
        this.endImage = endImage;
    }
}
