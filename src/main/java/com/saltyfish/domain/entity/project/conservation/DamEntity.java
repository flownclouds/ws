package com.saltyfish.domain.entity.project.conservation;

import com.saltyfish.domain.entity.base.SuperBean;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by weck on 16/8/30.
 * <p>
 * 塘坝
 */

@Entity
@Table(name = "dam", catalog = "exciting")
public class DamEntity extends SuperBean {
    private static final Long serialVersionUID = -2342987549323245464L;

    private String isRegistered;        //是否注册登记

    private String features; //坝长,坝高,库容,堰顶高程

    private String mainFunctions;   //主要功能

    private String isAccountability;    //是否建立三级责任制

    private String feeResources;    //主要运行经费来源

    private String maintainPersonFee;   //聘用维护人员工资

    private String isCertificated;      //是否持证上岗

    private String development;     //生产开发

    private String manageRageLine;      //大坝管理范围线

    private String pondDamManageRageLine;     //塘坝管理范围线

    private String protectGround;   //护坝地

    private String twoLinesBuilding;        //理论二线范围内建筑物

    private String specifiedMange;          //规范化管理

    private String image;           //照片

    private String protectRageManagement;       //大坝保护范围封闭管理

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getProtectRageManagement() {
        return protectRageManagement;
    }

    public void setProtectRageManagement(String protectRageManagement) {
        this.protectRageManagement = protectRageManagement;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIsRegistered() {
        return isRegistered;
    }

    public void setIsRegistered(String isRegistered) {
        this.isRegistered = isRegistered;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getMainFunctions() {
        return mainFunctions;
    }

    public void setMainFunctions(String mainFunctions) {
        this.mainFunctions = mainFunctions;
    }

    public String getIsAccountability() {
        return isAccountability;
    }

    public void setIsAccountability(String isAccountability) {
        this.isAccountability = isAccountability;
    }

    public String getFeeResources() {
        return feeResources;
    }

    public void setFeeResources(String feeResources) {
        this.feeResources = feeResources;
    }

    public String getMaintainPersonFee() {
        return maintainPersonFee;
    }

    public void setMaintainPersonFee(String maintainPersonFee) {
        this.maintainPersonFee = maintainPersonFee;
    }

    public String getIsCertificated() {
        return isCertificated;
    }

    public void setIsCertificated(String isCertificated) {
        this.isCertificated = isCertificated;
    }

    public String getDevelopment() {
        return development;
    }

    public void setDevelopment(String development) {
        this.development = development;
    }

    public String getManageRageLine() {
        return manageRageLine;
    }

    public void setManageRageLine(String manageRageLine) {
        this.manageRageLine = manageRageLine;
    }

    public String getPondDamManageRageLine() {
        return pondDamManageRageLine;
    }

    public void setPondDamManageRageLine(String pondDamManageRageLine) {
        this.pondDamManageRageLine = pondDamManageRageLine;
    }

    public String getProtectGround() {
        return protectGround;
    }

    public void setProtectGround(String protectGround) {
        this.protectGround = protectGround;
    }

    public String getTwoLinesBuilding() {
        return twoLinesBuilding;
    }

    public void setTwoLinesBuilding(String twoLinesBuilding) {
        this.twoLinesBuilding = twoLinesBuilding;
    }

    public String getSpecifiedMange() {
        return specifiedMange;
    }

    public void setSpecifiedMange(String specifiedMange) {
        this.specifiedMange = specifiedMange;
    }
}
