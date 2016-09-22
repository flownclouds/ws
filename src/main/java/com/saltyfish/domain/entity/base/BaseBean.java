package com.saltyfish.domain.entity.base;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

/**
 * Created by weck on 16/8/27.
 * <p>
 * 实体父类
 */
@MappedSuperclass
public class BaseBean extends SuperBean {

    private static final Long serialVersionUID = -7674269980281525370L;

    @Column(nullable = false)
    private Timestamp createTime = new Timestamp(System.currentTimeMillis());       //创建时间

    @Column(nullable = false)
    private Timestamp updateTime = new Timestamp(System.currentTimeMillis());       //更新时间

    @Column(nullable = false, columnDefinition = "int(1) default 0")
    private Integer isDelete;           //是否删除


    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
