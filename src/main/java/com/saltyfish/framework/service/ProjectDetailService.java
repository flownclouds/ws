package com.saltyfish.framework.service;

import com.saltyfish.domain.entity.project.ProjectDetailEntity;
import com.saltyfish.domain.entity.project.projectdetail.ApprovalEntity;
import com.saltyfish.domain.entity.project.projectdetail.CheckEntity;
import com.saltyfish.domain.entity.project.projectdetail.InvestmentEntity;
import com.saltyfish.domain.repository.project.ProjectDetailRepository;
import com.saltyfish.domain.repository.project.projectDetail.ApprovalRepository;
import com.saltyfish.domain.repository.project.projectDetail.CheckRepository;
import com.saltyfish.domain.repository.project.projectDetail.InvestmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by weck on 16/9/19.
 */
@Service
public class ProjectDetailService {

    @Autowired
    private ProjectDetailRepository projectDetailRepository;

    @Autowired
    private ApprovalRepository approvalRepository;

    @Autowired
    private CheckRepository checkRepository;

    @Autowired
    private InvestmentRepository investmentRepository;

    public Integer saveCommonItems(String projectName, String constructTime, String constructUnit, String sumEstimateInvestment, String sumFactInvestment, String others, Long timeStamp) {
        ProjectDetailEntity projectDetail = new ProjectDetailEntity();
        Timestamp time = new Timestamp(timeStamp);
        projectDetail.setProjectName(projectName);
        projectDetail.setConstructUnit(constructUnit);
        projectDetail.setConstructTime(constructTime);
        projectDetail.setSumEstimateInvestment(sumEstimateInvestment);
        projectDetail.setSumFactInvestment(sumFactInvestment);
        projectDetail.setOthers(others);
        projectDetail.setIsDelete(0);
        projectDetail.setUpdateTime(time);
        projectDetail.setCreateTime(time);
        projectDetailRepository.save(projectDetail);
        return projectDetail.getId();
    }

    public void saveApproval(Integer projectDetailId, String filePath, String grade, String symbol, String approvalTime, Long timeStamp) {
        ProjectDetailEntity projectDetail = projectDetailRepository.findById(projectDetailId);
        ApprovalEntity approval = new ApprovalEntity();
        Timestamp time = new Timestamp(timeStamp);
        approval.setCreateTime(time);
        approval.setUpdateTime(time);
        approval.setIsDelete(0);
        approval.setFile(filePath);
        approval.setGrade(grade);
        approval.setSymbol(symbol);
        approval.setTime(approvalTime);
        approvalRepository.save(approval);
        List<ApprovalEntity> approvals = projectDetail.getApprovals();
        approvals.add(approval);
        projectDetail.setApprovals(approvals);
        projectDetail.setUpdateTime(time);
        projectDetailRepository.save(projectDetail);
    }

    public void saveCheck(Integer projectDetailId, String filePath, String grade, String checkTime, Long timeStamp) {
        ProjectDetailEntity projectDetail = projectDetailRepository.findById(projectDetailId);
        CheckEntity check = new CheckEntity();
        Timestamp time = new Timestamp(timeStamp);
        check.setUpdateTime(time);
        check.setCreateTime(time);
        check.setGrade(grade);
        check.setIsDelete(0);
        check.setFile(filePath);
        check.setTime(checkTime);
        checkRepository.save(check);
        List<CheckEntity> checks = projectDetail.getChecks();
        checks.add(check);
        projectDetail.setChecks(checks);
        projectDetail.setUpdateTime(time);
        projectDetailRepository.save(projectDetail);
    }

    public void saveInvestment(Integer projectDetailId, String invest, String investResource, String category, Long timeStamp) {
        ProjectDetailEntity projectDetail = projectDetailRepository.findById(projectDetailId);
        InvestmentEntity investment = new InvestmentEntity();
        Timestamp time = new Timestamp(timeStamp);
        investment.setUpdateTime(time);
        investment.setIsDelete(0);
        investment.setCreateTime(time);
        investment.setCategory(category);
        investment.setInvest(invest);
        investment.setInvestResource(investResource);
        investmentRepository.save(investment);
        List<InvestmentEntity> investments = projectDetail.getInvestments();
        investments.add(investment);
        projectDetail.setInvestments(investments);
        projectDetail.setUpdateTime(time);
        projectDetailRepository.save(projectDetail);
    }

    public void deleteProjectDetail(Integer projectDetailId, Long timeStamp) {
        ProjectDetailEntity projectDetail = projectDetailRepository.findById(projectDetailId);
        Timestamp time = new Timestamp(timeStamp);
        projectDetail.setIsDelete(0);
        if (projectDetail.getApprovals() != null) {
            approvalRepository.delete(projectDetail.getApprovals());
        }
        if (projectDetail.getInvestments() != null) {
            investmentRepository.delete(projectDetail.getInvestments());
        }
        if (projectDetail.getChecks() != null) {
            checkRepository.delete(projectDetail.getChecks());
        }
        projectDetail.setUpdateTime(time);
        projectDetailRepository.save(projectDetail);
    }

    public void modifyProjectDetail(Integer projectDetailId, String projectName, String constructTime, String constructUnit,
                                    String sumEstimateInvestment, String sumFactInvestment, Long timeStamp, String others) {
        ProjectDetailEntity projectDetail = projectDetailRepository.findById(projectDetailId);
        Timestamp time = new Timestamp(timeStamp);
        projectDetail.setUpdateTime(time);
        projectDetail.setOthers(others);
        projectDetail.setSumFactInvestment(sumFactInvestment);
        projectDetail.setConstructTime(constructTime);
        projectDetail.setProjectName(projectName);
        projectDetail.setSumEstimateInvestment(sumEstimateInvestment);
        projectDetail.setConstructUnit(constructUnit);
        projectDetailRepository.save(projectDetail);
    }

    public void deleteApproval(Integer projectDetailId, Integer approvalId, Long timeStamp) {
        ProjectDetailEntity projectDetail = projectDetailRepository.findById(projectDetailId);
        Timestamp time = new Timestamp(timeStamp);
        projectDetail.setUpdateTime(time);
        approvalRepository.delete(approvalId);
        projectDetailRepository.save(projectDetail);
    }

    public void deleteCheck(Integer projectDetailId, Integer checkId, Long timeStamp) {
        Timestamp time = new Timestamp(timeStamp);
        ProjectDetailEntity projectDetail = projectDetailRepository.findById(projectDetailId);
        projectDetail.setUpdateTime(time);
        checkRepository.delete(checkId);
        projectDetailRepository.save(projectDetail);
    }

    public void deleteInvestment(Integer projectDetailId, Integer investmentId, Long timeStamp) {
        ProjectDetailEntity projectDetail = projectDetailRepository.findById(projectDetailId);
        Timestamp time = new Timestamp(timeStamp);
        investmentRepository.delete(investmentId);
        projectDetail.setUpdateTime(time);
        projectDetailRepository.save(projectDetail);
    }
}
