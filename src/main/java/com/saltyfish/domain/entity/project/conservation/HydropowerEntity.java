package com.saltyfish.domain.entity.project.conservation;

import com.saltyfish.domain.entity.base.SuperBean;
import com.saltyfish.domain.entity.project.device.GeneratorEntity;
import com.saltyfish.domain.entity.project.device.TransformerEntity;
import com.saltyfish.domain.entity.project.device.TurbineEntity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by weck on 16/8/30.
 * <p>
 * 水电站
 */
@Entity
@Table(name = "hydropower", catalog = "exciting")
public class HydropowerEntity extends SuperBean {

    private static final Long serialVersionUID = -4945280285272950802L;

    //受益面积
    private String irrigateArea;     //灌溉面积

    private String paddyFieldArea;   //其中水田面积

    private String drainageArea;     //排涝面积

    //机电灌溉收费情况
    private String irrigateFee;     //灌溉收费标准

    private String drainageFee;     //排涝收费标准

    private String annualFee;    //年收入

    private String riverLocation;   //所在河流

    private String machineArea;      //机房面积,m2

    private String affiliation;         //隶属关系

    private String sumElectricCapacity;      //总发电装机容量（KW）

    private String averageCapacity;          //近5年平均年发电量(万kWh)

    //照片
    private String internalImage;       //发电站内照片

    private String externalImage;       //发电站外照片

    private String problem;         //存在的问题

    /**
     * 变压器
     */
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "bydropower_id", referencedColumnName = "id")
    private List<TransformerEntity> transformers;


    /**
     * 水轮机
     */
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "hydropower_id", referencedColumnName = "id")
    private List<TurbineEntity> turbines;

    /**
     * 发电机
     */
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "hydropower_id", referencedColumnName = "id")
    private List<GeneratorEntity> generators;

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<TransformerEntity> getTransformers() {
        return transformers;
    }

    public void setTransformers(List<TransformerEntity> transformers) {
        this.transformers = transformers;
    }

    public List<TurbineEntity> getTurbines() {
        return turbines;
    }

    public void setTurbines(List<TurbineEntity> turbines) {
        this.turbines = turbines;
    }

    public List<GeneratorEntity> getGenerators() {
        return generators;
    }

    public void setGenerators(List<GeneratorEntity> generators) {
        this.generators = generators;
    }

    public String getIrrigateArea() {
        return irrigateArea;
    }

    public void setIrrigateArea(String irrigateArea) {
        this.irrigateArea = irrigateArea;
    }

    public String getPaddyFieldArea() {
        return paddyFieldArea;
    }

    public void setPaddyFieldArea(String paddyFieldArea) {
        this.paddyFieldArea = paddyFieldArea;
    }

    public String getDrainageArea() {
        return drainageArea;
    }

    public void setDrainageArea(String drainageArea) {
        this.drainageArea = drainageArea;
    }

    public String getIrrigateFee() {
        return irrigateFee;
    }

    public void setIrrigateFee(String irrigateFee) {
        this.irrigateFee = irrigateFee;
    }

    public String getDrainageFee() {
        return drainageFee;
    }

    public void setDrainageFee(String drainageFee) {
        this.drainageFee = drainageFee;
    }

    public String getAnnualFee() {
        return annualFee;
    }

    public void setAnnualFee(String annualFee) {
        this.annualFee = annualFee;
    }

    public String getRiverLocation() {
        return riverLocation;
    }

    public void setRiverLocation(String riverLocation) {
        this.riverLocation = riverLocation;
    }

    public String getMachineArea() {
        return machineArea;
    }

    public void setMachineArea(String machineArea) {
        this.machineArea = machineArea;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public String getSumElectricCapacity() {
        return sumElectricCapacity;
    }

    public void setSumElectricCapacity(String sumElectricCapacity) {
        this.sumElectricCapacity = sumElectricCapacity;
    }

    public String getAverageCapacity() {
        return averageCapacity;
    }

    public void setAverageCapacity(String averageCapacity) {
        this.averageCapacity = averageCapacity;
    }

    public String getInternalImage() {
        return internalImage;
    }

    public void setInternalImage(String internalImage) {
        this.internalImage = internalImage;
    }

    public String getExternalImage() {
        return externalImage;
    }

    public void setExternalImage(String externalImage) {
        this.externalImage = externalImage;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }
}
