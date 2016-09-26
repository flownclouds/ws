package com.saltyfish.domain.entity.projectdetail;

import com.saltyfish.domain.entity.superbean.BaseBean;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by weck on 16/9/19.
 * <p>
 * 工程明细
 */
@Document(collection = "projectDetail")
public class ProjectDetailEntity extends BaseBean {

    @Id
    private String id;

    private static final Long serialVersionUID = -3838574890528525370L;

    private String projectName;

    private String constructTime;

    private String constructUnit;

    private String sumFactInvestment;   //实际到位投资总额

    private String sumEstimateInvestment;   //概算投资总额

    private String others;

    private String countryApprovalSymbol;

    private String countryApprovalFile;

    private String countryApprovalTime;

    private String provinceApprovalSymbol;

    private String provinceApprovalFile;

    private String provinceApprovalTime;

    private String countyApprovalSymbol;

    private String countyApprovalTime;

    private String countyApprovalFile;

    private String cityApprovalFile;

    private String cityApprovalSymbol;

    private String cityApprovalTime;

    private String countryCheckFile;

    private String countryCheckTime;

    private String provinceCheckFile;

    private String provinceCheckTime;

    private String cityCheckFile;

    private String cityCheckTime;

    private String countyCheckFile;

    private String countyCheckTime;

    private String countryEstimateInvestment;

    private String provinceEstimateInvestment;

    private String cityEstimateInvestment;

    private String countyEstimateInvestment;

    private String countryFactInvestment;

    private String provinceFactInvestment;

    private String cityFactInvestment;

    private String countyFactInvestment;

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

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public String getCountryApprovalSymbol() {
        return countryApprovalSymbol;
    }

    public void setCountryApprovalSymbol(String countryApprovalSymbol) {
        this.countryApprovalSymbol = countryApprovalSymbol;
    }

    public String getCountryApprovalFile() {
        return countryApprovalFile;
    }

    public void setCountryApprovalFile(String countryApprovalFile) {
        this.countryApprovalFile = countryApprovalFile;
    }

    public String getCountryApprovalTime() {
        return countryApprovalTime;
    }

    public void setCountryApprovalTime(String countryApprovalTime) {
        this.countryApprovalTime = countryApprovalTime;
    }

    public String getProvinceApprovalSymbol() {
        return provinceApprovalSymbol;
    }

    public void setProvinceApprovalSymbol(String provinceApprovalSymbol) {
        this.provinceApprovalSymbol = provinceApprovalSymbol;
    }

    public String getProvinceApprovalFile() {
        return provinceApprovalFile;
    }

    public void setProvinceApprovalFile(String provinceApprovalFile) {
        this.provinceApprovalFile = provinceApprovalFile;
    }

    public String getProvinceApprovalTime() {
        return provinceApprovalTime;
    }

    public void setProvinceApprovalTime(String provinceApprovalTime) {
        this.provinceApprovalTime = provinceApprovalTime;
    }

    public String getCountyApprovalSymbol() {
        return countyApprovalSymbol;
    }

    public void setCountyApprovalSymbol(String countyApprovalSymbol) {
        this.countyApprovalSymbol = countyApprovalSymbol;
    }

    public String getCountyApprovalTime() {
        return countyApprovalTime;
    }

    public void setCountyApprovalTime(String countyApprovalTime) {
        this.countyApprovalTime = countyApprovalTime;
    }

    public String getCountyApprovalFile() {
        return countyApprovalFile;
    }

    public void setCountyApprovalFile(String countyApprovalFile) {
        this.countyApprovalFile = countyApprovalFile;
    }

    public String getCityApprovalFile() {
        return cityApprovalFile;
    }

    public void setCityApprovalFile(String cityApprovalFile) {
        this.cityApprovalFile = cityApprovalFile;
    }

    public String getCityApprovalSymbol() {
        return cityApprovalSymbol;
    }

    public void setCityApprovalSymbol(String cityApprovalSymbol) {
        this.cityApprovalSymbol = cityApprovalSymbol;
    }

    public String getCityApprovalTime() {
        return cityApprovalTime;
    }

    public void setCityApprovalTime(String cityApprovalTime) {
        this.cityApprovalTime = cityApprovalTime;
    }

    public String getCountryCheckFile() {
        return countryCheckFile;
    }

    public void setCountryCheckFile(String countryCheckFile) {
        this.countryCheckFile = countryCheckFile;
    }

    public String getCountryCheckTime() {
        return countryCheckTime;
    }

    public void setCountryCheckTime(String countryCheckTime) {
        this.countryCheckTime = countryCheckTime;
    }

    public String getProvinceCheckFile() {
        return provinceCheckFile;
    }

    public void setProvinceCheckFile(String provinceCheckFile) {
        this.provinceCheckFile = provinceCheckFile;
    }

    public String getProvinceCheckTime() {
        return provinceCheckTime;
    }

    public void setProvinceCheckTime(String provinceCheckTime) {
        this.provinceCheckTime = provinceCheckTime;
    }

    public String getCityCheckFile() {
        return cityCheckFile;
    }

    public void setCityCheckFile(String cityCheckFile) {
        this.cityCheckFile = cityCheckFile;
    }

    public String getCityCheckTime() {
        return cityCheckTime;
    }

    public void setCityCheckTime(String cityCheckTime) {
        this.cityCheckTime = cityCheckTime;
    }

    public String getCountyCheckFile() {
        return countyCheckFile;
    }

    public void setCountyCheckFile(String countyCheckFile) {
        this.countyCheckFile = countyCheckFile;
    }

    public String getCountyCheckTime() {
        return countyCheckTime;
    }

    public void setCountyCheckTime(String countyCheckTime) {
        this.countyCheckTime = countyCheckTime;
    }

    public String getCountryEstimateInvestment() {
        return countryEstimateInvestment;
    }

    public void setCountryEstimateInvestment(String countryEstimateInvestment) {
        this.countryEstimateInvestment = countryEstimateInvestment;
    }

    public String getProvinceEstimateInvestment() {
        return provinceEstimateInvestment;
    }

    public void setProvinceEstimateInvestment(String provinceEstimateInvestment) {
        this.provinceEstimateInvestment = provinceEstimateInvestment;
    }

    public String getCityEstimateInvestment() {
        return cityEstimateInvestment;
    }

    public void setCityEstimateInvestment(String cityEstimateInvestment) {
        this.cityEstimateInvestment = cityEstimateInvestment;
    }

    public String getCountyEstimateInvestment() {
        return countyEstimateInvestment;
    }

    public void setCountyEstimateInvestment(String countyEstimateInvestment) {
        this.countyEstimateInvestment = countyEstimateInvestment;
    }

    public String getCountryFactInvestment() {
        return countryFactInvestment;
    }

    public void setCountryFactInvestment(String countryFactInvestment) {
        this.countryFactInvestment = countryFactInvestment;
    }

    public String getProvinceFactInvestment() {
        return provinceFactInvestment;
    }

    public void setProvinceFactInvestment(String provinceFactInvestment) {
        this.provinceFactInvestment = provinceFactInvestment;
    }

    public String getCityFactInvestment() {
        return cityFactInvestment;
    }

    public void setCityFactInvestment(String cityFactInvestment) {
        this.cityFactInvestment = cityFactInvestment;
    }

    public String getCountyFactInvestment() {
        return countyFactInvestment;
    }

    public void setCountyFactInvestment(String countyFactInvestment) {
        this.countyFactInvestment = countyFactInvestment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
