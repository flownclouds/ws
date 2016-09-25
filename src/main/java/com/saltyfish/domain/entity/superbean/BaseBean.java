package com.saltyfish.domain.entity.superbean;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by weck on 16/8/27.
 * <p>
 * 实体父类
 */
@MappedSuperclass
public class BaseBean implements Serializable {

    private static final Long serialVersionUID = -7674269980281525370L;

    private Long createTime;

    private Long updateTime;

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

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Long getCreateTime() {

        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
