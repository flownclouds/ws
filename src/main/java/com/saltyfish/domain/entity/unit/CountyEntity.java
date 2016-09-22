package com.saltyfish.domain.entity.unit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.saltyfish.domain.entity.base.Unit;
import com.saltyfish.domain.entity.location.LocationEntity;

import javax.persistence.*;

/**
 * Created by weck on 16/8/29.
 * <p>
 * 县
 */
@Entity
@Table(name = "county", catalog = "exciting")
public class CountyEntity extends Unit {

    private static final Long serialVersionUID = -2394798907978590424L;

    /*一个县对应一个定位,删除县的同时要删除其定位,县作为维护的一方*/
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private LocationEntity location;

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public LocationEntity getLocation() {
        return location;
    }

    public void setLocation(LocationEntity location) {
        this.location = location;
    }

}
