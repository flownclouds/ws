package com.saltyfish.domain.entity.conservation;

import com.saltyfish.domain.entity.superbean.ConservationEntity;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by weck on 16/9/24.
 */
@Document(collection = "watercourse")
public class WatercourseEntity extends ConservationEntity {
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

    private String endpointLongitude;

    private String endpointLatitude;

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

    public String getEndpointLongitude() {
        return endpointLongitude;
    }

    public void setEndpointLongitude(String endpointLongitude) {
        this.endpointLongitude = endpointLongitude;
    }

    public String getEndpointLatitude() {
        return endpointLatitude;
    }

    public void setEndpointLatitude(String endpointLatitude) {
        this.endpointLatitude = endpointLatitude;
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
