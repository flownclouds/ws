package com.saltyfish.domain.entity.other;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.saltyfish.domain.entity.auth.UserEntity;
import com.saltyfish.domain.entity.base.BaseBean;
import com.saltyfish.domain.entity.unit.CountyEntity;

import javax.persistence.*;

/**
 * Created by weck on 16/8/29.
 * <p>
 * 通知
 */
@Entity
@Table(name = "notification", catalog = "exciting")
public class NotificationEntity extends BaseBean {

    private static final Long serialVersionUID = -5924582025423825370L;

    @Column(nullable = false)
    private String title;           //通知标题

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "county_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private CountyEntity county;

    @Column(nullable = false)
    private String content;         //内容

    @Column(nullable = false)
    private Integer category;       //种类,1县级,2市级

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserEntity user;

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public CountyEntity getCounty() {
        return county;
    }

    public void setCounty(CountyEntity county) {
        this.county = county;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }
}
