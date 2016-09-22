package com.saltyfish.domain.entity.base;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by weck on 16/9/2.
 * <p>
 * 实体超类
 */
@MappedSuperclass
public class SuperBean implements Serializable {

    private static final Long serialVersionUID = -945829729470298024L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;             //自增id

    public static Long getSerialVersionUID() {

        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
