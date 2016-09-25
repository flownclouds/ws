package com.saltyfish.domain.entity.unit;

import com.saltyfish.domain.entity.superbean.UnitBean;

import javax.persistence.*;

/**
 * Created by weck on 16/8/30.
 * <p>
 * 组
 */
@Entity
@Table(name = "group", catalog = "conservation")
public class GroupEntity extends UnitBean {

    private static final Long serialVersionUID = -6128309824038223424L;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "village_id", referencedColumnName = "id", nullable = false)
    private VillageEntity village;

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public VillageEntity getVillage() {
        return village;
    }

    public void setVillage(VillageEntity village) {
        this.village = village;
    }
}
