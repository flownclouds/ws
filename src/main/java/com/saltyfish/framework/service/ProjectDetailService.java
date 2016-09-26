package com.saltyfish.framework.service;

import com.saltyfish.domain.entity.projectdetail.ProjectDetailEntity;
import com.saltyfish.domain.repository.projectdetail.ProjectDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Created by weck on 16/9/26.
 */
@Service
public class ProjectDetailService {

    @Autowired
    private ProjectDetailRepository projectDetailRepository;

    public void addProjectDetail(String projectName, Long timeStamp, String constructTime, String constructUnit,
                                 String sumEstimateInvestment, String sumFactInvestment, String others,
                                 String countryApprovalSymbol, String countryApprovalTime, String countryApprovalFilePath,
                                 String provinceApprovalSymbol, String provinceApprovalTime, String provinceApprovalFilePath,
                                 String cityApprovalSymbol, String cityApprovalTime, String cityApprovalFilePath,
                                 String countyApprovalSymbol, String countyApprovalTime, String countyApprovalFilePath,
                                 String provinceCheckFilePath, String provinceCheckTime, String countryCheckTime,
                                 String countryCheckFilePath, String cityCheckFilePath, String cityCheckTime,
                                 String countyCheckFilePath, String countyCheckTime, String cityEstimateInvestment,
                                 String cityFactInvestment, String countryEstimateInvestment, String countryFactInvestment,
                                 String provinceEstimateInvestment, String provinceFactInvestment,
                                 String countyEstimateInvestment, String countyFactInvestment) {
        ProjectDetailEntity projectDetail = new ProjectDetailEntity();
        projectDetail.setProjectName(projectName);
        projectDetail.setProvinceCheckFile(provinceCheckFilePath);
        projectDetail.setProvinceCheckTime(provinceCheckTime);
        projectDetail.setCreateTime(timeStamp);
        projectDetail.setUpdateTime(timeStamp);
        projectDetail.setIsDelete(0);
        projectDetail.setCountryCheckTime(countryCheckTime);
        projectDetail.setCountryCheckFile(countryCheckFilePath);
        projectDetail.setCityCheckFile(cityCheckFilePath);
        projectDetail.setCityCheckTime(cityCheckTime);
        projectDetail.setCountyCheckFile(countyCheckFilePath);
        projectDetail.setCountyCheckTime(countyCheckTime);
        projectDetail.setCityEstimateInvestment(cityEstimateInvestment);
        projectDetail.setCityFactInvestment(cityFactInvestment);
        projectDetail.setCountryEstimateInvestment(countryEstimateInvestment);
        projectDetail.setCountryFactInvestment(countryFactInvestment);
        projectDetail.setProvinceEstimateInvestment(provinceEstimateInvestment);
        projectDetail.setProvinceFactInvestment(provinceFactInvestment);
        projectDetail.setCountyFactInvestment(countyFactInvestment);
        projectDetail.setCountyEstimateInvestment(countyEstimateInvestment);
        projectDetail.setConstructTime(constructTime);
        projectDetail.setConstructUnit(constructUnit);
        projectDetail.setSumFactInvestment(sumFactInvestment);
        projectDetail.setSumEstimateInvestment(sumEstimateInvestment);
        projectDetail.setCountryApprovalFile(countryApprovalFilePath);
        projectDetail.setOthers(others);
        projectDetail.setCountryApprovalSymbol(countryApprovalSymbol);
        projectDetail.setCountryApprovalTime(countryApprovalTime);
        projectDetail.setProvinceApprovalFile(provinceApprovalFilePath);
        projectDetail.setProvinceApprovalSymbol(provinceApprovalSymbol);
        projectDetail.setProvinceApprovalTime(provinceApprovalTime);
        projectDetail.setCityApprovalFile(cityApprovalFilePath);
        projectDetail.setCityApprovalSymbol(cityApprovalSymbol);
        projectDetail.setCityApprovalTime(cityApprovalTime);
        projectDetail.setCountyApprovalFile(countyApprovalFilePath);
        projectDetail.setCountyApprovalSymbol(countyApprovalSymbol);
        projectDetail.setCountyApprovalTime(countyApprovalTime);
        projectDetail.setProvinceApprovalFile(provinceApprovalFilePath);
        projectDetail.setProvinceApprovalSymbol(provinceApprovalSymbol);
        projectDetail.setProvinceApprovalTime(provinceApprovalTime);
        projectDetailRepository.save(projectDetail);
    }

