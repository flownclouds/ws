package com.saltyfish.domain.entity.unit;

import com.saltyfish.domain.entity.base.Unit;

import javax.persistence.*;

/**
 * Created by weck on 16/8/29.
 * <p>
 * 村
 */
@Entity
@Table(name = "village", catalog = "exciting")
public class VillageEntity extends Unit {

    private static final Long serialVersionUID = -1283787394589098432L;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "town_id", referencedColumnName = "id", nullable = false)
    private TownEntity town;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "county_id", referencedColumnName = "id", nullable = false)
    private CountyEntity county;

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public TownEntity getTown() {
        return town;
    }

    public void setTown(TownEntity town) {
        this.town = town;
    }

    public CountyEntity getCounty() {
        return county;
    }

    public void setCounty(CountyEntity county) {
        this.county = county;
    }
}
