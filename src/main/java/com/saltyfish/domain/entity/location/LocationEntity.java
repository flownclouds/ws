package com.saltyfish.domain.entity.location;

import com.saltyfish.domain.entity.base.BaseBean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by weck on 16/9/1.
 * <p>
 * 经纬度
 */
@Entity
@Table(name = "location", catalog = "exciting")
public class LocationEntity extends BaseBean {

    private static final Long serialVersionUID = -2389744690840525370L;

    @Column(nullable = false)
    private String longitude;

    @Column(nullable = false)
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
