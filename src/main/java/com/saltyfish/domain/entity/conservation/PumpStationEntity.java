package com.saltyfish.domain.entity.conservation;

import com.saltyfish.common.bean.device.ElectricMotor;
import com.saltyfish.common.bean.device.Pump;
import com.saltyfish.common.bean.device.Transformer;
import com.saltyfish.domain.entity.superbean.ConservationEntity;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by weck on 16/9/24.
 */
@Document(collection = "pumpStation")
public class PumpStationEntity extends ConservationEntity {
    private static final Long serialVersionUID = -798391408317894179L;
    List<Transformer> transformers;
    List<Pump> pumps;
    List<ElectricMotor> electricMotors;
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

    public static Long getSerialVersionUID() {
        return serialVersionUID;
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

    public List<Transformer> getTransformers() {
        return transformers;
    }

    public void setTransformers(List<Transformer> transformers) {
        this.transformers = transformers;
    }

    public List<Pump> getPumps() {
        return pumps;
    }

    public void setPumps(List<Pump> pumps) {
        this.pumps = pumps;
    }

    public List<ElectricMotor> getElectricMotors() {
        return electricMotors;
    }

    public void setElectricMotors(List<ElectricMotor> electricMotors) {
        this.electricMotors = electricMotors;
    }
}
