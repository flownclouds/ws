package com.saltyfish.framework.controller;

import com.saltyfish.common.bean.Response;
import com.saltyfish.framework.service.AuthService;
import com.saltyfish.framework.service.FileService;
import com.saltyfish.framework.service.ProjectDetailService;
import com.saltyfish.framework.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by weck on 16/9/26.
 */
@RestController
@RequestMapping("/projectDetail")
public class ProjectDetailController {
    @Autowired
    private ProjectDetailService projectDetailService;

    @Autowired
    private AuthService authService;

    @Autowired
    private ResponseService responseService;

    @Autowired
    private FileService fileService;


    @RequestMapping("/deleteProjectDetail")
    private Response deleteProjectDetail(@RequestParam("userId") Integer userId,
                                         @RequestParam("token") String token,
                                         @RequestParam("timeStamp") Long timeStamp,
                                         @RequestParam("projectDetailId") String projectDetailId) {
        Response response = new Response();
        try {
            if (!authService.checkLogin(userId, token)) {
                return responseService.notLogin(response);
            } else if (!authService.checkAdmin(userId)) {
                return responseService.noAccess(response);
            } else {
                projectDetailService.deleteProjectDetail(projectDetailId, timeStamp);
                return responseService.success(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return responseService.serverError(response);
        }
    }

    @RequestMapping("/getProjectDetails")
    private Response getProjectDetails(@RequestParam("userId") Integer userId,
                                       @RequestParam("token") String token,
                                       @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                       @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        Response response = new Response();
        try {
            if (!authService.checkLogin(userId, token)) {
                return responseService.notLogin(response);
            } else if (!authService.checkAdmin(userId)) {
                return responseService.noAccess(response);
            } else {
                response.setCode(HttpStatus.OK.value());
                Map<String, Object> data = new HashMap<>();
                data.put("projectDetails", projectDetailService.getProjectDetails(page, size));
                response.setData(data);
                return response;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return responseService.serverError(response);
        }
    }

    @RequestMapping("/modifyProjectDetail")
    private Response modifyProjectDetail(@RequestParam("userId") Integer userId,
                                         @RequestParam("token") String token,
                                         @RequestParam("projectDetailId") String projectDetailId,
                                         @RequestParam("timeStamp") Long timeStamp,
                                         @RequestParam("projectName") String projectName,
                                         @RequestParam(value = "constructTime", required = false, defaultValue = "") String constructTime,
                                         @RequestParam(value = "constructUnit", required = false, defaultValue = "") String constructUnit,
                                         @RequestParam(value = "sumEstimateInvestment", required = false, defaultValue = "") String sumEstimateInvestment,
                                         @RequestParam(value = "sumFactInvestment", required = false, defaultValue = "") String sumFactInvestment,
                                         @RequestParam(value = "others", required = false, defaultValue = "") String others,
                                         @RequestParam(value = "countryApprovalSymbol", required = false, defaultValue = "") String countryApprovalSymbol,
                                         @RequestParam(value = "countryApprovalFile", required = false) MultipartFile countryApprovalFile,
                                         @RequestParam(value = "countryApprovalTime", required = false, defaultValue = "") String countryApprovalTime,
                                         @RequestParam(value = "provinceApprovalSymbol", required = false, defaultValue = "") String provinceApprovalSymbol,
                                         @RequestParam(value = "provinceApprovalFile", required = false) MultipartFile provinceApprovalFile,
                                         @RequestParam(value = "provinceApprovalTime", required = false, defaultValue = "") String provinceApprovalTime,
                                         @RequestParam(value = "cityApprovalSymbol", required = false, defaultValue = "") String cityApprovalSymbol,
                                         @RequestParam(value = "cityApprovalFile", required = false) MultipartFile cityApprovalFile,
                                         @RequestParam(value = "cityApprovalTime", required = false, defaultValue = "") String cityApprovalTime,
                                         @RequestParam(value = "countyApprovalSymbol", required = false, defaultValue = "") String countyApprovalSymbol,
                                         @RequestParam(value = "countyApprovalFile", required = false) MultipartFile countyApprovalFile,
                                         @RequestParam(value = "countyApprovalTime", required = false, defaultValue = "") String countyApprovalTime,
                                         @RequestParam(value = "countyCheckFile", required = false) MultipartFile countyCheckFile,
                                         @RequestParam(value = "countyCheckTime", required = false, defaultValue = "") String countyCheckTime,
                                         @RequestParam(value = "countryCheckFile", required = false) MultipartFile countryCheckFile,
                                         @RequestParam(value = "countryCheckTime", required = false, defaultValue = "") String countryCheckTime,
                                         @RequestParam(value = "provinceCheckFile", required = false) MultipartFile provinceCheckFile,
                                         @RequestParam(value = "provinceCheckTime", required = false, defaultValue = "") String provinceCheckTime,
                                         @RequestParam(value = "cityCheckFile", required = false) MultipartFile cityCheckFile,
                                         @RequestParam(value = "cityCheckTime", required = false, defaultValue = "") String cityCheckTime,
                                         @RequestParam(value = "cityEstimateInvestment", required = false, defaultValue = "") String cityEstimateInvestment,
                                         @RequestParam(value = "cityFactInvestment", required = false, defaultValue = "") String cityFactInvestment,
                                         @RequestParam(value = "countyEstimateInvestment", required = false, defaultValue = "") String countyEstimateInvestment,
                                         @RequestParam(value = "countyFactInvestment", required = false, defaultValue = "") String countyFactInvestment,
                                         @RequestParam(value = "countryEstimateInvestment", required = false, defaultValue = "") String countryEstimateInvestment,
                                         @RequestParam(value = "countryFactInvestment", required = false, defaultValue = "") String countryFactInvestment,
                                         @RequestParam(value = "provinceEstimateInvestment", required = false, defaultValue = "") String provinceEstimateInvestment,
                                         @RequestParam(value = "provinceFactInvestment", required = false, defaultValue = "") String provinceFactInvestment) {
        Response response = new Response();
        try {
            if (!authService.checkLogin(userId, token)) {
                return responseService.notLogin(response);
            } else if (!authService.checkAdmin(userId)) {
                return responseService.noAccess(response);
            } else {
                String countryApprovalFilePath = "";
                String provinceApprovalFilePath = "";
                String cityApprovalFilePath = "";
                String countyApprovalFilePath = "";
                String countryCheckFilePath = "";
                String provinceCheckFilePath = "";
                String cityCheckFilePath = "";
                String countyCheckFilePath = "";
                if (countryApprovalFile != null && !countryApprovalFile.isEmpty()) {
                    countryApprovalFilePath = fileService.saveFileToMongoDB(countryApprovalFile, timeStamp);
                }
                if (provinceApprovalFile != null && !provinceApprovalFile.isEmpty()) {
                    provinceApprovalFilePath = fileService.saveFileToMongoDB(provinceApprovalFile, timeStamp);
                }
                if (cityApprovalFile != null && !cityApprovalFile.isEmpty()) {
                    cityApprovalFilePath = fileService.saveFileToMongoDB(cityApprovalFile, timeStamp);
                }
                if (countyApprovalFile != null && !countyApprovalFile.isEmpty()) {
                    countyApprovalFilePath = fileService.saveFileToMongoDB(countyApprovalFile, timeStamp);
                }
                if (countryCheckFile != null && !countryCheckFile.isEmpty()) {
                    countryCheckFilePath = fileService.saveFileToMongoDB(countryCheckFile, timeStamp);
                }
                if (provinceCheckFile != null && !provinceCheckFile.isEmpty()) {
                    provinceCheckFilePath = fileService.saveFileToMongoDB(provinceCheckFile, timeStamp);
                }
                if (cityCheckFile != null && !cityCheckFile.isEmpty()) {
                    cityCheckFilePath = fileService.saveFileToMongoDB(cityCheckFile, timeStamp);
                }
                if (countyCheckFile != null && !countyCheckFile.isEmpty()) {
                    countyCheckFilePath = fileService.saveFileToMongoDB(countyCheckFile, timeStamp);
                }
                projectDetailService.modifyProjectDetail(projectDetailId, projectName, timeStamp, constructTime, constructUnit,
                        sumEstimateInvestment, sumFactInvestment, others, countryApprovalSymbol, countryApprovalTime,
                        countryApprovalFilePath, provinceApprovalSymbol, provinceApprovalTime, provinceApprovalFilePath,
                        cityApprovalSymbol, cityApprovalTime, cityApprovalFilePath, countyApprovalSymbol,
                        countyApprovalTime, countyApprovalFilePath, provinceCheckFilePath, provinceCheckTime,
                        countryCheckTime, countryCheckFilePath, cityCheckFilePath, cityCheckTime, countyCheckFilePath,
                        countyCheckTime, cityEstimateInvestment, cityFactInvestment, countryEstimateInvestment,
                        countryFactInvestment, provinceEstimateInvestment, provinceFactInvestment, countyEstimateInvestment,
                        countyFactInvestment);
                return responseService.success(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return responseService.saveFileError(response);
        } catch (Exception e) {
            e.printStackTrace();
            return responseService.serverError(response);
        }
    }


    @RequestMapping("/addProjectDetail")
    private Response addProjectDetail(@RequestParam("userId") Integer userId,
                                      @RequestParam("token") String token,
                                      @RequestParam("timeStamp") Long timeStamp,
                                      @RequestParam("projectName") String projectName,
                                      @RequestParam(value = "constructTime", required = false, defaultValue = "") String constructTime,
                                      @RequestParam(value = "constructUnit", required = false, defaultValue = "") String constructUnit,
                                      @RequestParam(value = "sumEstimateInvestment", required = false, defaultValue = "") String sumEstimateInvestment,
                                      @RequestParam(value = "sumFactInvestment", required = false, defaultValue = "") String sumFactInvestment,
                                      @RequestParam(value = "others", required = false, defaultValue = "") String others,
                                      @RequestParam(value = "countryApprovalSymbol", required = false, defaultValue = "") String countryApprovalSymbol,
                                      @RequestParam(value = "countryApprovalFile", required = false) MultipartFile countryApprovalFile,
                                      @RequestParam(value = "countryApprovalTime", required = false, defaultValue = "") String countryApprovalTime,
                                      @RequestParam(value = "provinceApprovalSymbol", required = false, defaultValue = "") String provinceApprovalSymbol,
                                      @RequestParam(value = "provinceApprovalFile", required = false) MultipartFile provinceApprovalFile,
                                      @RequestParam(value = "provinceApprovalTime", required = false, defaultValue = "") String provinceApprovalTime,
                                      @RequestParam(value = "cityApprovalSymbol", required = false, defaultValue = "") String cityApprovalSymbol,
                                      @RequestParam(value = "cityApprovalFile", required = false) MultipartFile cityApprovalFile,
                                      @RequestParam(value = "cityApprovalTime", required = false, defaultValue = "") String cityApprovalTime,
                                      @RequestParam(value = "countyApprovalSymbol", required = false, defaultValue = "") String countyApprovalSymbol,
                                      @RequestParam(value = "countyApprovalFile", required = false) MultipartFile countyApprovalFile,
                                      @RequestParam(value = "countyApprovalTime", required = false, defaultValue = "") String countyApprovalTime,
                                      @RequestParam(value = "countyCheckFile", required = false) MultipartFile countyCheckFile,
                                      @RequestParam(value = "countyCheckTime", required = false, defaultValue = "") String countyCheckTime,
                                      @RequestParam(value = "countryCheckFile", required = false) MultipartFile countryCheckFile,
                                      @RequestParam(value = "countryCheckTime", required = false, defaultValue = "") String countryCheckTime,
                                      @RequestParam(value = "provinceCheckFile", required = false) MultipartFile provinceCheckFile,
                                      @RequestParam(value = "provinceCheckTime", required = false, defaultValue = "") String provinceCheckTime,
                                      @RequestParam(value = "cityCheckFile", required = false) MultipartFile cityCheckFile,
                                      @RequestParam(value = "cityCheckTime", required = false, defaultValue = "") String cityCheckTime,
                                      @RequestParam(value = "cityEstimateInvestment", required = false, defaultValue = "") String cityEstimateInvestment,
                                      @RequestParam(value = "cityFactInvestment", required = false, defaultValue = "") String cityFactInvestment,
                                      @RequestParam(value = "countyEstimateInvestment", required = false, defaultValue = "") String countyEstimateInvestment,
                                      @RequestParam(value = "countyFactInvestment", required = false, defaultValue = "") String countyFactInvestment,
                                      @RequestParam(value = "countryEstimateInvestment", required = false, defaultValue = "") String countryEstimateInvestment,
                                      @RequestParam(value = "countryFactInvestment", required = false, defaultValue = "") String countryFactInvestment,
                                      @RequestParam(value = "provinceEstimateInvestment", required = false, defaultValue = "") String provinceEstimateInvestment,
                                      @RequestParam(value = "provinceFactInvestment", required = false, defaultValue = "") String provinceFactInvestment) {
        Response response = new Response();
        try {
            if (!authService.checkLogin(userId, token)) {
                return responseService.notLogin(response);
            } else if (!authService.checkAdmin(userId)) {
                return responseService.noAccess(response);
            } else {
                String countryApprovalFilePath = "";
                String provinceApprovalFilePath = "";
                String cityApprovalFilePath = "";
                String countyApprovalFilePath = "";
                String countryCheckFilePath = "";
                String provinceCheckFilePath = "";
                String cityCheckFilePath = "";
                String countyCheckFilePath = "";
                if (countryApprovalFile != null && !countryApprovalFile.isEmpty()) {
                    countryApprovalFilePath = fileService.saveFileToMongoDB(countryApprovalFile, timeStamp);
                }
                if (provinceApprovalFile != null && !provinceApprovalFile.isEmpty()) {
                    provinceApprovalFilePath = fileService.saveFileToMongoDB(provinceApprovalFile, timeStamp);
                }
                if (cityApprovalFile != null && !cityApprovalFile.isEmpty()) {
                    cityApprovalFilePath = fileService.saveFileToMongoDB(cityApprovalFile, timeStamp);
                }
                if (countyApprovalFile != null && !countyApprovalFile.isEmpty()) {
                    countyApprovalFilePath = fileService.saveFileToMongoDB(countyApprovalFile, timeStamp);
                }
                if (countryCheckFile != null && !countryCheckFile.isEmpty()) {
                    countryCheckFilePath = fileService.saveFileToMongoDB(countryCheckFile, timeStamp);
                }
                if (provinceCheckFile != null && !provinceCheckFile.isEmpty()) {
                    provinceCheckFilePath = fileService.saveFileToMongoDB(provinceCheckFile, timeStamp);
                }
                if (cityCheckFile != null && !cityCheckFile.isEmpty()) {
                    cityCheckFilePath = fileService.saveFileToMongoDB(cityCheckFile, timeStamp);
                }
                if (countyCheckFile != null && !countyCheckFile.isEmpty()) {
                    countyCheckFilePath = fileService.saveFileToMongoDB(countyCheckFile, timeStamp);
                }

                projectDetailService.addProjectDetail(projectName, timeStamp, constructTime, constructUnit,
                        sumEstimateInvestment, sumFactInvestment, others, countryApprovalSymbol, countryApprovalTime,
                        countryApprovalFilePath, provinceApprovalSymbol, provinceApprovalTime, provinceApprovalFilePath,
                        cityApprovalSymbol, cityApprovalTime, cityApprovalFilePath, countyApprovalSymbol,
                        countyApprovalTime, countyApprovalFilePath, provinceCheckFilePath, provinceCheckTime,
                        countryCheckTime, countryCheckFilePath, cityCheckFilePath, cityCheckTime, countyCheckFilePath,
                        countyCheckTime, cityEstimateInvestment, cityFactInvestment, countryEstimateInvestment,
                        countryFactInvestment, provinceEstimateInvestment, provinceFactInvestment, countyEstimateInvestment,
                        countyFactInvestment);
                return responseService.success(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return responseService.saveFileError(response);
        } catch (Exception e) {
            e.printStackTrace();
            return responseService.serverError(response);
        }
    }
}
