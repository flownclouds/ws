package com.saltyfish.domain.entity.conservation;

import com.saltyfish.common.bean.division.Pipe;
import com.saltyfish.domain.entity.superbean.ConservationEntity;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by weck on 16/9/24.
 */

@Document(collection = "dripIrrigationPipe")
public class DripIrrigationPipeEntity extends ConservationEntity {
    private static final Long serialVersionUID = -59475892792024924L;

    private String irrigateArea;     //灌溉面积

    private String intakeWay;       //取水方式

    private String length;

    private String waterResource;       //水源

    private String sumLength;            //管道总长

    private String sumDiameter;          //管道总直径

    private String image;               //照片

    private String sketch;          //平面草图

    private List<Pipe> pipes;

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getIrrigateArea() {
        return irrigateArea;
    }

    public void setIrrigateArea(String irrigateArea) {
        this.irrigateArea = irrigateArea;
    }

    public String getIntakeWay() {
        return intakeWay;
    }

    public void setIntakeWay(String intakeWay) {
        this.intakeWay = intakeWay;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWaterResource() {
        return waterResource;
    }

    public void setWaterResource(String waterResource) {
        this.waterResource = waterResource;
    }

    public String getSumLength() {
        return sumLength;
    }

    public void setSumLength(String sumLength) {
        this.sumLength = sumLength;
    }

    public String getSumDiameter() {
        return sumDiameter;
    }

    public void setSumDiameter(String sumDiameter) {
        this.sumDiameter = sumDiameter;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSketch() {
        return sketch;
    }

    public void setSketch(String sketch) {
        this.sketch = sketch;
    }

    public List<Pipe> getPipes() {
        return pipes;
    }

    public void setPipes(List<Pipe> pipes) {
        this.pipes = pipes;
    }
}
