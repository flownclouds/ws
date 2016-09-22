package com.saltyfish.domain.entity.unit;

import com.saltyfish.domain.entity.base.Unit;

import javax.persistence.*;

/**
 * Created by weck on 16/8/29.
 * <p>
 * 镇
 */
@Entity
@Table(name = "town", catalog = "exciting")
public class TownEntity extends Unit {

    private static final Long serialVersionUID = -8390098490395830424L;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "county_id", referencedColumnName = "id", nullable = false)
    private CountyEntity county;

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public CountyEntity getCounty() {
        return county;
    }

    public void setCounty(CountyEntity county) {
        this.county = county;
    }
}