    public void deleteProjectDetail(String projectDetailId, Long timeStamp) {
        ProjectDetailEntity projectDetail = projectDetailRepository.findById(projectDetailId);
        projectDetail.setUpdateTime(timeStamp);
        projectDetail.setIsDelete(1);
        projectDetailRepository.save(projectDetail);
    }

    public Page<ProjectDetailEntity> getProjectDetails(Integer page, Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        Pageable pageable = new PageRequest(page - 1, size, sort);
        return projectDetailRepository.findByIsDelete(0, pageable);
    }

    public void modifyProjectDetail(String projectDetailId, String projectName, Long timeStamp, String constructTime,
                                    String constructUnit, String sumEstimateInvestment, String sumFactInvestment,
                                    String others, String countryApprovalSymbol, String countryApprovalTime,
                                    String countryApprovalFilePath, String provinceApprovalSymbol,
                                    String provinceApprovalTime, String provinceApprovalFilePath,
                                    String cityApprovalSymbol, String cityApprovalTime, String cityApprovalFilePath,
                                    String countyApprovalSymbol, String countyApprovalTime,
                                    String countyApprovalFilePath, String provinceCheckFilePath,
                                    String provinceCheckTime, String countryCheckTime, String countryCheckFilePath,
                                    String cityCheckFilePath, String cityCheckTime, String countyCheckFilePath,
                                    String countyCheckTime, String cityEstimateInvestment, String cityFactInvestment,
                                    String countryEstimateInvestment, String countryFactInvestment,
                                    String provinceEstimateInvestment, String provinceFactInvestment,
                                    String countyEstimateInvestment, String countyFactInvestment) {
        ProjectDetailEntity projectDetail = projectDetailRepository.findById(projectDetailId);
        projectDetail.setProjectName(projectName);
        projectDetail.setProvinceCheckFile(provinceCheckFilePath);
        projectDetail.setProvinceCheckTime(provinceCheckTime);
        projectDetail.setUpdateTime(timeStamp);
        projectDetail.setCountryCheckTime(countryCheckTime);
        projectDetail.setCountryCheckFile(countryCheckFilePath);
        projectDetail.setCityCheckFile(cityCheckFilePath);
        projectDetail.setCityCheckTime(cityCheckTime);
        projectDetail.setCountyCheckFile(countyCheckFilePath);
        projectDetail.setCountyCheckTime(countyCheckTime);
        projectDetail.setCityEstimateInvestment(cityEstimateInvestment);
        projectDetail.setCityFactInvestment(cityFactInvestment);
        projectDetail.setCountryEstimateInvestment(countryEstimateInvestment);
        projectDetail.setCountryFactInvestment(countryFactInvestment);
        projectDetail.setProvinceEstimateInvestment(provinceEstimateInvestment);
        projectDetail.setProvinceFactInvestment(provinceFactInvestment);
        projectDetail.setCountyFactInvestment(countyFactInvestment);
        projectDetail.setCountyEstimateInvestment(countyEstimateInvestment);
        projectDetail.setConstructTime(constructTime);
        projectDetail.setConstructUnit(constructUnit);
        projectDetail.setSumFactInvestment(sumFactInvestment);
        projectDetail.setSumEstimateInvestment(sumEstimateInvestment);
        projectDetail.setCountryApprovalFile(countryApprovalFilePath);
        projectDetail.setOthers(others);
        projectDetail.setCountryApprovalSymbol(countryApprovalSymbol);
        projectDetail.setCountryApprovalTime(countryApprovalTime);
        projectDetail.setProvinceApprovalFile(provinceApprovalFilePath);
        projectDetail.setProvinceApprovalSymbol(provinceApprovalSymbol);
        projectDetail.setProvinceApprovalTime(provinceApprovalTime);
        projectDetail.setCityApprovalFile(cityApprovalFilePath);
        projectDetail.setCityApprovalSymbol(cityApprovalSymbol);
        projectDetail.setCityApprovalTime(cityApprovalTime);
        projectDetail.setCountyApprovalFile(countyApprovalFilePath);
        projectDetail.setCountyApprovalSymbol(countyApprovalSymbol);
        projectDetail.setCountyApprovalTime(countyApprovalTime);
        projectDetail.setProvinceApprovalFile(provinceApprovalFilePath);
        projectDetail.setProvinceApprovalSymbol(provinceApprovalSymbol);
        projectDetail.setProvinceApprovalTime(provinceApprovalTime);
        projectDetailRepository.save(projectDetail);
    }
}
