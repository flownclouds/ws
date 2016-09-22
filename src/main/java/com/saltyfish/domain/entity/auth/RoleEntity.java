package com.saltyfish.domain.entity.auth;

import com.saltyfish.domain.entity.base.BaseBean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by weck on 16/8/27.
 * <p>
 * 角色
 */
@Entity
@Table(name = "role", catalog = "exciting")
public class RoleEntity extends BaseBean {

    private static final Long serialVersionUID = -6122347374515830424L;

    @Column(unique = true, nullable = false)
    private String name;        //guest只能查看权限范围内据,superGuest能查看全县所有乡镇的数据，admin能管理权限范围内的数据,countyManager能查看全县数据并管理县内用户,superAdmin能查看全市汇总并管理县级管理员

    @Column(columnDefinition = "int(1) default 1", nullable = false)
    private Integer isActive;          //是否启用


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
}
