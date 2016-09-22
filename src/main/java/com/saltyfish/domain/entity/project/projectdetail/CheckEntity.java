package com.saltyfish.domain.entity.project.projectdetail;

import com.saltyfish.domain.entity.base.BaseBean;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by weck on 16/8/30.
 * <p>
 * 验收
 */
@Entity
@Table(name = "check", catalog = "exciting")
public class CheckEntity extends BaseBean {

    private static final Long serialVersionUID = 1238048502978590424L;

    private String time;

    private String file;

    private String grade;          //等级,1国家级,2省级,3市级,4县级

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
