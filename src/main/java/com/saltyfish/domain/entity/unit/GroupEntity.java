package com.saltyfish.domain.entity.unit;

import com.saltyfish.domain.entity.base.Unit;

import javax.persistence.*;

/**
 * Created by weck on 16/8/30.
 * <p>
 * 组
 */
@Entity
@Table(name = "group", catalog = "exciting")
public class GroupEntity extends Unit {

    private static final Long serialVersionUID = -6128309824038223424L;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "village_id", referencedColumnName = "id", nullable = false)
    private VillageEntity village;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "town_id", referencedColumnName = "id", nullable = false)
    private TownEntity town;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "county_id", referencedColumnName = "id", nullable = false)
    private CountyEntity county;

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public VillageEntity getVillage() {
        return village;
    }

    public void setVillage(VillageEntity village) {
        this.village = village;
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
