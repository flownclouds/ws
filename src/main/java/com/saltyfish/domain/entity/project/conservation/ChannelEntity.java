package com.saltyfish.domain.entity.project.conservation;

import com.saltyfish.domain.entity.base.SuperBean;
import com.saltyfish.domain.entity.project.division.CanalEntity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by weck on 16/8/30.
 * <p>
 * 渠道
 */

@Entity
@Table(name = "channel", catalog = "exciting")
public class ChannelEntity extends SuperBean {

    private static final Long serialVersionUID = -7954280582502454234L;

    private String headOrPumpStation;             //渠首/取水泵站

    private String buildingMatchRate;         //建筑物配套率(%)

    private String length;                //渠道总长(KM)

    private String goodConditionRate;        //渠道及建筑完好率(%)

    private String image;                   //照片

    private String sectionSize;           //总计断面尺寸

    private String seepageCanalLength;        //总计其中防渗渠长度(KM)

    private String liningSectionSize;         //总计衬砌断面尺寸

    private String sumLength;           //合计长度

    private String sumLiningSectionSize;    //合计砌断面尺寸

    private String sumSeepageCanalLength;   //合计防渗渠长度

    private String sumSectionSize;  //合计断面尺寸

    private String planeSketch;         //平面草图

    /**
     * 渠道组成部分
     */
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "channel_id", referencedColumnName = "id")
    private List<CanalEntity> canals;

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSumLength() {
        return sumLength;
    }

    public void setSumLength(String sumLength) {
        this.sumLength = sumLength;
    }

    public String getPlaneSketch() {
        return planeSketch;
    }

    public void setPlaneSketch(String planeSketch) {
        this.planeSketch = planeSketch;
    }

    public List<CanalEntity> getCanals() {
        return canals;
    }

    public void setCanals(List<CanalEntity> canals) {
        this.canals = canals;
    }

    public String getHeadOrPumpStation() {
        return headOrPumpStation;
    }

    public void setHeadOrPumpStation(String headOrPumpStation) {
        this.headOrPumpStation = headOrPumpStation;
    }

    public String getBuildingMatchRate() {
        return buildingMatchRate;
    }

    public void setBuildingMatchRate(String buildingMatchRate) {
        this.buildingMatchRate = buildingMatchRate;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getGoodConditionRate() {
        return goodConditionRate;
    }

    public void setGoodConditionRate(String goodConditionRate) {
        this.goodConditionRate = goodConditionRate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSectionSize() {
        return sectionSize;
    }

    public void setSectionSize(String sectionSize) {
        this.sectionSize = sectionSize;
    }

    public String getSeepageCanalLength() {
        return seepageCanalLength;
    }

    public void setSeepageCanalLength(String seepageCanalLength) {
        this.seepageCanalLength = seepageCanalLength;
    }

    public String getLiningSectionSize() {
        return liningSectionSize;
    }

    public void setLiningSectionSize(String liningSectionSize) {
        this.liningSectionSize = liningSectionSize;
    }

    public String getSumLiningSectionSize() {
        return sumLiningSectionSize;
    }

    public void setSumLiningSectionSize(String sumLiningSectionSize) {
        this.sumLiningSectionSize = sumLiningSectionSize;
    }

    public String getSumSeepageCanalLength() {
        return sumSeepageCanalLength;
    }

    public void setSumSeepageCanalLength(String sumSeepageCanalLength) {
        this.sumSeepageCanalLength = sumSeepageCanalLength;
    }

    public String getSumSectionSize() {
        return sumSectionSize;
    }

    public void setSumSectionSize(String sumSectionSize) {
        this.sumSectionSize = sumSectionSize;
    }
}
