package com.saltyfish.domain.entity.unit;

import com.saltyfish.domain.entity.superbean.UnitBean;

import javax.persistence.*;

/**
 * Created by weck on 16/8/29.
 * <p>
 * 村
 */
@Entity
@Table(name = "village", catalog = "conservation")
public class VillageEntity extends UnitBean {

    private static final Long serialVersionUID = -1283787394589098432L;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "town_id", referencedColumnName = "id", nullable = false)
    private TownEntity town;

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public TownEntity getTown() {
        return town;
    }

    public void setTown(TownEntity town) {
        this.town = town;
    }

}
