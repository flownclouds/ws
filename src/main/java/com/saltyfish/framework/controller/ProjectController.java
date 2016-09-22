package com.saltyfish.framework.controller;

import com.saltyfish.common.bean.Response;
import com.saltyfish.domain.entity.project.WaterConservationEntity;
import com.saltyfish.domain.entity.project.conservation.*;
import com.saltyfish.domain.entity.project.mark.ProjectMarkEntity;
import com.saltyfish.domain.repository.project.WaterConservationRepository;
import com.saltyfish.framework.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by weck on 16/9/4.
 * 工程操作请求
 */
@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private FileService fileService;

    @Autowired
    private AuthService authService;

    @Autowired
    private ResponseService responseService;

    @Autowired
    private WaterConservationRepository waterConservationRepository;

    @Autowired
    private UnitService unitService;

    /**
     * 查询单个工程
     *
     * @param userId    用户id
     * @param token     登录token
     * @param projectId 工程id
     * @return 工程
     */
    @RequestMapping("/getProject")
    public Response getProject(@RequestParam("userId") Integer userId,
                               @RequestParam("token") String token,
                               @RequestParam("projectId") Integer projectId) {
        Response response = new Response();
        try {
            if (!authService.checkLogin(userId, token)) {
                return responseService.notLogin(response);
            } else if (!authService.checkUserProjectTownAccess(userId, projectId)) {
                return responseService.noAccess(response);
            } else {
                Map<String, Object> data = new HashMap<>();
                data.put("project", projectService.getConservation(projectId));
                response.setCode(HttpStatus.OK.value());
                response.setData(data);
                return response;
            }
        } catch (Exception e) {
            return responseService.serverError(response);
        }
    }

    /**
     * 删除工程
     *
     * @param userId    用户id
     * @param token     登录token
     * @param projectId 工程id
     * @param timeStamp 时间戳
     * @return 操作结果
     */
    @RequestMapping("/deleteProject")
    public Response deleteProject(@RequestParam("userId") Integer userId,
                                  @RequestParam("token") String token,
                                  @RequestParam("projectId") Integer projectId,
                                  @RequestParam("timeStamp") Long timeStamp) {
        Response response = new Response();
        try {
            if (!authService.checkLogin(userId, token)) {
                return responseService.notLogin(response);
            } else if (!authService.checkUserProjectTownAccess(userId, projectId)) {
                return responseService.noAccess(response);
            } else {
                projectService.deleteProject(projectId, timeStamp);
                return responseService.success(response);
            }
        } catch (Exception e) {
            return responseService.serverError(response);
        }
    }


    /**
     * 获取设施分页列表
     *
     * @param userId   用户id
     * @param token    登录token
     * @param category 工程种类
     * @return 工程page
     */
    @RequestMapping("/getConservations")
    public Response getConservations(@RequestParam(value = "startDate", required = false) Long startDate,
                                     @RequestParam(value = "endDate", required = false) Long endDate,
                                     @RequestParam(value = "code", required = false) String code,
                                     @RequestParam(value = "name", required = false) String name,
                                     @RequestParam("userId") Integer userId,
                                     @RequestParam("token") String token,
                                     @RequestParam(value = "category", required = false) String category,
                                     @RequestParam(value = "townId", required = false) Integer townId,
                                     @RequestParam(value = "villageId", required = false) Integer villageId,
                                     @RequestParam(value = "groupId", required = false) Integer groupId,
                                     @RequestParam(value = "manageModel", required = false) String manageModel,
                                     @RequestParam(value = "situation", required = false) String situation,
                                     @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                     @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        Response response = new Response();
        try {
            if (!authService.checkLogin(userId, token)) {
                return responseService.notLogin(response);
            } else {
                response.setCode(HttpStatus.OK.value());
                Map<String, Object> data = new HashMap<>();
                List<Integer> townIds = unitService.getAccessedTownIds(userId);
                if (category == null) {
                    data.put("conservations", projectService.getConservations(townIds, page, size));
                }
                if (startDate == null && endDate == null && code == null && name == null) {
                    if (situation == null) {
                        if (manageModel == null) {
                            if (townId == null && villageId == null && groupId == null) {
                                data.put("conservations", projectService.getConservationsByCategory(townIds, category, page, size));
                                response.setData(data);
                            } else if (townId != null && villageId == null && groupId == null) {
                                data.put("conservations", projectService.getConservationsByCategoryAndTownId(category, townId, page, size));
                                response.setData(data);
                            } else if (townId != null && villageId != null && groupId == null) {
                                data.put("conservations", projectService.getConservationsByCategoryAndVillageId(category, villageId, page, size));
                                response.setData(data);
                            } else if (townId != null && villageId != null) {
                                data.put("conservations", projectService.getConservationsByCategoryAndGroupId(category, groupId, page, size));
                                response.setData(data);
                            }
                        } else {
                            if (townId == null && villageId == null && groupId == null) {
                                data.put("conservations", projectService.getConservationsByCategoryAndManageModel(manageModel, townIds, category, page, size));
                                response.setData(data);
                            } else if (townId != null && villageId == null && groupId == null) {
                                data.put("conservations", projectService.getConservationsByCategoryAndManageModelAndTownId(manageModel, category, townId, page, size));
                                response.setData(data);
                            } else if (townId != null && villageId != null && groupId == null) {
                                data.put("conservations", projectService.getConservationsByManageModelAndCategoryAndVillageId(manageModel, category, villageId, page, size));
                                response.setData(data);
                            } else if (townId != null && villageId != null) {
                                data.put("conservations", projectService.getConservationsByManageModelAndCategoryAndGroupId(manageModel, category, groupId, page, size));
                                response.setData(data);
                            }
                        }
                    } else {
                        if (manageModel == null) {
                            if (townId == null && villageId == null && groupId == null) {
                                data.put("conservations", projectService.getConservationsByCategoryAndSituation(situation, townIds, category, page, size));
                                response.setData(data);
                            } else if (townId != null && villageId == null && groupId == null) {
                                data.put("conservations", projectService.getConservationsByCategoryAndSituationAndTownId(situation, category, townId, page, size));
                                response.setData(data);
                            } else if (townId != null && villageId != null && groupId == null) {
                                data.put("conservations", projectService.getConservationsBySituationAndCategoryAndVillageId(situation, category, villageId, page, size));
                                response.setData(data);
                            } else if (townId != null && villageId != null) {
                                data.put("conservations", projectService.getConservationsBySituationAndCategoryAndGroupId(situation, category, groupId, page, size));
                                response.setData(data);
                            }
                        } else {
                            if (townId == null && villageId == null && groupId == null) {
                                data.put("conservations", projectService.getConservationsByCategoryAndSituationAndManageModel(situation, manageModel, townIds, category, page, size));
                                response.setData(data);
                            } else if (townId != null && villageId == null && groupId == null) {
                                data.put("conservations", projectService.getConservationsByCategoryAndSituationAndManageModelAndTownId(situation, manageModel, category, townId, page, size));
                                response.setData(data);
                            } else if (townId != null && villageId != null && groupId == null) {
                                data.put("conservations", projectService.getConservationsBySituationAndCategoryAndManageModelAndVillageId(situation, manageModel, category, villageId, page, size));
                                response.setData(data);
                            } else if (townId != null && villageId != null) {
                                data.put("conservations", projectService.getConservationsBySituationAndCategoryAndManageModelAndGroupId(situation, manageModel, category, groupId, page, size));
                                response.setData(data);
                            }
                        }
                    }
                } else {
                    if (startDate != null && endDate != null) {
                        if (code != null) {
                            if (name != null) {
                                data.put("conservations", projectService.getConservationsByCategoryAndName(townIds, category, name, page, size));
                                response.setData(data);
                            } else {
                                data.put("conservations", projectService.getConservationsByCategoryAndCode(townIds, category, code, page, size));
                                response.setData(data);
                            }
                        } else {
                            data.put("conservations", projectService.getConservationsByCategoryAndDate(townIds, category, startDate, endDate, page, size));
                            response.setData(data);
                        }
                    }
                }
                return response;
            }
        } catch (Exception e) {
            return responseService.serverError(response);
        }
    }

    /**
     * @param userId                   用户id
     * @param token                    登录token
     * @param category                 工程种类
     * @param townId                   乡镇id
     * @param villageId                村庄id
     * @param groupId                  组id
     * @param code                     工程编码
     * @param name                     工程名字
     * @param remark                   备注
     * @param manageModel              管理模式
     * @param situation                完好情况
     * @param constructTime            修建/改造时间
     * @param constructUnit            修建/改造单位
     * @param propertyOwner            产权人
     * @param manager                  管理人
     * @param crossWatercourseLocation 所跨河道
     * @param crossCount               跨数
     * @param sectionSize              断面尺寸
     * @param structureAndMaterial     结构和材质
     * @param crossLength              每跨长
     * @param length                   总长
     * @param width                    宽度
     * @param watercourseLocation      说在河道
     * @param loadStandard             负载标准
     * @param headOrPumpStation        渠首或者取水泵站
     * @param buildingMatchRate        建筑物配套率
     * @param goodConditionRate        渠道及建筑物完好率
     * @param sumLength                总计长度
     * @param culvertModel             涵洞类型
     * @param holeModel                洞省类型
     * @param doorMaterial             洞口闸门材质
     * @param holeMaterial             洞身材质
     * @param hoistModel               洞口启闭机类型
     * @param isRegistered             是否登记注册
     * @param features                 特性
     * @param isAccountability         是否建立三级责任制
     * @param feeResources             主要运行经费来源
     * @param maintainPersonFee        聘用管护人员工资
     * @param isCertificated           是否持证上岗
     * @param development              生产开发
     * @param manageRageLine           管理范围线
     * @param pondDamManageLine        塘坝管理范围线
     * @param protectGround            护坝地
     * @param twoLinesBuilding         理论二线内建筑物
     * @param specifiedManage          规范化管理
     * @param irrigateArea             灌溉面积
     * @param deepPump                 深水泵
     * @param diameter                 直径
     * @param depth                    深度
     * @param material                 材质
     * @param intakeWay                取水方式
     * @param waterResource            水源
     * @param sumDiameter              总直径
     * @param waterCapacity            储水量
     * @param size                     尺寸
     * @param modelAndMaterial         型号和材质
     * @param paddyFieldArea           水田面积
     * @param drainageArea             排涝面积
     * @param irrigateFee              灌溉收费
     * @param drainageFee              排涝收费
     * @param annualFee                年收费
     * @param machineArea              机房面积
     * @param affiliation              隶属关系
     * @param sumElectricCapacity      总装机容量
     * @param averageCapacity          平均容量
     * @param problem                  存在的问题
     * @param lastDredgingTime         上次疏浚时间
     * @param waterArea                水面积
     * @param model                    型号
     * @param holeCount                单孔数
     * @param door                     闸门
     * @param hoistTonnage             启闭机吨位
     * @param holeHeight               单孔高
     * @param holeWidth                单孔宽
     * @param doorHeight               闸门高
     * @param doorWidth                闸门宽
     * @param buildingSituation        建筑完好情况
     * @param doorSituation            闸门完好情况
     * @param hoistSituation           启闭机完好情况
     * @param estuaryWidth             河口宽
     * @param estuaryHeight            河口高
     * @param hediWidth                河底宽
     * @param hediHeight               河底高
     * @param leftWidth                左岸宽
     * @param rightWidth               右岸宽
     * @param flowVillages             流经村庄
     * @param nature                   性质
     * @param longitude                经度
     * @param latitude                 纬度
     * @param endpointLongitude        终点经度
     * @param endpointLatitude         终点纬度
     * @param provideAmount            日供水量
     * @param waterModel               水源类型
     * @param haveCleaner              有无净化设施
     * @param isRegularCheck           是否定期检测
     * @param dayProvideAmount         日供水量
     * @param provideVillageCount      供水村庄数量
     * @param providePopulation        供水人口
     * @param haveProtectArea          是否建立水源保护区
     * @param generatorModel1          发电机类型
     * @param generatorPower1          发电机单台功率*台数
     * @param generatorFactoryDate1    发电机出厂日期
     * @param turbineModel1            水轮机类型
     * @param turbineCount1            水轮机台数
     * @param turbineTurnsOrFlow1      水轮机转数/流量
     * @param turbineFactoryDate1      水轮机出厂日期
     * @param transformerModel1        变压器类型
     * @param transformerCapacity1     变压器单机容量*台数
     * @param pumpCount1               水泵台数
     * @param pumpLiftOrFlow1          水泵扬程/流量
     * @param pumpFactoryDate1         水泵出厂日期
     * @param pumpModel1               水泵类型
     * @param electricMotorModel1      电动机类型
     * @param electricPower1           电动机单台功率*台数
     * @param electricFactoryDate1     电动机出厂日期
     * @param pipeModel1               管道类型
     * @param pipeLength1              管道长
     * @param pipeDiameter1            管道直径
     * @param pipeMaterial1            管道材质
     * @param canalModel1              沟渠类型
     * @param canalLength1             沟渠长度
     * @param canalSeepageLength1      沟渠防渗渠长度
     * @param canalLiningSectionSize1  沟渠砌断面尺寸
     * @param canalLiningMaterial1     沟渠衬砌材质
     * @return 操作结果
     */
    @RequestMapping("/addConservation")
    public Response addConservation(@RequestParam("userId") Integer userId,
                                    @RequestParam("token") String token,
                                    @RequestParam("category") String category,
                                    @RequestParam("townId") Integer townId,
                                    @RequestParam("villageId") Integer villageId,
                                    @RequestParam(value = "groupId", required = false) Integer groupId,
                                    @RequestParam("code") String code,
                                    @RequestParam("name") String name,
                                    @RequestParam(value = "remark", required = false, defaultValue = "") String remark,
                                    @RequestParam(value = "manageModel", required = false, defaultValue = "自管") String manageModel,
                                    @RequestParam(value = "situation", required = false, defaultValue = "完好") String situation,
                                    @RequestParam(value = "constructTime", required = false, defaultValue = "") String constructTime,
                                    @RequestParam(value = "constructUnit", required = false, defaultValue = "") String constructUnit,
                                    @RequestParam(value = "propertyOwner", required = false, defaultValue = "") String propertyOwner,
                                    @RequestParam(value = "manager", required = false, defaultValue = "") String manager,
                                    @RequestParam(value = "crossWatercourseLocation", required = false, defaultValue = "") String crossWatercourseLocation,
                                    @RequestParam(value = "crossCount", required = false, defaultValue = "") String crossCount,
                                    @RequestParam(value = "sectionSize", required = false, defaultValue = "") String sectionSize,
                                    @RequestParam(value = "structureAndMaterial", required = false, defaultValue = "") String structureAndMaterial,
                                    @RequestParam(value = "image", required = false) MultipartFile image,
                                    @RequestParam(value = "crossLength", required = false, defaultValue = "") String crossLength,
                                    @RequestParam(value = "length", required = false, defaultValue = "") String length,
                                    @RequestParam(value = "width", required = false, defaultValue = "") String width,
                                    @RequestParam(value = "watercourseLocation", required = false, defaultValue = "") String watercourseLocation,
                                    @RequestParam(value = "loadStandard", required = false, defaultValue = "") String loadStandard,
                                    @RequestParam(value = "headOrPumpStation", required = false, defaultValue = "") String headOrPumpStation,
                                    @RequestParam(value = "buildingMatchRate", required = false, defaultValue = "") String buildingMatchRate,
                                    @RequestParam(value = "goodConditionRate", required = false, defaultValue = "") String goodConditionRate,
                                    @RequestParam(value = "seepageCanalLength", required = false, defaultValue = "") String seepageCanalLength,
                                    @RequestParam(value = "liningSectionSize", required = false, defaultValue = "") String liningSectionSize,
                                    @RequestParam(value = "sumLength", required = false, defaultValue = "") String sumLength,
                                    @RequestParam(value = "sumLiningSectionSize", required = false, defaultValue = "") String sumLiningSectionSize,
                                    @RequestParam(value = "sumSeepageCanalLength", required = false, defaultValue = "") String sumSeepageCanalLength,
                                    @RequestParam(value = "sumSectionSize", required = false, defaultValue = "") String sumSectionSize,
                                    @RequestParam(value = "planeSketch", required = false) MultipartFile planeSketch,
                                    @RequestParam(value = "culvertModel", required = false, defaultValue = "") String culvertModel,
                                    @RequestParam(value = "holeModel", required = false, defaultValue = "") String holeModel,
                                    @RequestParam(value = "doorMaterial", required = false, defaultValue = "") String doorMaterial,
                                    @RequestParam(value = "holeMaterial", required = false, defaultValue = "") String holeMaterial,
                                    @RequestParam(value = "hoistModel", required = false, defaultValue = "") String hoistModel,
                                    @RequestParam(value = "isRegistered", required = false, defaultValue = "") String isRegistered,
                                    @RequestParam(value = "features", required = false, defaultValue = "否") String features,
                                    @RequestParam(value = "isAccountability", required = false, defaultValue = "否") String isAccountability,
                                    @RequestParam(value = "feeResources", required = false, defaultValue = "") String feeResources,
                                    @RequestParam(value = "maintainPersonFee", required = false, defaultValue = "") String maintainPersonFee,
                                    @RequestParam(value = "isCertificated", required = false, defaultValue = "否") String isCertificated,
                                    @RequestParam(value = "development", required = false, defaultValue = "") String development,
                                    @RequestParam(value = "manageRageLine", required = false, defaultValue = "") String manageRageLine,
                                    @RequestParam(value = "pondDamManageLine", required = false, defaultValue = "") String pondDamManageLine,
                                    @RequestParam(value = "protectGround", required = false, defaultValue = "") String protectGround,
                                    @RequestParam(value = "twoLinesBuilding", required = false, defaultValue = "") String twoLinesBuilding,
                                    @RequestParam(value = "specifiedManage", required = false, defaultValue = "") String specifiedManage,
                                    @RequestParam(value = "irrigateArea", required = false, defaultValue = "") String irrigateArea,
                                    @RequestParam(value = "protectRageManagement", required = false, defaultValue = "") String protectRageManagement,
                                    @RequestParam(value = "deepPump", required = false, defaultValue = "") String deepPump,
                                    @RequestParam(value = "diameter", required = false, defaultValue = "") String diameter,
                                    @RequestParam(value = "depth", required = false, defaultValue = "") String depth,
                                    @RequestParam(value = "material", required = false, defaultValue = "") String material,
                                    @RequestParam(value = "intakeWay", required = false, defaultValue = "") String intakeWay,
                                    @RequestParam(value = "waterResource", required = false, defaultValue = "") String waterResource,
                                    @RequestParam(value = "sumDiameter", required = false, defaultValue = "") String sumDiameter,
                                    @RequestParam(value = "waterCapacity", required = false, defaultValue = "") String waterCapacity,
                                    @RequestParam(value = "size", required = false, defaultValue = "") String size,
                                    @RequestParam(value = "modelAndMaterial", required = false, defaultValue = "") String modelAndMaterial,
                                    @RequestParam(value = "paddyFieldArea", required = false, defaultValue = "") String paddyFieldArea,
                                    @RequestParam(value = "drainageArea", required = false, defaultValue = "") String drainageArea,
                                    @RequestParam(value = "irrigateFee", required = false, defaultValue = "") String irrigateFee,
                                    @RequestParam(value = "drainageFee", required = false, defaultValue = "") String drainageFee,
                                    @RequestParam(value = "annualFee", required = false, defaultValue = "") String annualFee,
                                    @RequestParam(value = "machineArea", required = false, defaultValue = "") String machineArea,
                                    @RequestParam(value = "affiliation", required = false, defaultValue = "") String affiliation,
                                    @RequestParam(value = "sumElectricCapacity", required = false, defaultValue = "") String sumElectricCapacity,
                                    @RequestParam(value = "averageCapacity", required = false, defaultValue = "") String averageCapacity,
                                    @RequestParam(value = "internalImage", required = false) MultipartFile internalImage,
                                    @RequestParam(value = "externalImage", required = false) MultipartFile externalImage,
                                    @RequestParam(value = "problem", required = false, defaultValue = "") String problem,
                                    @RequestParam(value = "lastDredgingTime", required = false, defaultValue = "") String lastDredgingTime,
                                    @RequestParam(value = "waterArea", required = false, defaultValue = "") String waterArea,
                                    @RequestParam(value = "model", required = false, defaultValue = "") String model,
                                    @RequestParam(value = "holeCount", required = false, defaultValue = "") String holeCount,
                                    @RequestParam(value = "door", required = false, defaultValue = "") String door,
                                    @RequestParam(value = "hoistTonnage", required = false, defaultValue = "") String hoistTonnage,
                                    @RequestParam(value = "holeHeight", required = false, defaultValue = "") String holeHeight,
                                    @RequestParam(value = "holeWidth", required = false, defaultValue = "") String holeWidth,
                                    @RequestParam(value = "doorHeight", required = false, defaultValue = "") String doorHeight,
                                    @RequestParam(value = "doorWidth", required = false, defaultValue = "") String doorWidth,
                                    @RequestParam(value = "buildingSituation", required = false, defaultValue = "完好") String buildingSituation,
                                    @RequestParam(value = "doorSituation", required = false, defaultValue = "") String doorSituation,
                                    @RequestParam(value = "hoistSituation", required = false, defaultValue = "") String hoistSituation,
                                    @RequestParam(value = "estuaryWidth", required = false, defaultValue = "") String estuaryWidth,
                                    @RequestParam(value = "estuaryHeight", required = false, defaultValue = "") String estuaryHeight,
                                    @RequestParam(value = "hediWidth", required = false, defaultValue = "") String hediWidth,
                                    @RequestParam(value = "hediHeight", required = false, defaultValue = "") String hediHeight,
                                    @RequestParam(value = "leftWidth", required = false, defaultValue = "") String leftWidth,
                                    @RequestParam(value = "rightWidth", required = false, defaultValue = "") String rightWidth,
                                    @RequestParam(value = "flowVillages", required = false, defaultValue = "") String flowVillages,
                                    @RequestParam(value = "nature", required = false, defaultValue = "") String nature,
                                    @RequestParam(value = "sectionImage", required = false) MultipartFile sectionImage,
                                    @RequestParam(value = "longitude", required = false, defaultValue = "0.0") String longitude,
                                    @RequestParam(value = "latitude", required = false, defaultValue = "0.0") String latitude,
                                    @RequestParam(value = "endpointLongitude", required = false, defaultValue = "0.0") String endpointLongitude,
                                    @RequestParam(value = "endpointLatitude", required = false, defaultValue = "0.0") String endpointLatitude,
                                    @RequestParam(value = "startImage", required = false) MultipartFile startImage,
                                    @RequestParam(value = "middleImage", required = false) MultipartFile middleImage,
                                    @RequestParam(value = "endImage", required = false) MultipartFile endImage,
                                    @RequestParam(value = "provideAmount", required = false, defaultValue = "") String provideAmount,
                                    @RequestParam(value = "waterModel", required = false, defaultValue = "") String waterModel,
                                    @RequestParam(value = "haveCleaner", required = false, defaultValue = "") String haveCleaner,
                                    @RequestParam(value = "isRegularCheck", required = false, defaultValue = "") String isRegularCheck,
                                    @RequestParam(value = "dayProvideAmount", required = false, defaultValue = "") String dayProvideAmount,
                                    @RequestParam(value = "provideVillageCount", required = false, defaultValue = "") String provideVillageCount,
                                    @RequestParam(value = "providePopulation", required = false, defaultValue = "") String providePopulation,
                                    @RequestParam(value = "haveProtectArea", required = false, defaultValue = "") String haveProtectArea,
                                    @RequestParam(value = "mainFunction", required = false, defaultValue = "") String mainFunction,
                                    @RequestParam(value = "totalInstalledCapacity", required = false, defaultValue = "") String totalInstalledCapacity,
                                    @RequestParam(value = "riverElevation", required = false, defaultValue = "") String riverElevation,
                                    @RequestParam(value = "poolHeight", required = false, defaultValue = "") String poolHeight,
                                    //发电机
                                    @RequestParam(value = "generatorModel1", required = false, defaultValue = "") String generatorModel1,
                                    @RequestParam(value = "generatorPower1", required = false, defaultValue = "") String generatorPower1,
                                    @RequestParam(value = "generatorFactoryDate1", required = false, defaultValue = "") String generatorFactoryDate1,
                                    @RequestParam(value = "generatorModel2", required = false, defaultValue = "") String generatorModel2,
                                    @RequestParam(value = "generatorPower2", required = false, defaultValue = "") String generatorPower2,
                                    @RequestParam(value = "generatorFactoryDate2", required = false, defaultValue = "") String generatorFactoryDate2,
                                    @RequestParam(value = "generatorModel3", required = false, defaultValue = "") String generatorModel3,
                                    @RequestParam(value = "generatorPower3", required = false, defaultValue = "") String generatorPower3,
                                    @RequestParam(value = "generatorFactoryDate3", required = false, defaultValue = "") String generatorFactoryDate3,
                                    //水轮机
                                    @RequestParam(value = "turbineModel1", required = false, defaultValue = "") String turbineModel1,
                                    @RequestParam(value = "turbineCount1", required = false, defaultValue = "") String turbineCount1,
                                    @RequestParam(value = "turbineTurnsOrFlow1", required = false, defaultValue = "") String turbineTurnsOrFlow1,
                                    @RequestParam(value = "turbineFactoryDate1", required = false, defaultValue = "") String turbineFactoryDate1,
                                    @RequestParam(value = "turbineModel2", required = false, defaultValue = "") String turbineModel2,
                                    @RequestParam(value = "turbineCount2", required = false, defaultValue = "") String turbineCount2,
                                    @RequestParam(value = "turbineTurnsOrFlow2", required = false, defaultValue = "") String turbineTurnsOrFlow2,
                                    @RequestParam(value = "turbineFactoryDate2", required = false, defaultValue = "") String turbineFactoryDate2,
                                    @RequestParam(value = "turbineModel3", required = false, defaultValue = "") String turbineModel3,
                                    @RequestParam(value = "turbineCount3", required = false, defaultValue = "") String turbineCount3,
                                    @RequestParam(value = "turbineTurnsOrFlow3", required = false, defaultValue = "") String turbineTurnsOrFlow3,
                                    @RequestParam(value = "turbineFactoryDate3", required = false, defaultValue = "") String turbineFactoryDate3,
                                    //变压器
                                    @RequestParam(value = "transformerModel1", required = false, defaultValue = "") String transformerModel1,
                                    @RequestParam(value = "transformerCapacity1", required = false, defaultValue = "") String transformerCapacity1,
                                    @RequestParam(value = "transformerModel2", required = false, defaultValue = "") String transformerModel2,
                                    @RequestParam(value = "transformerCapacity2", required = false, defaultValue = "") String transformerCapacity2,
                                    @RequestParam(value = "transformerModel3", required = false, defaultValue = "") String transformerModel3,
                                    @RequestParam(value = "transformerCapacity3", required = false, defaultValue = "") String transformerCapacity3,
                                    //水泵
                                    @RequestParam(value = "pumpCount1", required = false, defaultValue = "") String pumpCount1,
                                    @RequestParam(value = "pumpLiftOrFlow1", required = false, defaultValue = "") String pumpLiftOrFlow1,
                                    @RequestParam(value = "pumpFactoryDate1", required = false, defaultValue = "") String pumpFactoryDate1,
                                    @RequestParam(value = "pumpModel1", required = false, defaultValue = "") String pumpModel1,
                                    @RequestParam(value = "pumpCount2", required = false, defaultValue = "") String pumpCount2,
                                    @RequestParam(value = "pumpLiftOrFlow2", required = false, defaultValue = "") String pumpLiftOrFlow2,
                                    @RequestParam(value = "pumpFactoryDate2", required = false, defaultValue = "") String pumpFactoryDate2,
                                    @RequestParam(value = "pumpModel2", required = false, defaultValue = "") String pumpModel2,
                                    @RequestParam(value = "pumpCount3", required = false, defaultValue = "") String pumpCount3,
                                    @RequestParam(value = "pumpLiftOrFlow3", required = false, defaultValue = "") String pumpLiftOrFlow3,
                                    @RequestParam(value = "pumpFactoryDate3", required = false, defaultValue = "") String pumpFactoryDate3,
                                    @RequestParam(value = "pumpModel3", required = false, defaultValue = "") String pumpModel3,
                                    //电动机
                                    @RequestParam(value = "electricMotorModel1", required = false, defaultValue = "") String electricMotorModel1,
                                    @RequestParam(value = "electricPower1", required = false, defaultValue = "") String electricPower1,
                                    @RequestParam(value = "electricFactoryDate1", required = false, defaultValue = "") String electricFactoryDate1,
                                    @RequestParam(value = "electricMotorModel2", required = false, defaultValue = "") String electricMotorModel2,
                                    @RequestParam(value = "electricPower2", required = false, defaultValue = "") String electricPower2,
                                    @RequestParam(value = "electricFactoryDate2", required = false, defaultValue = "") String electricFactoryDate2,
                                    @RequestParam(value = "electricMotorModel3", required = false, defaultValue = "") String electricMotorModel3,
                                    @RequestParam(value = "electricPower3", required = false, defaultValue = "") String electricPower3,
                                    @RequestParam(value = "electricFactoryDate3", required = false, defaultValue = "") String electricFactoryDate3,
                                    //管滴灌的支管
                                    @RequestParam(value = "pipeModel1", required = false, defaultValue = "主管1") String pipeModel1,
                                    @RequestParam(value = "pipeLength1", required = false, defaultValue = "") String pipeLength1,
                                    @RequestParam(value = "pipeDiameter1", required = false, defaultValue = "") String pipeDiameter1,
                                    @RequestParam(value = "pipeMaterial1", required = false, defaultValue = "") String pipeMaterial1,
                                    @RequestParam(value = "pipeModel2", required = false, defaultValue = "主管2") String pipeModel2,
                                    @RequestParam(value = "pipeLength2", required = false, defaultValue = "") String pipeLength2,
                                    @RequestParam(value = "pipeDiameter2", required = false, defaultValue = "") String pipeDiameter2,
                                    @RequestParam(value = "pipeMaterial2", required = false, defaultValue = "") String pipeMaterial2,
                                    @RequestParam(value = "pipeModel3", required = false, defaultValue = "支管1") String pipeModel3,
                                    @RequestParam(value = "pipeLength3", required = false, defaultValue = "") String pipeLength3,
                                    @RequestParam(value = "pipeDiameter3", required = false, defaultValue = "") String pipeDiameter3,
                                    @RequestParam(value = "pipeMaterial3", required = false, defaultValue = "") String pipeMaterial3,
                                    @RequestParam(value = "pipeModel4", required = false, defaultValue = "支管2") String pipeModel4,
                                    @RequestParam(value = "pipeLength4", required = false, defaultValue = "") String pipeLength4,
                                    @RequestParam(value = "pipeDiameter4", required = false, defaultValue = "") String pipeDiameter4,
                                    @RequestParam(value = "pipeMaterial4", required = false, defaultValue = "") String pipeMaterial4,
                                    @RequestParam(value = "pipeModel5", required = false, defaultValue = "毛管1") String pipeModel5,
                                    @RequestParam(value = "pipeLength5", required = false, defaultValue = "") String pipeLength5,
                                    @RequestParam(value = "pipeDiameter5", required = false, defaultValue = "") String pipeDiameter5,
                                    @RequestParam(value = "pipeMaterial5", required = false, defaultValue = "") String pipeMaterial5,
                                    @RequestParam(value = "pipeModel6", required = false, defaultValue = "毛管2") String pipeModel6,
                                    @RequestParam(value = "pipeLength6", required = false, defaultValue = "") String pipeLength6,
                                    @RequestParam(value = "pipeDiameter6", required = false, defaultValue = "") String pipeDiameter6,
                                    @RequestParam(value = "pipeMaterial6", required = false, defaultValue = "") String pipeMaterial6,
                                    //渠道的小渠
                                    @RequestParam(value = "canalModel1", required = false, defaultValue = "干渠1") String canalModel1,
                                    @RequestParam(value = "canalLength1", required = false, defaultValue = "") String canalLength1,
                                    @RequestParam(value = "canalSectionSize1", required = false, defaultValue = "") String canalSectionSize1,
                                    @RequestParam(value = "canalSeepageLength1", required = false, defaultValue = "") String canalSeepageLength1,
                                    @RequestParam(value = "canalLiningSectionSize1", required = false, defaultValue = "") String canalLiningSectionSize1,
                                    @RequestParam(value = "canalLiningMaterial1", required = false, defaultValue = "") String canalLiningMaterial1,
                                    @RequestParam(value = "canalModel2", required = false, defaultValue = "干渠2") String canalModel2,
                                    @RequestParam(value = "canalLength2", required = false, defaultValue = "") String canalLength2,
                                    @RequestParam(value = "canalSectionSize2", required = false, defaultValue = "") String canalSectionSize2,
                                    @RequestParam(value = "canalSeepageLength2", required = false, defaultValue = "") String canalSeepageLength2,
                                    @RequestParam(value = "canalLiningSectionSize2", required = false, defaultValue = "") String canalLiningSectionSize2,
                                    @RequestParam(value = "canalLiningMaterial2", required = false, defaultValue = "") String canalLiningMaterial2,
                                    @RequestParam(value = "canalModel3", required = false, defaultValue = "支渠1") String canalModel3,
                                    @RequestParam(value = "canalLength3", required = false, defaultValue = "") String canalLength3,
                                    @RequestParam(value = "canalSectionSize3", required = false, defaultValue = "") String canalSectionSize3,
                                    @RequestParam(value = "canalSeepageLength3", required = false, defaultValue = "") String canalSeepageLength3,
                                    @RequestParam(value = "canalLiningSectionSize3", required = false, defaultValue = "") String canalLiningSectionSize3,
                                    @RequestParam(value = "canalLiningMaterial3", required = false, defaultValue = "") String canalLiningMaterial3,
                                    @RequestParam(value = "canalModel4", required = false, defaultValue = "支渠2") String canalModel4,
                                    @RequestParam(value = "canalLength4", required = false, defaultValue = "") String canalLength4,
                                    @RequestParam(value = "canalSectionSize4", required = false, defaultValue = "") String canalSectionSize4,
                                    @RequestParam(value = "canalSeepageLength4", required = false, defaultValue = "") String canalSeepageLength4,
                                    @RequestParam(value = "canalLiningSectionSize4", required = false, defaultValue = "") String canalLiningSectionSize4,
                                    @RequestParam(value = "canalLiningMaterial4", required = false, defaultValue = "") String canalLiningMaterial4,
                                    @RequestParam(value = "canalModel5", required = false, defaultValue = "斗渠1") String canalModel5,
                                    @RequestParam(value = "canalLength5", required = false, defaultValue = "") String canalLength5,
                                    @RequestParam(value = "canalSectionSize5", required = false, defaultValue = "") String canalSectionSize5,
                                    @RequestParam(value = "canalSeepageLength5", required = false, defaultValue = "") String canalSeepageLength5,
                                    @RequestParam(value = "canalLiningSectionSize5", required = false, defaultValue = "") String canalLiningSectionSize5,
                                    @RequestParam(value = "canalLiningMaterial5", required = false, defaultValue = "") String canalLiningMaterial5,
                                    @RequestParam(value = "canalModel6", required = false, defaultValue = "斗渠2") String canalModel6,
                                    @RequestParam(value = "canalLength6", required = false, defaultValue = "") String canalLength6,
                                    @RequestParam(value = "canalSectionSize6", required = false, defaultValue = "") String canalSectionSize6,
                                    @RequestParam(value = "canalSeepageLength6", required = false, defaultValue = "") String canalSeepageLength6,
                                    @RequestParam(value = "canalLiningSectionSize6", required = false, defaultValue = "") String canalLiningSectionSize6,
                                    @RequestParam(value = "canalLiningMaterial6", required = false, defaultValue = "") String canalLiningMaterial6,
                                    @RequestParam(value = "canalModel7", required = false, defaultValue = "农渠1") String canalModel7,
                                    @RequestParam(value = "canalLength7", required = false, defaultValue = "") String canalLength7,
                                    @RequestParam(value = "canalSectionSize7", required = false, defaultValue = "") String canalSectionSize7,
                                    @RequestParam(value = "canalSeepageLength7", required = false, defaultValue = "") String canalSeepageLength7,
                                    @RequestParam(value = "canalLiningSectionSize7", required = false, defaultValue = "") String canalLiningSectionSize7,
                                    @RequestParam(value = "canalLiningMaterial7", required = false, defaultValue = "") String canalLiningMaterial7,
                                    @RequestParam(value = "canalModel8", required = false, defaultValue = "农渠2") String canalModel8,
                                    @RequestParam(value = "canalLength8", required = false, defaultValue = "") String canalLength8,
                                    @RequestParam(value = "canalSectionSize8", required = false, defaultValue = "") String canalSectionSize8,
                                    @RequestParam(value = "canalSeepageLength8", required = false, defaultValue = "") String canalSeepageLength8,
                                    @RequestParam(value = "canalLiningSectionSize8", required = false, defaultValue = "") String canalLiningSectionSize8,
                                    @RequestParam(value = "canalLiningMaterial8", required = false, defaultValue = "") String canalLiningMaterial8,
                                    @RequestParam("timeStamp") Long timeStamp) {
        Response response = new Response();
        try {
            if (!authService.checkLogin(userId, token)) {
                return responseService.notLogin(response);
            } else if (authService.checkGuest(userId) || !authService.checkUserTownAccess(userId, townId)
                    || !authService.unitContainingCheck(townId, villageId, groupId)) {
                return responseService.noAccess(response);
            }
            WaterConservationEntity waterConservationEntity = new WaterConservationEntity();
            projectService.setCommonProperty(waterConservationEntity, userId, category, remark, name, code, manageModel, townId, villageId,
                    groupId, situation, constructTime, constructUnit, propertyOwner, manager, longitude, latitude, timeStamp);
            ProjectMarkEntity projectMark = new ProjectMarkEntity();
            String imagePath = "";
            String planeSketchPath = "";
            String internalImagePath = "";
            String externalImagePath = "";
            String sectionImagePath = "";
            String startImagePath = "";
            String middleImagePath = "";
            String endImagePath = "";
            if (image != null) {
                imagePath = fileService.saveFile(image, timeStamp);
            }
            if (planeSketch != null) {
                planeSketchPath = fileService.saveFile(planeSketch, timeStamp);
            }
            if (sectionImage != null) {
                sectionImagePath = fileService.saveFile(sectionImage, timeStamp);
            }
            if (startImage != null) {
                startImagePath = fileService.saveFile(startImage, timeStamp);
            }
            if (middleImage != null) {
                middleImagePath = fileService.saveFile(middleImage, timeStamp);
            }
            if (endImage != null) {
                endImagePath = fileService.saveFile(endImage, timeStamp);
            }
            if (internalImage != null) {
                internalImagePath = fileService.saveFile(internalImage, timeStamp);
            }
            if (externalImage != null) {
                externalImagePath = fileService.saveFile(externalImage, timeStamp);
            }
            switch (category) {
                case "渡槽":
                    AqueductEntity aqueduct = new AqueductEntity();
                    projectService.saveAqueduct(aqueduct, crossWatercourseLocation, crossCount, sectionSize, structureAndMaterial,
                            crossLength, imagePath);
                    projectMark.setAqueduct(aqueduct);
                    break;
                case "桥梁":
                    BridgeEntity bridgeEntity = new BridgeEntity();
                    projectService.saveBridge(bridgeEntity, watercourseLocation, crossCount, structureAndMaterial, loadStandard,
                            crossLength, width, length, imagePath);
                    projectMark.setBridge(bridgeEntity);
                    break;
                case "渠道":
                    ChannelEntity channelEntity = new ChannelEntity();
                    projectService.saveChannel(channelEntity, headOrPumpStation, buildingMatchRate, length, goodConditionRate, imagePath, sectionSize,
                            seepageCanalLength, liningSectionSize, sumLength, planeSketchPath, canalLength1, canalLength2, canalLength3, canalLength4,
                            canalLength5, canalLength6, canalLength7, canalLength8, canalLiningMaterial1, canalLiningMaterial2, canalLiningMaterial3,
                            canalLiningMaterial4, canalLiningMaterial5, canalLiningMaterial6, canalLiningMaterial7, canalLiningMaterial8,
                            canalLiningSectionSize1, canalLiningSectionSize2, canalLiningSectionSize3, canalLiningSectionSize4, canalLiningSectionSize5,
                            canalLiningSectionSize6, canalLiningSectionSize7, canalLiningSectionSize8, canalModel1, canalModel2, canalModel3,
                            canalModel4, canalModel5, canalModel6, canalModel7, canalModel8, canalSectionSize1, canalSectionSize2, canalSectionSize3,
                            canalSectionSize4, canalSectionSize5, canalSectionSize6, canalSectionSize7, canalSectionSize8, canalSeepageLength1,
                            canalSeepageLength2, canalSeepageLength3, canalSeepageLength4, canalSeepageLength5, canalSeepageLength6, canalSeepageLength7,
                            canalSeepageLength8, sumSectionSize, sumLiningSectionSize, sumSeepageCanalLength);
                    projectMark.setChannel(channelEntity);
                    break;
                case "涵洞":
                    CulvertEntity culvertEntity = new CulvertEntity();
                    projectService.saveCulvert(culvertEntity, watercourseLocation, culvertModel, sectionSize, length, holeModel,
                            doorMaterial, hoistModel, holeMaterial, imagePath);
                    projectMark.setCulvert(culvertEntity);
                    break;
                case "塘坝":
                    DamEntity damEntity = new DamEntity();
                    projectService.saveDam(damEntity, isRegistered, features, mainFunction, isAccountability, feeResources, maintainPersonFee,
                            isCertificated, development, manageRageLine, pondDamManageLine, protectGround, twoLinesBuilding, specifiedManage, imagePath, protectRageManagement);
                    projectMark.setDam(damEntity);
                    break;
                case "深水井":
                    DeepWellsEntity deepWellsEntity = new DeepWellsEntity();
                    projectService.saveDeepWells(deepWellsEntity, irrigateArea, deepPump, diameter, depth, material, imagePath);
                    projectMark.setDeepWells(deepWellsEntity);
                    break;
                case "管滴灌":
                    DripIrrigationPipeEntity dripIrrigationPipeEntity = new DripIrrigationPipeEntity();
                    projectService.saveDripIrrigationPipe(dripIrrigationPipeEntity, irrigateArea, intakeWay, waterResource, sumLength, sumDiameter,
                            imagePath, planeSketchPath, pipeDiameter1, pipeDiameter2, pipeDiameter3, pipeDiameter4, pipeDiameter5, pipeDiameter6,
                            pipeLength1, pipeLength2, pipeLength3, pipeLength4, pipeLength5, pipeLength6, pipeMaterial1, pipeMaterial2, pipeMaterial3,
                            pipeMaterial4, pipeMaterial5, pipeMaterial6, pipeModel1, pipeModel2, pipeModel3, pipeModel4, pipeModel5, pipeModel6, length);
                    projectMark.setDripIrrigationPipe(dripIrrigationPipeEntity);
                    break;
                case "大口井":
                    GreatWellsEntity greatWellsEntity = new GreatWellsEntity();
                    projectService.saveGreatWells(greatWellsEntity, irrigateArea, waterCapacity, size, depth, modelAndMaterial, imagePath);
                    projectMark.setGreatWells(greatWellsEntity);
                    break;
                case "水电站":
                    HydropowerEntity hydropowerEntity = new HydropowerEntity();
                    projectService.saveHydropower(hydropowerEntity, irrigateArea, paddyFieldArea, drainageArea, irrigateFee, drainageFee,
                            annualFee, watercourseLocation, machineArea, affiliation, sumElectricCapacity, averageCapacity, internalImagePath,
                            externalImagePath, problem, transformerCapacity1, transformerCapacity2, transformerCapacity3, transformerModel1,
                            transformerModel2, transformerModel3, turbineCount1, turbineCount2, turbineCount3, turbineFactoryDate1,
                            turbineFactoryDate2, turbineFactoryDate3, turbineModel1, turbineModel2, turbineModel3, turbineTurnsOrFlow1,
                            turbineTurnsOrFlow2, turbineTurnsOrFlow3, generatorFactoryDate1, generatorFactoryDate2, generatorFactoryDate3,
                            generatorModel1, generatorModel2, generatorModel3, generatorPower1, generatorPower2, generatorPower3);
                    projectMark.setHydropower(hydropowerEntity);
                    break;
                case "水塘":
                    PondEntity pondEntity = new PondEntity();
                    projectService.savePond(pondEntity, mainFunction, lastDredgingTime, waterArea, waterCapacity, imagePath);
                    projectMark.setPond(pondEntity);
                    break;
                case "泵站":
                    PumpStationEntity pumpStationEntity = new PumpStationEntity();
                    projectService.savePumpStation(pumpStationEntity, watercourseLocation, irrigateArea, paddyFieldArea, drainageArea,
                            irrigateFee, drainageFee, annualFee, nature, machineArea, totalInstalledCapacity, riverElevation, poolHeight,
                            internalImagePath, externalImagePath, problem, transformerCapacity1, transformerCapacity2, transformerCapacity3,
                            transformerModel1, transformerModel2, transformerModel3, pumpCount1, pumpCount2, pumpCount3, pumpFactoryDate1,
                            pumpFactoryDate2, pumpFactoryDate3, pumpLiftOrFlow1, pumpLiftOrFlow2, pumpLiftOrFlow3, pumpModel1, pumpModel2,
                            pumpModel3, electricFactoryDate1, electricFactoryDate2, electricFactoryDate3, electricMotorModel1,
                            electricMotorModel2, electricMotorModel3, electricPower1, electricPower2, electricPower3);
                    projectMark.setPumpStation(pumpStationEntity);
                    break;
                case "水闸":
                    SluiceEntity sluiceEntity = new SluiceEntity();
                    projectService.saveSluice(sluiceEntity, watercourseLocation, model, holeCount, door, hoistTonnage, holeHeight,
                            holeWidth, doorHeight, doorWidth, hoistModel, buildingSituation, doorSituation, hoistSituation, imagePath);
                    projectMark.setSluice(sluiceEntity);
                    break;
                case "河道":
                    WatercourseEntity watercourseEntity = new WatercourseEntity();
                    projectService.saveWaterCourse(watercourseEntity, length, lastDredgingTime, estuaryHeight, estuaryWidth, leftWidth,
                            rightWidth, hediHeight, hediWidth, flowVillages, nature, sectionImagePath, startImagePath, middleImagePath,
                            endImagePath, endpointLatitude, endpointLongitude, timeStamp);
                    projectMark.setWatercourse(watercourseEntity);
                    break;
                case "水厂":
                    WaterWorksEntity waterWorksEntity = new WaterWorksEntity();
                    projectService.saveWaterWorks(waterWorksEntity, provideAmount, waterModel, haveCleaner, isRegularCheck, dayProvideAmount,
                            provideVillageCount, providePopulation, haveProtectArea, imagePath);
                    projectMark.setWaterWorks(waterWorksEntity);
                    break;
                default:
                    break;
            }
            waterConservationEntity.setProjectMark(projectMark);
            waterConservationRepository.save(waterConservationEntity);
            return responseService.success(response);
        } catch (IOException e) {
            return responseService.saveFileError(response);
        } catch (Exception e) {
            return responseService.serverError(response);
        }
    }

    /**
     * @param userId                   用户id
     * @param token                    登录token
     * @param townId                   乡镇id
     * @param villageId                村庄id
     * @param groupId                  组id
     * @param code                     工程编码
     * @param name                     工程名字
     * @param remark                   备注
     * @param manageModel              管理模式
     * @param situation                完好情况
     * @param constructTime            修建/改造时间
     * @param constructUnit            修建/改造单位
     * @param propertyOwner            产权人
     * @param manager                  管理人
     * @param crossWatercourseLocation 所跨河道
     * @param crossCount               跨数
     * @param sectionSize              断面尺寸
     * @param structureAndMaterial     结构和材质
     * @param crossLength              每跨长
     * @param length                   总长
     * @param width                    宽度
     * @param watercourseLocation      说在河道
     * @param loadStandard             负载标准
     * @param headOrPumpStation        渠首或者取水泵站
     * @param buildingMatchRate        建筑物配套率
     * @param goodConditionRate        渠道及建筑物完好率
     * @param sumLength                总计长度
     * @param culvertModel             涵洞类型
     * @param holeModel                洞省类型
     * @param doorMaterial             洞口闸门材质
     * @param holeMaterial             洞身材质
     * @param hoistModel               洞口启闭机类型
     * @param isRegistered             是否登记注册
     * @param features                 特性
     * @param isAccountability         是否建立三级责任制
     * @param feeResources             主要运行经费来源
     * @param maintainPersonFee        聘用管护人员工资
     * @param isCertificated           是否持证上岗
     * @param development              生产开发
     * @param manageRageLine           管理范围线
     * @param pondDamManageLine        塘坝管理范围线
     * @param protectGround            护坝地
     * @param twoLinesBuilding         理论二线内建筑物
     * @param specifiedManage          规范化管理
     * @param irrigateArea             灌溉面积
     * @param deepPump                 深水泵
     * @param diameter                 直径
     * @param depth                    深度
     * @param material                 材质
     * @param intakeWay                取水方式
     * @param waterResource            水源
     * @param sumDiameter              总直径
     * @param waterCapacity            储水量
     * @param size                     尺寸
     * @param modelAndMaterial         型号和材质
     * @param paddyFieldArea           水田面积
     * @param drainageArea             排涝面积
     * @param irrigateFee              灌溉收费
     * @param drainageFee              排涝收费
     * @param annualFee                年收费
     * @param machineArea              机房面积
     * @param affiliation              隶属关系
     * @param sumElectricCapacity      总装机容量
     * @param averageCapacity          平均容量
     * @param problem                  存在的问题
     * @param lastDredgingTime         上次疏浚时间
     * @param waterArea                水面积
     * @param model                    型号
     * @param holeCount                单孔数
     * @param door                     闸门
     * @param hoistTonnage             启闭机吨位
     * @param holeHeight               单孔高
     * @param holeWidth                单孔宽
     * @param doorHeight               闸门高
     * @param doorWidth                闸门宽
     * @param buildingSituation        建筑完好情况
     * @param doorSituation            闸门完好情况
     * @param hoistSituation           启闭机完好情况
     * @param estuaryWidth             河口宽
     * @param estuaryHeight            河口高
     * @param hediWidth                河底宽
     * @param hediHeight               河底高
     * @param leftWidth                左岸宽
     * @param rightWidth               右岸宽
     * @param flowVillages             流经村庄
     * @param nature                   性质
     * @param longitude                经度
     * @param latitude                 纬度
     * @param endpointLongitude        终点经度
     * @param endpointLatitude         终点纬度
     * @param provideAmount            日供水量
     * @param waterModel               水源类型
     * @param haveCleaner              有无净化设施
     * @param isRegularCheck           是否定期检测
     * @param dayProvideAmount         日供水量
     * @param provideVillageCount      供水村庄数量
     * @param providePopulation        供水人口
     * @param haveProtectArea          是否建立水源保护区
     * @param generatorModel1          发电机类型
     * @param generatorPower1          发电机单台功率*台数
     * @param generatorFactoryDate1    发电机出厂日期
     * @param turbineModel1            水轮机类型
     * @param turbineCount1            水轮机台数
     * @param turbineTurnsOrFlow1      水轮机转数/流量
     * @param turbineFactoryDate1      水轮机出厂日期
     * @param transformerModel1        变压器类型
     * @param transformerCapacity1     变压器单机容量*台数
     * @param pumpCount1               水泵台数
     * @param pumpLiftOrFlow1          水泵扬程/流量
     * @param pumpFactoryDate1         水泵出厂日期
     * @param pumpModel1               水泵类型
     * @param electricMotorModel1      电动机类型
     * @param electricPower1           电动机单台功率*台数
     * @param electricFactoryDate1     电动机出厂日期
     * @param pipeModel1               管道类型
     * @param pipeLength1              管道长
     * @param pipeDiameter1            管道直径
     * @param pipeMaterial1            管道材质
     * @param canalModel1              沟渠类型
     * @param canalLength1             沟渠长度
     * @param canalSeepageLength1      沟渠防渗渠长度
     * @param canalLiningSectionSize1  沟渠砌断面尺寸
     * @param canalLiningMaterial1     沟渠衬砌材质
     * @return 操作结果
     */
    @RequestMapping("/modifyConservation")
    public Response modifyConservation(@RequestParam("userId") Integer userId,
                                       @RequestParam("token") String token,
                                       @RequestParam("projectId") Integer projectId,
                                       @RequestParam("townId") Integer townId,
                                       @RequestParam("villageId") Integer villageId,
                                       @RequestParam(value = "groupId", required = false) Integer groupId,
                                       @RequestParam("code") String code,
                                       @RequestParam("name") String name,
                                       @RequestParam(value = "remark", required = false, defaultValue = "") String remark,
                                       @RequestParam(value = "manageModel", required = false, defaultValue = "自管") String manageModel,
                                       @RequestParam(value = "situation", required = false, defaultValue = "完好") String situation,
                                       @RequestParam(value = "constructTime", required = false, defaultValue = "") String constructTime,
                                       @RequestParam(value = "constructUnit", required = false, defaultValue = "") String constructUnit,
                                       @RequestParam(value = "propertyOwner", required = false, defaultValue = "") String propertyOwner,
                                       @RequestParam(value = "manager", required = false, defaultValue = "") String manager,
                                       @RequestParam(value = "crossWatercourseLocation", required = false, defaultValue = "") String crossWatercourseLocation,
                                       @RequestParam(value = "crossCount", required = false, defaultValue = "") String crossCount,
                                       @RequestParam(value = "sectionSize", required = false, defaultValue = "") String sectionSize,
                                       @RequestParam(value = "structureAndMaterial", required = false, defaultValue = "") String structureAndMaterial,
                                       @RequestParam(value = "image", required = false) MultipartFile image,
                                       @RequestParam(value = "crossLength", required = false, defaultValue = "") String crossLength,
                                       @RequestParam(value = "length", required = false, defaultValue = "") String length,
                                       @RequestParam(value = "width", required = false, defaultValue = "") String width,
                                       @RequestParam(value = "watercourseLocation", required = false, defaultValue = "") String watercourseLocation,
                                       @RequestParam(value = "loadStandard", required = false, defaultValue = "") String loadStandard,
                                       @RequestParam(value = "headOrPumpStation", required = false, defaultValue = "") String headOrPumpStation,
                                       @RequestParam(value = "buildingMatchRate", required = false, defaultValue = "") String buildingMatchRate,
                                       @RequestParam(value = "goodConditionRate", required = false, defaultValue = "") String goodConditionRate,
                                       @RequestParam(value = "seepageCanalLength", required = false, defaultValue = "") String seepageCanalLength,
                                       @RequestParam(value = "liningSectionSize", required = false, defaultValue = "") String liningSectionSize,
                                       @RequestParam(value = "sumLength", required = false, defaultValue = "") String sumLength,
                                       @RequestParam(value = "sumLiningSectionSize", required = false, defaultValue = "") String sumLiningSectionSize,
                                       @RequestParam(value = "sumSeepageCanalLength", required = false, defaultValue = "") String sumSeepageCanalLength,
                                       @RequestParam(value = "sumSectionSize", required = false, defaultValue = "") String sumSectionSize,
                                       @RequestParam(value = "planeSketch", required = false) MultipartFile planeSketch,
                                       @RequestParam(value = "culvertModel", required = false, defaultValue = "") String culvertModel,
                                       @RequestParam(value = "holeModel", required = false, defaultValue = "") String holeModel,
                                       @RequestParam(value = "doorMaterial", required = false, defaultValue = "") String doorMaterial,
                                       @RequestParam(value = "holeMaterial", required = false, defaultValue = "") String holeMaterial,
                                       @RequestParam(value = "hoistModel", required = false, defaultValue = "") String hoistModel,
                                       @RequestParam(value = "isRegistered", required = false, defaultValue = "") String isRegistered,
                                       @RequestParam(value = "features", required = false, defaultValue = "否") String features,
                                       @RequestParam(value = "isAccountability", required = false, defaultValue = "否") String isAccountability,
                                       @RequestParam(value = "feeResources", required = false, defaultValue = "") String feeResources,
                                       @RequestParam(value = "maintainPersonFee", required = false, defaultValue = "") String maintainPersonFee,
                                       @RequestParam(value = "isCertificated", required = false, defaultValue = "否") String isCertificated,
                                       @RequestParam(value = "development", required = false, defaultValue = "") String development,
                                       @RequestParam(value = "manageRageLine", required = false, defaultValue = "") String manageRageLine,
                                       @RequestParam(value = "pondDamManageLine", required = false, defaultValue = "") String pondDamManageLine,
                                       @RequestParam(value = "protectGround", required = false, defaultValue = "") String protectGround,
                                       @RequestParam(value = "twoLinesBuilding", required = false, defaultValue = "") String twoLinesBuilding,
                                       @RequestParam(value = "specifiedManage", required = false, defaultValue = "") String specifiedManage,
                                       @RequestParam(value = "irrigateArea", required = false, defaultValue = "") String irrigateArea,
                                       @RequestParam(value = "protectRageManagement", required = false, defaultValue = "") String protectRageManagement,
                                       @RequestParam(value = "deepPump", required = false, defaultValue = "") String deepPump,
                                       @RequestParam(value = "diameter", required = false, defaultValue = "") String diameter,
                                       @RequestParam(value = "depth", required = false, defaultValue = "") String depth,
                                       @RequestParam(value = "material", required = false, defaultValue = "") String material,
                                       @RequestParam(value = "intakeWay", required = false, defaultValue = "") String intakeWay,
                                       @RequestParam(value = "waterResource", required = false, defaultValue = "") String waterResource,
                                       @RequestParam(value = "sumDiameter", required = false, defaultValue = "") String sumDiameter,
                                       @RequestParam(value = "waterCapacity", required = false, defaultValue = "") String waterCapacity,
                                       @RequestParam(value = "size", required = false, defaultValue = "") String size,
                                       @RequestParam(value = "modelAndMaterial", required = false, defaultValue = "") String modelAndMaterial,
                                       @RequestParam(value = "paddyFieldArea", required = false, defaultValue = "") String paddyFieldArea,
                                       @RequestParam(value = "drainageArea", required = false, defaultValue = "") String drainageArea,
                                       @RequestParam(value = "irrigateFee", required = false, defaultValue = "") String irrigateFee,
                                       @RequestParam(value = "drainageFee", required = false, defaultValue = "") String drainageFee,
                                       @RequestParam(value = "annualFee", required = false, defaultValue = "") String annualFee,
                                       @RequestParam(value = "machineArea", required = false, defaultValue = "") String machineArea,
                                       @RequestParam(value = "affiliation", required = false, defaultValue = "") String affiliation,
                                       @RequestParam(value = "sumElectricCapacity", required = false, defaultValue = "") String sumElectricCapacity,
                                       @RequestParam(value = "averageCapacity", required = false, defaultValue = "") String averageCapacity,
                                       @RequestParam(value = "internalImage", required = false) MultipartFile internalImage,
                                       @RequestParam(value = "externalImage", required = false) MultipartFile externalImage,
                                       @RequestParam(value = "problem", required = false, defaultValue = "") String problem,
                                       @RequestParam(value = "lastDredgingTime", required = false, defaultValue = "") String lastDredgingTime,
                                       @RequestParam(value = "waterArea", required = false, defaultValue = "") String waterArea,
                                       @RequestParam(value = "model", required = false, defaultValue = "") String model,
                                       @RequestParam(value = "holeCount", required = false, defaultValue = "") String holeCount,
                                       @RequestParam(value = "door", required = false, defaultValue = "") String door,
                                       @RequestParam(value = "hoistTonnage", required = false, defaultValue = "") String hoistTonnage,
                                       @RequestParam(value = "holeHeight", required = false, defaultValue = "") String holeHeight,
                                       @RequestParam(value = "holeWidth", required = false, defaultValue = "") String holeWidth,
                                       @RequestParam(value = "doorHeight", required = false, defaultValue = "") String doorHeight,
                                       @RequestParam(value = "doorWidth", required = false, defaultValue = "") String doorWidth,
                                       @RequestParam(value = "buildingSituation", required = false, defaultValue = "完好") String buildingSituation,
                                       @RequestParam(value = "doorSituation", required = false, defaultValue = "") String doorSituation,
                                       @RequestParam(value = "hoistSituation", required = false, defaultValue = "") String hoistSituation,
                                       @RequestParam(value = "estuaryWidth", required = false, defaultValue = "") String estuaryWidth,
                                       @RequestParam(value = "estuaryHeight", required = false, defaultValue = "") String estuaryHeight,
                                       @RequestParam(value = "hediWidth", required = false, defaultValue = "") String hediWidth,
                                       @RequestParam(value = "hediHeight", required = false, defaultValue = "") String hediHeight,
                                       @RequestParam(value = "leftWidth", required = false, defaultValue = "") String leftWidth,
                                       @RequestParam(value = "rightWidth", required = false, defaultValue = "") String rightWidth,
                                       @RequestParam(value = "flowVillages", required = false, defaultValue = "") String flowVillages,
                                       @RequestParam(value = "nature", required = false, defaultValue = "") String nature,
                                       @RequestParam(value = "sectionImage", required = false) MultipartFile sectionImage,
                                       @RequestParam(value = "longitude", required = false, defaultValue = "0.0") String longitude,
                                       @RequestParam(value = "latitude", required = false, defaultValue = "0.0") String latitude,
                                       @RequestParam(value = "endpointLongitude", required = false, defaultValue = "0.0") String endpointLongitude,
                                       @RequestParam(value = "endpointLatitude", required = false, defaultValue = "0.0") String endpointLatitude,
                                       @RequestParam(value = "startImage", required = false) MultipartFile startImage,
                                       @RequestParam(value = "middleImage", required = false) MultipartFile middleImage,
                                       @RequestParam(value = "endImage", required = false) MultipartFile endImage,
                                       @RequestParam(value = "provideAmount", required = false, defaultValue = "") String provideAmount,
                                       @RequestParam(value = "waterModel", required = false, defaultValue = "") String waterModel,
                                       @RequestParam(value = "haveCleaner", required = false, defaultValue = "") String haveCleaner,
                                       @RequestParam(value = "isRegularCheck", required = false, defaultValue = "") String isRegularCheck,
                                       @RequestParam(value = "dayProvideAmount", required = false, defaultValue = "") String dayProvideAmount,
                                       @RequestParam(value = "provideVillageCount", required = false, defaultValue = "") String provideVillageCount,
                                       @RequestParam(value = "providePopulation", required = false, defaultValue = "") String providePopulation,
                                       @RequestParam(value = "haveProtectArea", required = false, defaultValue = "") String haveProtectArea,
                                       @RequestParam(value = "mainFunction", required = false, defaultValue = "") String mainFunction,
                                       @RequestParam(value = "totalInstalledCapacity", required = false, defaultValue = "") String totalInstalledCapacity,
                                       @RequestParam(value = "riverElevation", required = false, defaultValue = "") String riverElevation,
                                       @RequestParam(value = "poolHeight", required = false, defaultValue = "") String poolHeight,
                                       //发电机
                                       @RequestParam(value = "generatorModel1", required = false, defaultValue = "") String generatorModel1,
                                       @RequestParam(value = "generatorPower1", required = false, defaultValue = "") String generatorPower1,
                                       @RequestParam(value = "generatorFactoryDate1", required = false, defaultValue = "") String generatorFactoryDate1,
                                       @RequestParam(value = "generatorModel2", required = false, defaultValue = "") String generatorModel2,
                                       @RequestParam(value = "generatorPower2", required = false, defaultValue = "") String generatorPower2,
                                       @RequestParam(value = "generatorFactoryDate2", required = false, defaultValue = "") String generatorFactoryDate2,
                                       @RequestParam(value = "generatorModel3", required = false, defaultValue = "") String generatorModel3,
                                       @RequestParam(value = "generatorPower3", required = false, defaultValue = "") String generatorPower3,
                                       @RequestParam(value = "generatorFactoryDate3", required = false, defaultValue = "") String generatorFactoryDate3,
                                       //水轮机
                                       @RequestParam(value = "turbineModel1", required = false, defaultValue = "") String turbineModel1,
                                       @RequestParam(value = "turbineCount1", required = false, defaultValue = "") String turbineCount1,
                                       @RequestParam(value = "turbineTurnsOrFlow1", required = false, defaultValue = "") String turbineTurnsOrFlow1,
                                       @RequestParam(value = "turbineFactoryDate1", required = false, defaultValue = "") String turbineFactoryDate1,
                                       @RequestParam(value = "turbineModel2", required = false, defaultValue = "") String turbineModel2,
                                       @RequestParam(value = "turbineCount2", required = false, defaultValue = "") String turbineCount2,
                                       @RequestParam(value = "turbineTurnsOrFlow2", required = false, defaultValue = "") String turbineTurnsOrFlow2,
                                       @RequestParam(value = "turbineFactoryDate2", required = false, defaultValue = "") String turbineFactoryDate2,
                                       @RequestParam(value = "turbineModel3", required = false, defaultValue = "") String turbineModel3,
                                       @RequestParam(value = "turbineCount3", required = false, defaultValue = "") String turbineCount3,
                                       @RequestParam(value = "turbineTurnsOrFlow3", required = false, defaultValue = "") String turbineTurnsOrFlow3,
                                       @RequestParam(value = "turbineFactoryDate3", required = false, defaultValue = "") String turbineFactoryDate3,
                                       //变压器
                                       @RequestParam(value = "transformerModel1", required = false, defaultValue = "") String transformerModel1,
                                       @RequestParam(value = "transformerCapacity1", required = false, defaultValue = "") String transformerCapacity1,
                                       @RequestParam(value = "transformerModel2", required = false, defaultValue = "") String transformerModel2,
                                       @RequestParam(value = "transformerCapacity2", required = false, defaultValue = "") String transformerCapacity2,
                                       @RequestParam(value = "transformerModel3", required = false, defaultValue = "") String transformerModel3,
                                       @RequestParam(value = "transformerCapacity3", required = false, defaultValue = "") String transformerCapacity3,
                                       //水泵
                                       @RequestParam(value = "pumpCount1", required = false, defaultValue = "") String pumpCount1,
                                       @RequestParam(value = "pumpLiftOrFlow1", required = false, defaultValue = "") String pumpLiftOrFlow1,
                                       @RequestParam(value = "pumpFactoryDate1", required = false, defaultValue = "") String pumpFactoryDate1,
                                       @RequestParam(value = "pumpModel1", required = false, defaultValue = "") String pumpModel1,
                                       @RequestParam(value = "pumpCount2", required = false, defaultValue = "") String pumpCount2,
                                       @RequestParam(value = "pumpLiftOrFlow2", required = false, defaultValue = "") String pumpLiftOrFlow2,
                                       @RequestParam(value = "pumpFactoryDate2", required = false, defaultValue = "") String pumpFactoryDate2,
                                       @RequestParam(value = "pumpModel2", required = false, defaultValue = "") String pumpModel2,
                                       @RequestParam(value = "pumpCount3", required = false, defaultValue = "") String pumpCount3,
                                       @RequestParam(value = "pumpLiftOrFlow3", required = false, defaultValue = "") String pumpLiftOrFlow3,
                                       @RequestParam(value = "pumpFactoryDate3", required = false, defaultValue = "") String pumpFactoryDate3,
                                       @RequestParam(value = "pumpModel3", required = false, defaultValue = "") String pumpModel3,
                                       //电动机
                                       @RequestParam(value = "electricMotorModel1", required = false, defaultValue = "") String electricMotorModel1,
                                       @RequestParam(value = "electricPower1", required = false, defaultValue = "") String electricPower1,
                                       @RequestParam(value = "electricFactoryDate1", required = false, defaultValue = "") String electricFactoryDate1,
                                       @RequestParam(value = "electricMotorModel2", required = false, defaultValue = "") String electricMotorModel2,
                                       @RequestParam(value = "electricPower2", required = false, defaultValue = "") String electricPower2,
                                       @RequestParam(value = "electricFactoryDate2", required = false, defaultValue = "") String electricFactoryDate2,
                                       @RequestParam(value = "electricMotorModel3", required = false, defaultValue = "") String electricMotorModel3,
                                       @RequestParam(value = "electricPower3", required = false, defaultValue = "") String electricPower3,
                                       @RequestParam(value = "electricFactoryDate3", required = false, defaultValue = "") String electricFactoryDate3,
                                       //管滴灌的支管
                                       @RequestParam(value = "pipeModel1", required = false, defaultValue = "主管1") String pipeModel1,
                                       @RequestParam(value = "pipeLength1", required = false, defaultValue = "") String pipeLength1,
                                       @RequestParam(value = "pipeDiameter1", required = false, defaultValue = "") String pipeDiameter1,
                                       @RequestParam(value = "pipeMaterial1", required = false, defaultValue = "") String pipeMaterial1,
                                       @RequestParam(value = "pipeModel2", required = false, defaultValue = "主管2") String pipeModel2,
                                       @RequestParam(value = "pipeLength2", required = false, defaultValue = "") String pipeLength2,
                                       @RequestParam(value = "pipeDiameter2", required = false, defaultValue = "") String pipeDiameter2,
                                       @RequestParam(value = "pipeMaterial2", required = false, defaultValue = "") String pipeMaterial2,
                                       @RequestParam(value = "pipeModel3", required = false, defaultValue = "支管1") String pipeModel3,
                                       @RequestParam(value = "pipeLength3", required = false, defaultValue = "") String pipeLength3,
                                       @RequestParam(value = "pipeDiameter3", required = false, defaultValue = "") String pipeDiameter3,
                                       @RequestParam(value = "pipeMaterial3", required = false, defaultValue = "") String pipeMaterial3,
                                       @RequestParam(value = "pipeModel4", required = false, defaultValue = "支管2") String pipeModel4,
                                       @RequestParam(value = "pipeLength4", required = false, defaultValue = "") String pipeLength4,
                                       @RequestParam(value = "pipeDiameter4", required = false, defaultValue = "") String pipeDiameter4,
                                       @RequestParam(value = "pipeMaterial4", required = false, defaultValue = "") String pipeMaterial4,
                                       @RequestParam(value = "pipeModel5", required = false, defaultValue = "毛管1") String pipeModel5,
                                       @RequestParam(value = "pipeLength5", required = false, defaultValue = "") String pipeLength5,
                                       @RequestParam(value = "pipeDiameter5", required = false, defaultValue = "") String pipeDiameter5,
                                       @RequestParam(value = "pipeMaterial5", required = false, defaultValue = "") String pipeMaterial5,
                                       @RequestParam(value = "pipeModel6", required = false, defaultValue = "毛管2") String pipeModel6,
                                       @RequestParam(value = "pipeLength6", required = false, defaultValue = "") String pipeLength6,
                                       @RequestParam(value = "pipeDiameter6", required = false, defaultValue = "") String pipeDiameter6,
                                       @RequestParam(value = "pipeMaterial6", required = false, defaultValue = "") String pipeMaterial6,
                                       //渠道的小渠
                                       @RequestParam(value = "canalModel1", required = false, defaultValue = "干渠1") String canalModel1,
                                       @RequestParam(value = "canalLength1", required = false, defaultValue = "") String canalLength1,
                                       @RequestParam(value = "canalSectionSize1", required = false, defaultValue = "") String canalSectionSize1,
                                       @RequestParam(value = "canalSeepageLength1", required = false, defaultValue = "") String canalSeepageLength1,
                                       @RequestParam(value = "canalLiningSectionSize1", required = false, defaultValue = "") String canalLiningSectionSize1,
                                       @RequestParam(value = "canalLiningMaterial1", required = false, defaultValue = "") String canalLiningMaterial1,
                                       @RequestParam(value = "canalModel2", required = false, defaultValue = "干渠2") String canalModel2,
                                       @RequestParam(value = "canalLength2", required = false, defaultValue = "") String canalLength2,
                                       @RequestParam(value = "canalSectionSize2", required = false, defaultValue = "") String canalSectionSize2,
                                       @RequestParam(value = "canalSeepageLength2", required = false, defaultValue = "") String canalSeepageLength2,
                                       @RequestParam(value = "canalLiningSectionSize2", required = false, defaultValue = "") String canalLiningSectionSize2,
                                       @RequestParam(value = "canalLiningMaterial2", required = false, defaultValue = "") String canalLiningMaterial2,
                                       @RequestParam(value = "canalModel3", required = false, defaultValue = "支渠1") String canalModel3,
                                       @RequestParam(value = "canalLength3", required = false, defaultValue = "") String canalLength3,
                                       @RequestParam(value = "canalSectionSize3", required = false, defaultValue = "") String canalSectionSize3,
                                       @RequestParam(value = "canalSeepageLength3", required = false, defaultValue = "") String canalSeepageLength3,
                                       @RequestParam(value = "canalLiningSectionSize3", required = false, defaultValue = "") String canalLiningSectionSize3,
                                       @RequestParam(value = "canalLiningMaterial3", required = false, defaultValue = "") String canalLiningMaterial3,
                                       @RequestParam(value = "canalModel4", required = false, defaultValue = "支渠2") String canalModel4,
                                       @RequestParam(value = "canalLength4", required = false, defaultValue = "") String canalLength4,
                                       @RequestParam(value = "canalSectionSize4", required = false, defaultValue = "") String canalSectionSize4,
                                       @RequestParam(value = "canalSeepageLength4", required = false, defaultValue = "") String canalSeepageLength4,
                                       @RequestParam(value = "canalLiningSectionSize4", required = false, defaultValue = "") String canalLiningSectionSize4,
                                       @RequestParam(value = "canalLiningMaterial4", required = false, defaultValue = "") String canalLiningMaterial4,
                                       @RequestParam(value = "canalModel5", required = false, defaultValue = "斗渠1") String canalModel5,
                                       @RequestParam(value = "canalLength5", required = false, defaultValue = "") String canalLength5,
                                       @RequestParam(value = "canalSectionSize5", required = false, defaultValue = "") String canalSectionSize5,
                                       @RequestParam(value = "canalSeepageLength5", required = false, defaultValue = "") String canalSeepageLength5,
                                       @RequestParam(value = "canalLiningSectionSize5", required = false, defaultValue = "") String canalLiningSectionSize5,
                                       @RequestParam(value = "canalLiningMaterial5", required = false, defaultValue = "") String canalLiningMaterial5,
                                       @RequestParam(value = "canalModel6", required = false, defaultValue = "斗渠2") String canalModel6,
                                       @RequestParam(value = "canalLength6", required = false, defaultValue = "") String canalLength6,
                                       @RequestParam(value = "canalSectionSize6", required = false, defaultValue = "") String canalSectionSize6,
                                       @RequestParam(value = "canalSeepageLength6", required = false, defaultValue = "") String canalSeepageLength6,
                                       @RequestParam(value = "canalLiningSectionSize6", required = false, defaultValue = "") String canalLiningSectionSize6,
                                       @RequestParam(value = "canalLiningMaterial6", required = false, defaultValue = "") String canalLiningMaterial6,
                                       @RequestParam(value = "canalModel7", required = false, defaultValue = "农渠1") String canalModel7,
                                       @RequestParam(value = "canalLength7", required = false, defaultValue = "") String canalLength7,
                                       @RequestParam(value = "canalSectionSize7", required = false, defaultValue = "") String canalSectionSize7,
                                       @RequestParam(value = "canalSeepageLength7", required = false, defaultValue = "") String canalSeepageLength7,
                                       @RequestParam(value = "canalLiningSectionSize7", required = false, defaultValue = "") String canalLiningSectionSize7,
                                       @RequestParam(value = "canalLiningMaterial7", required = false, defaultValue = "") String canalLiningMaterial7,
                                       @RequestParam(value = "canalModel8", required = false, defaultValue = "农渠2") String canalModel8,
                                       @RequestParam(value = "canalLength8", required = false, defaultValue = "") String canalLength8,
                                       @RequestParam(value = "canalSectionSize8", required = false, defaultValue = "") String canalSectionSize8,
                                       @RequestParam(value = "canalSeepageLength8", required = false, defaultValue = "") String canalSeepageLength8,
                                       @RequestParam(value = "canalLiningSectionSize8", required = false, defaultValue = "") String canalLiningSectionSize8,
                                       @RequestParam(value = "canalLiningMaterial8", required = false, defaultValue = "") String canalLiningMaterial8,
                                       @RequestParam("timeStamp") Long timeStamp) {
        Response response = new Response();
        try {
            if (!authService.checkLogin(userId, token)) {
                return responseService.notLogin(response);
            } else if (authService.checkGuest(userId) || !authService.checkUserTownAccess(userId, townId)
                    || !authService.unitContainingCheck(townId, villageId, groupId)) {
                return responseService.noAccess(response);
            }
            WaterConservationEntity waterConservationEntity = waterConservationRepository.findById(projectId);
            projectService.modifyCommonItems(waterConservationEntity, remark, name, code, manageModel, townId, villageId,
                    groupId, situation, constructTime, constructUnit, propertyOwner, manager, longitude, latitude, timeStamp);
            String category = waterConservationEntity.getCategory();
            ProjectMarkEntity projectMark = waterConservationEntity.getProjectMark();
            String imagePath = "";
            String planeSketchPath = "";
            String internalImagePath = "";
            String externalImagePath = "";
            String sectionImagePath = "";
            String startImagePath = "";
            String middleImagePath = "";
            String endImagePath = "";
            if (image != null) {
                imagePath = fileService.saveFile(image, timeStamp);
            }
            if (planeSketch != null) {
                planeSketchPath = fileService.saveFile(planeSketch, timeStamp);
            }
            if (sectionImage != null) {
                sectionImagePath = fileService.saveFile(sectionImage, timeStamp);
            }
            if (startImage != null) {
                startImagePath = fileService.saveFile(startImage, timeStamp);
            }
            if (middleImage != null) {
                middleImagePath = fileService.saveFile(middleImage, timeStamp);
            }
            if (endImage != null) {
                endImagePath = fileService.saveFile(endImage, timeStamp);
            }
            if (internalImage != null) {
                internalImagePath = fileService.saveFile(internalImage, timeStamp);
            }
            if (externalImage != null) {
                externalImagePath = fileService.saveFile(externalImage, timeStamp);
            }
            switch (category) {
                case "渡槽":
                    AqueductEntity aqueduct = projectMark.getAqueduct();
                    projectService.modifyAqueduct(aqueduct, crossWatercourseLocation, crossCount, sectionSize, structureAndMaterial,
                            crossLength, imagePath);
                    break;
                case "桥梁":
                    BridgeEntity bridgeEntity = projectMark.getBridge();
                    projectService.modifyBridge(bridgeEntity, watercourseLocation, crossCount, structureAndMaterial, loadStandard,
                            crossLength, width, length, imagePath);
                    break;
                case "渠道":
                    ChannelEntity channelEntity = projectMark.getChannel();
                    projectService.modifyChannel(channelEntity, headOrPumpStation, buildingMatchRate, length, goodConditionRate, imagePath, sectionSize,
                            seepageCanalLength, liningSectionSize, sumLength, planeSketchPath, canalLength1, canalLength2, canalLength3, canalLength4,
                            canalLength5, canalLength6, canalLength7, canalLength8, canalLiningMaterial1, canalLiningMaterial2, canalLiningMaterial3,
                            canalLiningMaterial4, canalLiningMaterial5, canalLiningMaterial6, canalLiningMaterial7, canalLiningMaterial8,
                            canalLiningSectionSize1, canalLiningSectionSize2, canalLiningSectionSize3, canalLiningSectionSize4, canalLiningSectionSize5,
                            canalLiningSectionSize6, canalLiningSectionSize7, canalLiningSectionSize8, canalModel1, canalModel2, canalModel3,
                            canalModel4, canalModel5, canalModel6, canalModel7, canalModel8, canalSectionSize1, canalSectionSize2, canalSectionSize3,
                            canalSectionSize4, canalSectionSize5, canalSectionSize6, canalSectionSize7, canalSectionSize8, canalSeepageLength1,
                            canalSeepageLength2, canalSeepageLength3, canalSeepageLength4, canalSeepageLength5, canalSeepageLength6, canalSeepageLength7,
                            canalSeepageLength8, sumSectionSize, sumLiningSectionSize, sumSeepageCanalLength);
                    break;
                case "涵洞":
                    CulvertEntity culvertEntity = projectMark.getCulvert();
                    projectService.modifyCulvert(culvertEntity, watercourseLocation, culvertModel, sectionSize, length, holeModel,
                            doorMaterial, hoistModel, holeMaterial, imagePath);
                    break;
                case "塘坝":
                    DamEntity damEntity = projectMark.getDam();
                    projectService.modifyDam(damEntity, isRegistered, features, mainFunction, isAccountability, feeResources, maintainPersonFee,
                            isCertificated, development, manageRageLine, pondDamManageLine, protectGround, twoLinesBuilding, specifiedManage, imagePath, protectRageManagement);
                    break;
                case "深水井":
                    DeepWellsEntity deepWellsEntity = projectMark.getDeepWells();
                    projectService.modifyDeepWells(deepWellsEntity, irrigateArea, deepPump, diameter, depth, material, imagePath);
                    break;
                case "管滴灌":
                    DripIrrigationPipeEntity dripIrrigationPipeEntity = projectMark.getDripIrrigationPipe();
                    projectService.modifyDripIrrigationPipe(dripIrrigationPipeEntity, irrigateArea, intakeWay, waterResource, sumLength, sumDiameter,
                            imagePath, planeSketchPath, pipeDiameter1, pipeDiameter2, pipeDiameter3, pipeDiameter4, pipeDiameter5, pipeDiameter6,
                            pipeLength1, pipeLength2, pipeLength3, pipeLength4, pipeLength5, pipeLength6, pipeMaterial1, pipeMaterial2, pipeMaterial3,
                            pipeMaterial4, pipeMaterial5, pipeMaterial6, pipeModel1, pipeModel2, pipeModel3, pipeModel4, pipeModel5, pipeModel6, length);
                    break;
                case "大口井":
                    GreatWellsEntity greatWellsEntity = projectMark.getGreatWells();
                    projectService.modifyGreatWells(greatWellsEntity, irrigateArea, waterCapacity, size, depth, modelAndMaterial, imagePath);
                    break;
                case "水电站":
                    HydropowerEntity hydropowerEntity = projectMark.getHydropower();
                    projectService.modifyHydropower(hydropowerEntity, irrigateArea, paddyFieldArea, drainageArea, irrigateFee, drainageFee,
                            annualFee, watercourseLocation, machineArea, affiliation, sumElectricCapacity, averageCapacity, internalImagePath,
                            externalImagePath, problem, transformerCapacity1, transformerCapacity2, transformerCapacity3, transformerModel1,
                            transformerModel2, transformerModel3, turbineCount1, turbineCount2, turbineCount3, turbineFactoryDate1,
                            turbineFactoryDate2, turbineFactoryDate3, turbineModel1, turbineModel2, turbineModel3, turbineTurnsOrFlow1,
                            turbineTurnsOrFlow2, turbineTurnsOrFlow3, generatorFactoryDate1, generatorFactoryDate2, generatorFactoryDate3,
                            generatorModel1, generatorModel2, generatorModel3, generatorPower1, generatorPower2, generatorPower3);
                    break;
                case "水塘":
                    PondEntity pondEntity = projectMark.getPond();
                    projectService.modifyPond(pondEntity, mainFunction, lastDredgingTime, waterArea, waterCapacity, imagePath);
                    break;
                case "泵站":
                    PumpStationEntity pumpStationEntity = projectMark.getPumpStation();
                    projectService.modifyPumpStation(pumpStationEntity, watercourseLocation, irrigateArea, paddyFieldArea, drainageArea,
                            irrigateFee, drainageFee, annualFee, nature, machineArea, totalInstalledCapacity, riverElevation, poolHeight,
                            internalImagePath, externalImagePath, problem, transformerCapacity1, transformerCapacity2, transformerCapacity3,
                            transformerModel1, transformerModel2, transformerModel3, pumpCount1, pumpCount2, pumpCount3, pumpFactoryDate1,
                            pumpFactoryDate2, pumpFactoryDate3, pumpLiftOrFlow1, pumpLiftOrFlow2, pumpLiftOrFlow3, pumpModel1, pumpModel2,
                            pumpModel3, electricFactoryDate1, electricFactoryDate2, electricFactoryDate3, electricMotorModel1,
                            electricMotorModel2, electricMotorModel3, electricPower1, electricPower2, electricPower3);
                    break;
                case "水闸":
                    SluiceEntity sluiceEntity = projectMark.getSluice();
                    projectService.modifySluice(sluiceEntity, watercourseLocation, model, holeCount, door, hoistTonnage, holeHeight,
                            holeWidth, doorHeight, doorWidth, hoistModel, buildingSituation, doorSituation, hoistSituation, imagePath);
                    break;
                case "河道":
                    WatercourseEntity watercourseEntity = projectMark.getWatercourse();
                    projectService.modifyWaterCourse(watercourseEntity, length, lastDredgingTime, estuaryHeight, estuaryWidth, leftWidth,
                            rightWidth, hediHeight, hediWidth, flowVillages, nature, sectionImagePath, startImagePath, middleImagePath,
                            endImagePath, endpointLatitude, endpointLongitude, timeStamp);
                    break;
                case "水厂":
                    WaterWorksEntity waterWorksEntity = projectMark.getWaterWorks();
                    projectService.modifyWaterWorks(waterWorksEntity, provideAmount, waterModel, haveCleaner, isRegularCheck, dayProvideAmount,
                            provideVillageCount, providePopulation, haveProtectArea, imagePath);
                    break;
                default:
                    break;
            }
            return responseService.success(response);
        } catch (IOException e) {
            return responseService.saveFileError(response);
        } catch (Exception e) {
            return responseService.serverError(response);
        }
    }
}
