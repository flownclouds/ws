package com.saltyfish.domain.entity.project;

import com.saltyfish.domain.entity.base.BaseBean;
import com.saltyfish.domain.entity.project.projectdetail.ApprovalEntity;
import com.saltyfish.domain.entity.project.projectdetail.CheckEntity;
import com.saltyfish.domain.entity.project.projectdetail.InvestmentEntity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by weck on 16/9/19.
 * <p>
 * 工程明细
 */
@Entity
@Table(name = "project_detail", catalog = "exciting")
public class ProjectDetailEntity extends BaseBean {

    private static final Long serialVersionUID = -3838574890528525370L;

    @Column(nullable = false)
    private String projectName;

    private String constructTime;

    private String constructUnit;

    private String sumFactInvestment;   //实际到位投资总额

    private String sumEstimateInvestment;   //概算投资总额

    private String others;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "project_detail_id", referencedColumnName = "id", nullable = false)
    private List<ApprovalEntity> approvals;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "project_detail_id", referencedColumnName = "id", nullable = false)
    private List<CheckEntity> checks;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "project_detail_id", referencedColumnName = "id", nullable = false)
    private List<InvestmentEntity> investments;

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getConstructTime() {
        return constructTime;
    }

    public void setConstructTime(String constructTime) {
        this.constructTime = constructTime;
    }

    public String getConstructUnit() {
        return constructUnit;
    }

    public void setConstructUnit(String constructUnit) {
        this.constructUnit = constructUnit;
    }

    public String getSumFactInvestment() {
        return sumFactInvestment;
    }

    public void setSumFactInvestment(String sumFactInvestment) {
        this.sumFactInvestment = sumFactInvestment;
    }

    public String getSumEstimateInvestment() {
        return sumEstimateInvestment;
    }

    public void setSumEstimateInvestment(String sumEstimateInvestment) {
        this.sumEstimateInvestment = sumEstimateInvestment;
    }

    public List<ApprovalEntity> getApprovals() {
        return approvals;
    }

    public void setApprovals(List<ApprovalEntity> approvals) {
        this.approvals = approvals;
    }

    public List<CheckEntity> getChecks() {
        return checks;
    }

    public void setChecks(List<CheckEntity> checks) {
        this.checks = checks;
    }

    public List<InvestmentEntity> getInvestments() {
        return investments;
    }

    public void setInvestments(List<InvestmentEntity> investments) {
        this.investments = investments;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }
}
