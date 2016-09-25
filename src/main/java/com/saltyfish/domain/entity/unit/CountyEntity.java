package com.saltyfish.domain.entity.unit;

import com.saltyfish.domain.entity.superbean.UnitBean;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by weck on 16/8/29.
 * <p>
 * 县
 */
@Entity
@Table(name = "county", catalog = "conservation")
public class CountyEntity extends UnitBean {

    private static final Long serialVersionUID = -2394798907978590424L;

    private String longitude;
    private String latitude;

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
