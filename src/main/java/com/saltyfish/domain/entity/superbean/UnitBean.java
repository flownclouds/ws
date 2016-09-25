package com.saltyfish.domain.entity.superbean;

import javax.persistence.*;

/**
 * Created by weck on 16/8/30.
 * <p>
 * 行政单位父类
 */
@MappedSuperclass
public class UnitBean extends BaseBean {

    private static final Long serialVersionUID = -2174837942509293370L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;             //自增id
    @Column(nullable = false)

    private String name;            //单位名称

    @Column(nullable = false, columnDefinition = "int(1) default 1")
    private Integer isActive;       //是否启用

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
