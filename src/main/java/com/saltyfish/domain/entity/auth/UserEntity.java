package com.saltyfish.domain.entity.auth;

import com.saltyfish.domain.entity.base.BaseBean;
import com.saltyfish.domain.entity.unit.CountyEntity;
import com.saltyfish.domain.entity.unit.TownEntity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by weck on 16/8/27.
 * <p>
 * 用户账号
 */
@Entity
@Table(name = "user", catalog = "exciting")
public class UserEntity extends BaseBean {

    private static final Long serialVersionUID = -6128783497515830424L;

    @Column(unique = true, nullable = false)
    private String name;        //用户名

    @Column(nullable = false)
    private String password;        //密码

    @Column(nullable = false)
    private String salt;        //加密的盐

    private String token;

    @Column(columnDefinition = "int(1) default 0", nullable = false)
    private Integer isSuperAdmin;   //是否超级管理员

    /*一个县对应多个用户,获取用户信息的时候也要获取县的信息,如果县的信息为空,用户也不应该为空,比如超级管理员*/
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "county_id", referencedColumnName = "id", nullable = false)
    private CountyEntity county;

    /*一个用户对应多个角色,一个角色有多个用户,删除用户信息不能删除角色信息*/
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    private RoleEntity role;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "town_id", referencedColumnName = "id")
    private List<TownEntity> towns;

    private String phone;           //电话

    @Column(columnDefinition = "int(1) default 1", nullable = false)
    private Integer isActive;       //是否启用

    private String email;           //邮箱

    private String realName;        //真实姓名

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<TownEntity> getTowns() {
        return towns;
    }

    public void setTowns(List<TownEntity> towns) {
        this.towns = towns;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CountyEntity getCounty() {
        return county;
    }

    public void setCounty(CountyEntity county) {
        this.county = county;
    }

    public Integer getIsSuperAdmin() {
        return isSuperAdmin;
    }

    public void setIsSuperAdmin(Integer isSuperAdmin) {
        this.isSuperAdmin = isSuperAdmin;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
