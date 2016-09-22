package com.saltyfish.domain.entity.project.division;

import com.saltyfish.domain.entity.base.SuperBean;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by weck on 16/8/31.
 * <p>
 * 沟渠
 */
@Entity
@Table(name = "canal", catalog = "exciting")
public class CanalEntity extends SuperBean {

    private static final Long serialVersionUID = -5847937472510125424L;

    private String model;           //类别

    private String length;               //长度

    private String sectionSize;              //断面尺寸

    private String seepageCanalLength;       //其中防渗渠长度(KM)

    private String liningSectionSize;        //衬砌断面尺寸

    private String liningMaterial;       //衬砌材质

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

    public String getLiningMaterial() {
        return liningMaterial;
    }

    public void setLiningMaterial(String liningMaterial) {
        this.liningMaterial = liningMaterial;
    }
}
