package com.saltyfish.domain.entity.project.projectdetail;

import com.saltyfish.domain.entity.base.BaseBean;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by weck on 16/8/30.
 * <p>
 * 投资
 */
@Entity
@Table(name = "investment", catalog = "exciting")
public class InvestmentEntity extends BaseBean {

    private static final Long serialVersionUID = 954892794278590424L;

    private String invest;          //投资金额

    private String category;           //1 概算投资,2实际到位投资

    private String investResource;      //投资来源，国家、省、市、县、其他

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getInvest() {
        return invest;
    }

    public void setInvest(String invest) {
        this.invest = invest;
    }

    public String getInvestResource() {
        return investResource;
    }

    public void setInvestResource(String investResource) {
        this.investResource = investResource;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
