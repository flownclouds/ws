package com.saltyfish.domain.entity.project.projectdetail;

import com.saltyfish.domain.entity.base.BaseBean;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by weck on 16/8/30.
 * <p>
 * 批复
 */
@Entity
@Table(name = "approval", catalog = "exciting")
public class ApprovalEntity extends BaseBean {

    private static final Long serialVersionUID = -283479572243590424L;

    private String symbol;      //批复文号

    private String time;     //批复时间

    private String file;            //文件路径

    private String grade;       //1国家级,2省级,3市级,4县级

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
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
