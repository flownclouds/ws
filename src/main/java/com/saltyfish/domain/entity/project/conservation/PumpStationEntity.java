package com.saltyfish.domain.entity.project.conservation;

import com.saltyfish.domain.entity.base.SuperBean;
import com.saltyfish.domain.entity.project.device.ElectricMotorEntity;
import com.saltyfish.domain.entity.project.device.PumpEntity;
import com.saltyfish.domain.entity.project.device.TransformerEntity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by weck on 16/8/30.
 * <p>
 * 泵站
 */
@Entity
@Table(name = "pump_station", catalog = "exciting")
public class PumpStationEntity extends SuperBean {

    private static final Long serialVersionUID = -798391408317894179L;

    private String riverLocation;   //所在沟河名称

    //受益面积
    private String irrigateArea;     //灌溉面积

    private String paddyFieldArea;   //其中水田面积

    private String drainageArea;     //排涝面积

    //机电灌溉收费情况
    private String irrigateFee;     //灌溉收费标准

    private String drainageFee;     //排涝收费标准

    private String annualFee;    //年收费

    private String nature;          //泵站的性质

    private String machineArea;      //机房面积,m2

    private String totalInstalledCapacity;   //总装机容量,KW

    private String riverElevation;   //引河河底高程,m

    private String poolHeight;       //进水池底板顶高程,m

    //照片
    private String internalImage;       //泵房内照片

    private String externalImage;       //泵房外照片

    private String problem;         //存在的问题

    /**
     * 变压器
     */
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "pump_station_id", referencedColumnName = "id")
    private List<TransformerEntity> transformers;

    /**
     * 水泵
     */
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "pump_station_id", referencedColumnName = "id")
    private List<PumpEntity> pumps;

    /**
     * 电动机
     */
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "pump_station_id", referencedColumnName = "id")
    private List<ElectricMotorEntity> electricMotors;

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<TransformerEntity> getTransformers() {
        return transformers;
    }

    public void setTransformers(List<TransformerEntity> transformers) {
        this.transformers = transformers;
    }

    public List<PumpEntity> getPumps() {
        return pumps;
    }

    public void setPumps(List<PumpEntity> pumps) {
        this.pumps = pumps;
    }

    public List<ElectricMotorEntity> getElectricMotors() {
        return electricMotors;
    }

    public void setElectricMotors(List<ElectricMotorEntity> electricMotors) {
        this.electricMotors = electricMotors;
    }

    public String getRiverLocation() {
        return riverLocation;
    }

    public void setRiverLocation(String riverLocation) {
        this.riverLocation = riverLocation;
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

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getMachineArea() {
        return machineArea;
    }

    public void setMachineArea(String machineArea) {
        this.machineArea = machineArea;
    }

    public String getTotalInstalledCapacity() {
        return totalInstalledCapacity;
    }

    public void setTotalInstalledCapacity(String totalInstalledCapacity) {
        this.totalInstalledCapacity = totalInstalledCapacity;
    }

    public String getRiverElevation() {
        return riverElevation;
    }

    public void setRiverElevation(String riverElevation) {
        this.riverElevation = riverElevation;
    }

    public String getPoolHeight() {
        return poolHeight;
    }

    public void setPoolHeight(String poolHeight) {
        this.poolHeight = poolHeight;
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
