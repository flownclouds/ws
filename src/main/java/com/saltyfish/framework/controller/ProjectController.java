package com.saltyfish.framework.controller;

import com.saltyfish.common.bean.ConservationSummary;
import com.saltyfish.common.bean.Response;
import com.saltyfish.domain.entity.conservation.*;
import com.saltyfish.domain.repository.conservation.*;
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
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Autowired
    private ResponseService responseService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private FileService fileService;

    @Autowired
    private AqueductRepository aqueductRepository;

    @Autowired
    private BridgeRepository bridgeRepository;

    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private CulvertRepository culvertRepository;

    @Autowired
    private DamRepository damRepository;

    @Autowired
    private DeepWellsRepository deepWellsRepository;

    @Autowired
    private DripIrrigationPipeRepository dripIrrigationPipeRepository;

    @Autowired
    private GreatWellsRepository greatWellsRepository;

    @Autowired
    private HydropowerRepository hydropowerRepository;

    @Autowired
    private PumpStationRepository pumpStationRepository;

    @Autowired
    private PondRepository pondRepository;

    @Autowired
    private SluiceRepository sluiceRepository;

    @Autowired
    private WaterWorksRepository waterWorksRepository;

    @Autowired
    private WatercourseRepository watercourseRepository;


    @RequestMapping("/summary")
    public Response summary(@RequestParam("userId") Integer userId,
                            @RequestParam("token") String token){
        Response response = new Response();
        try {
            if (!authService.checkLogin(userId, token)) {
                return responseService.notLogin(response);
            }
            List<ConservationSummary> summaries = projectService.summary(userId);
            Map<String,Object> data = new HashMap<>();
            data.put("summaries",summaries);
            response.setCode(HttpStatus.OK.value());
            response.setData(data);
            return response;
        }catch (Exception e){
            e.printStackTrace();
            return responseService.serverError(response);
        }
    }


    @RequestMapping("/queryConservations")
    public Response queryConservations(@RequestParam("userId") Integer userId,
                                       @RequestParam("token") String token,
                                       @RequestParam(value = "category", required = false) String category,
                                       @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                       @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
                                       @RequestParam(value = "name",required = false) String name,
                                       @RequestParam(value = "code",required = false) String code,
                                       @RequestParam(value = "startTime",required = false) Long startTime,
                                       @RequestParam(value = "endTime",required = false) Long endTime,
                                       @RequestParam(value = "manageModel",required = false) String manageModel,
                                       @RequestParam(value = "townId",required = false) Integer townId,
                                       @RequestParam(value = "villageId",required = false) Integer villageId,
                                       @RequestParam(value = "groupId",required = false) Integer groupId){
        Response response = new Response();
        try {
            if (!authService.checkLogin(userId, token)) {
                return responseService.notLogin(response);
            }
            else {
                if (townId!=null){
                    if (!authService.checkUserTownAccess(userId,townId)){
                        return responseService.noAccess(response);
                    }
                    else if (villageId!=null&&!authService.unitContainingCheck(townId,villageId,groupId)){
                        return responseService.noAccess(response);
                    }
                }
                Map<String,Object> data = new HashMap<>();
                data.put("conservations",projectService.queryConservations(userId,category,page,size,name,code,startTime,endTime,manageModel,townId,villageId,groupId));
                response.setData(data);
                response.setCode(HttpStatus.OK.value());
                return response;
            }
        }catch (Exception e){
            e.printStackTrace();
            return responseService.serverError(response);
        }
    }

    @RequestMapping("/deleteProject")
    public Response deleteProject(@RequestParam("userId") Integer userId,
                                  @RequestParam("token") String token,
                                  @RequestParam("projectId") String projectId,
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

    @RequestMapping("/getConservations")
    public Response getConservations(@RequestParam("userId") Integer userId,
                                     @RequestParam("token") String token,
                                     @RequestParam(value = "category", required = false) String category,
                                     @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                     @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        Response response = new Response();
        try {
            if (!authService.checkLogin(userId, token)) {
                return responseService.notLogin(response);
            } else {
                Map<String, Object> data = new HashMap<>();
                if (category == null) {
                    data.put("conservations", projectService.getConservationsInAccessedTowns(userId, page, size));
                } else {
                    data.put("conservations", projectService.getConservations(userId, category, page, size));
                }
                response.setCode(HttpStatus.OK.value());
                response.setData(data);
                return response;
            }
        } catch (Exception e) {
            return responseService.serverError(response);
        }
    }

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
                               @RequestParam("projectId") String projectId) {
        Response response = new Response();
        try {
            if (!authService.checkLogin(userId, token)) {
                return responseService.notLogin(response);
            } else if (!authService.checkUserProjectTownAccess(userId, projectId) || authService.checkGuest(userId)) {
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

    @RequestMapping("/modifyConservation")
    public Response modifyProject(@RequestParam("userId") Integer userId,
                                  @RequestParam("token") String token,
                                  @RequestParam("projectId") String projectId,
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
            String imagePath = "";
            String planeSketchPath = "";
            String internalImagePath = "";
            String externalImagePath = "";
            String sectionImagePath = "";
            String startImagePath = "";
            String middleImagePath = "";
            String endImagePath = "";
            if (image != null && !image.isEmpty()) {
                imagePath = fileService.saveFile(image, timeStamp);
            }
            if (planeSketch != null && !planeSketch.isEmpty()) {
                planeSketchPath = fileService.saveFile(planeSketch, timeStamp);
            }
            if (sectionImage != null && !sectionImage.isEmpty()) {
                sectionImagePath = fileService.saveFile(sectionImage, timeStamp);
            }
            if (startImage != null && !startImage.isEmpty()) {
                startImagePath = fileService.saveFile(startImage, timeStamp);
            }
            if (middleImage != null && !middleImage.isEmpty()) {
                middleImagePath = fileService.saveFile(middleImage, timeStamp);
            }
            if (endImage != null && !endImage.isEmpty()) {
                endImagePath = fileService.saveFile(endImage, timeStamp);
            }
            if (internalImage != null && !internalImage.isEmpty()) {
                internalImagePath = fileService.saveFile(internalImage, timeStamp);
            }
            if (externalImage != null && !externalImage.isEmpty()) {
                externalImagePath = fileService.saveFile(externalImage, timeStamp);
            }
            switch (category) {
                case "渡槽":
                    AqueductEntity aqueductEntity = aqueductRepository.findById(projectId);
                    projectService.modifyCommonProperty(aqueductEntity, userId, remark, name, code, manageModel, townId, villageId,
                            groupId, situation, constructTime, constructUnit, propertyOwner, manager, longitude, latitude, timeStamp);
                    projectService.saveAqueduct(aqueductEntity, crossWatercourseLocation, crossCount, sectionSize, structureAndMaterial,
                            crossLength, imagePath);
                    break;
                case "桥梁":
                    BridgeEntity bridgeEntity = bridgeRepository.findById(projectId);
                    projectService.modifyCommonProperty(bridgeEntity, userId, remark, name, code, manageModel, townId, villageId,
                            groupId, situation, constructTime, constructUnit, propertyOwner, manager, longitude, latitude, timeStamp);
                    projectService.saveBridge(bridgeEntity, watercourseLocation, crossCount, structureAndMaterial, loadStandard,
                            crossLength, width, length, imagePath);
                    break;
                case "渠道":
                    ChannelEntity channelEntity = channelRepository.findById(projectId);
                    projectService.modifyCommonProperty(channelEntity, userId, remark, name, code, manageModel, townId, villageId,
                            groupId, situation, constructTime, constructUnit, propertyOwner, manager, longitude, latitude, timeStamp);
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
                    break;
                case "涵洞":
                    CulvertEntity culvertEntity = culvertRepository.findById(projectId);
                    projectService.modifyCommonProperty(culvertEntity, userId, remark, name, code, manageModel, townId, villageId,
                            groupId, situation, constructTime, constructUnit, propertyOwner, manager, longitude, latitude, timeStamp);
                    projectService.saveCulvert(culvertEntity, watercourseLocation, culvertModel, sectionSize, length, holeModel,
                            doorMaterial, hoistModel, holeMaterial, imagePath);
                    break;
                case "塘坝":
                    DamEntity damEntity = damRepository.findById(projectId);
                    projectService.modifyCommonProperty(damEntity, userId, remark, name, code, manageModel, townId, villageId,
                            groupId, situation, constructTime, constructUnit, propertyOwner, manager, longitude, latitude, timeStamp);
                    projectService.saveDam(damEntity, isRegistered, features, mainFunction, isAccountability, feeResources, maintainPersonFee,
                            isCertificated, development, manageRageLine, pondDamManageLine, protectGround, twoLinesBuilding, specifiedManage,
                            imagePath, protectRageManagement);
                    break;
                case "深水井":
                    DeepWellsEntity deepWellsEntity = deepWellsRepository.findById(projectId);
                    projectService.modifyCommonProperty(deepWellsEntity, userId, remark, name, code, manageModel, townId, villageId,
                            groupId, situation, constructTime, constructUnit, propertyOwner, manager, longitude, latitude, timeStamp);
                    projectService.saveDeepWells(deepWellsEntity, irrigateArea, deepPump, diameter, depth, material, imagePath);
                    break;
                case "管滴灌":
                    DripIrrigationPipeEntity dripIrrigationPipeEntity = dripIrrigationPipeRepository.findById(projectId);
                    projectService.modifyCommonProperty(dripIrrigationPipeEntity, userId, remark, name, code, manageModel, townId, villageId,
                            groupId, situation, constructTime, constructUnit, propertyOwner, manager, longitude, latitude, timeStamp);
                    projectService.saveDripIrrigationPipe(dripIrrigationPipeEntity, irrigateArea, intakeWay, waterResource, sumLength, sumDiameter,
                            imagePath, planeSketchPath, pipeDiameter1, pipeDiameter2, pipeDiameter3, pipeDiameter4, pipeDiameter5, pipeDiameter6,
                            pipeLength1, pipeLength2, pipeLength3, pipeLength4, pipeLength5, pipeLength6, pipeMaterial1, pipeMaterial2, pipeMaterial3,
                            pipeMaterial4, pipeMaterial5, pipeMaterial6, pipeModel1, pipeModel2, pipeModel3, pipeModel4, pipeModel5, pipeModel6, length);
                    break;
                case "大口井":
                    GreatWellsEntity greatWellsEntity = greatWellsRepository.findById(projectId);
                    projectService.modifyCommonProperty(greatWellsEntity, userId, remark, name, code, manageModel, townId, villageId,
                            groupId, situation, constructTime, constructUnit, propertyOwner, manager, longitude, latitude, timeStamp);
                    projectService.saveGreatWells(greatWellsEntity, irrigateArea, waterCapacity, size, depth, modelAndMaterial, imagePath);
                    break;
                case "水电站":
                    HydropowerEntity hydropowerEntity = hydropowerRepository.findById(projectId);
                    projectService.modifyCommonProperty(hydropowerEntity, userId, remark, name, code, manageModel, townId, villageId,
                            groupId, situation, constructTime, constructUnit, propertyOwner, manager, longitude, latitude, timeStamp);
                    projectService.saveHydropower(hydropowerEntity, irrigateArea, paddyFieldArea, drainageArea, irrigateFee, drainageFee,
                            annualFee, watercourseLocation, machineArea, affiliation, sumElectricCapacity, averageCapacity, internalImagePath,
                            externalImagePath, problem, transformerCapacity1, transformerCapacity2, transformerCapacity3, transformerModel1,
                            transformerModel2, transformerModel3, turbineCount1, turbineCount2, turbineCount3, turbineFactoryDate1,
                            turbineFactoryDate2, turbineFactoryDate3, turbineModel1, turbineModel2, turbineModel3, turbineTurnsOrFlow1,
                            turbineTurnsOrFlow2, turbineTurnsOrFlow3, generatorFactoryDate1, generatorFactoryDate2, generatorFactoryDate3,
                            generatorModel1, generatorModel2, generatorModel3, generatorPower1, generatorPower2, generatorPower3);
                    break;
                case "水塘":
                    PondEntity pondEntity = pondRepository.findById(projectId);
                    projectService.modifyCommonProperty(pondEntity, userId, remark, name, code, manageModel, townId, villageId,
                            groupId, situation, constructTime, constructUnit, propertyOwner, manager, longitude, latitude, timeStamp);
                    projectService.savePond(pondEntity, mainFunction, lastDredgingTime, waterArea, waterCapacity, imagePath);
                    break;
                case "泵站":
                    PumpStationEntity pumpStationEntity = pumpStationRepository.findById(projectId);
                    projectService.modifyCommonProperty(pumpStationEntity, userId, remark, name, code, manageModel, townId, villageId,
                            groupId, situation, constructTime, constructUnit, propertyOwner, manager, longitude, latitude, timeStamp);
                    projectService.savePumpStation(pumpStationEntity, watercourseLocation, irrigateArea, paddyFieldArea, drainageArea,
                            irrigateFee, drainageFee, annualFee, nature, machineArea, totalInstalledCapacity, riverElevation, poolHeight,
                            internalImagePath, externalImagePath, problem, transformerCapacity1, transformerCapacity2, transformerCapacity3,
                            transformerModel1, transformerModel2, transformerModel3, pumpCount1, pumpCount2, pumpCount3, pumpFactoryDate1,
                            pumpFactoryDate2, pumpFactoryDate3, pumpLiftOrFlow1, pumpLiftOrFlow2, pumpLiftOrFlow3, pumpModel1, pumpModel2,
                            pumpModel3, electricFactoryDate1, electricFactoryDate2, electricFactoryDate3, electricMotorModel1,
                            electricMotorModel2, electricMotorModel3, electricPower1, electricPower2, electricPower3);
                    break;
                case "水闸":
                    SluiceEntity sluiceEntity = sluiceRepository.findById(projectId);
                    projectService.modifyCommonProperty(sluiceEntity, userId, remark, name, code, manageModel, townId, villageId,
                            groupId, situation, constructTime, constructUnit, propertyOwner, manager, longitude, latitude, timeStamp);
                    projectService.saveSluice(sluiceEntity, watercourseLocation, model, holeCount, door, hoistTonnage, holeHeight,
                            holeWidth, doorHeight, doorWidth, hoistModel, buildingSituation, doorSituation, hoistSituation, imagePath);
                    break;
                case "河道":
                    WatercourseEntity watercourseEntity = watercourseRepository.findById(projectId);
                    projectService.modifyCommonProperty(watercourseEntity, userId, remark, name, code, manageModel, townId, villageId,
                            groupId, situation, constructTime, constructUnit, propertyOwner, manager, longitude, latitude, timeStamp);
                    projectService.saveWatercourse(watercourseEntity, length, lastDredgingTime, estuaryHeight, estuaryWidth, leftWidth,
                            rightWidth, hediHeight, hediWidth, flowVillages, nature, sectionImagePath, startImagePath, middleImagePath,
                            endImagePath, endpointLatitude, endpointLongitude);
                    break;
                case "水厂":
                    WaterWorksEntity waterWorksEntity = waterWorksRepository.findById(projectId);
                    projectService.modifyCommonProperty(waterWorksEntity, userId, remark, name, code, manageModel, townId, villageId,
                            groupId, situation, constructTime, constructUnit, propertyOwner, manager, longitude, latitude, timeStamp);
                    projectService.saveWaterWorks(waterWorksEntity, provideAmount, waterModel, haveCleaner, isRegularCheck, dayProvideAmount,
                            provideVillageCount, providePopulation, haveProtectArea, imagePath);
                    break;
                default:
                    break;
            }
            return responseService.success(response);
        } catch (IOException e) {
            e.printStackTrace();
            return responseService.saveFileError(response);
        } catch (Exception e) {
            e.printStackTrace();
            return responseService.serverError(response);
        }
    }


    @RequestMapping("/addConservation")
    public Response addProject(@RequestParam("userId") Integer userId,
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
            String imagePath = "";
            String planeSketchPath = "";
            String internalImagePath = "";
            String externalImagePath = "";
            String sectionImagePath = "";
            String startImagePath = "";
            String middleImagePath = "";
            String endImagePath = "";
            if (image != null&&!image.isEmpty()) {
                imagePath = fileService.saveFile(image, timeStamp);
            }
            if (planeSketch != null&&!planeSketch.isEmpty()) {
                planeSketchPath = fileService.saveFile(planeSketch, timeStamp);
            }
            if (sectionImage != null&&!sectionImage.isEmpty()) {
                sectionImagePath = fileService.saveFile(sectionImage, timeStamp);
            }
            if (startImage != null&&!startImage.isEmpty()) {
                startImagePath = fileService.saveFile(startImage, timeStamp);
            }
            if (middleImage != null&&!middleImage.isEmpty()) {
                middleImagePath = fileService.saveFile(middleImage, timeStamp);
            }
            if (endImage != null&&!endImage.isEmpty()) {
                endImagePath = fileService.saveFile(endImage, timeStamp);
            }
            if (internalImage != null&&!internalImage.isEmpty()) {
                internalImagePath = fileService.saveFile(internalImage, timeStamp);
            }
            if (externalImage != null&&!externalImage.isEmpty()) {
                externalImagePath = fileService.saveFile(externalImage, timeStamp);
            }
            switch (category) {
                case "渡槽":
                    AqueductEntity aqueductEntity = new AqueductEntity();
                    projectService.setCommonProperty(aqueductEntity, userId, category, remark, name, code, manageModel, townId, villageId,
                            groupId, situation, constructTime, constructUnit, propertyOwner, manager, longitude, latitude, timeStamp);
                    projectService.saveAqueduct(aqueductEntity, crossWatercourseLocation, crossCount, sectionSize, structureAndMaterial,
                            crossLength, imagePath);
                    break;
                case "桥梁":
                    BridgeEntity bridgeEntity = new BridgeEntity();
                    projectService.setCommonProperty(bridgeEntity, userId, category, remark, name, code, manageModel, townId, villageId,
                            groupId, situation, constructTime, constructUnit, propertyOwner, manager, longitude, latitude, timeStamp);
                    projectService.saveBridge(bridgeEntity, watercourseLocation, crossCount, structureAndMaterial, loadStandard,
                            crossLength, width, length, imagePath);
                    break;
                case "渠道":
                    ChannelEntity channelEntity = new ChannelEntity();
                    projectService.setCommonProperty(channelEntity, userId, category, remark, name, code, manageModel, townId, villageId,
                            groupId, situation, constructTime, constructUnit, propertyOwner, manager, longitude, latitude, timeStamp);
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
                    break;
                case "涵洞":
                    CulvertEntity culvertEntity = new CulvertEntity();
                    projectService.setCommonProperty(culvertEntity, userId, category, remark, name, code, manageModel, townId, villageId,
                            groupId, situation, constructTime, constructUnit, propertyOwner, manager, longitude, latitude, timeStamp);
                    projectService.saveCulvert(culvertEntity, watercourseLocation, culvertModel, sectionSize, length, holeModel,
                            doorMaterial, hoistModel, holeMaterial, imagePath);
                    break;
                case "塘坝":
                    DamEntity damEntity = new DamEntity();
                    projectService.setCommonProperty(damEntity, userId, category, remark, name, code, manageModel, townId, villageId,
                            groupId, situation, constructTime, constructUnit, propertyOwner, manager, longitude, latitude, timeStamp);
                    projectService.saveDam(damEntity, isRegistered, features, mainFunction, isAccountability, feeResources, maintainPersonFee,
                            isCertificated, development, manageRageLine, pondDamManageLine, protectGround, twoLinesBuilding, specifiedManage,
                            imagePath, protectRageManagement);
                    break;
                case "深水井":
                    DeepWellsEntity deepWellsEntity = new DeepWellsEntity();
                    projectService.setCommonProperty(deepWellsEntity, userId, category, remark, name, code, manageModel, townId, villageId,
                            groupId, situation, constructTime, constructUnit, propertyOwner, manager, longitude, latitude, timeStamp);
                    projectService.saveDeepWells(deepWellsEntity, irrigateArea, deepPump, diameter, depth, material, imagePath);
                    break;
                case "管滴灌":
                    DripIrrigationPipeEntity dripIrrigationPipeEntity = new DripIrrigationPipeEntity();
                    projectService.setCommonProperty(dripIrrigationPipeEntity, userId, category, remark, name, code, manageModel, townId, villageId,
                            groupId, situation, constructTime, constructUnit, propertyOwner, manager, longitude, latitude, timeStamp);
                    projectService.saveDripIrrigationPipe(dripIrrigationPipeEntity, irrigateArea, intakeWay, waterResource, sumLength, sumDiameter,
                            imagePath, planeSketchPath, pipeDiameter1, pipeDiameter2, pipeDiameter3, pipeDiameter4, pipeDiameter5, pipeDiameter6,
                            pipeLength1, pipeLength2, pipeLength3, pipeLength4, pipeLength5, pipeLength6, pipeMaterial1, pipeMaterial2, pipeMaterial3,
                            pipeMaterial4, pipeMaterial5, pipeMaterial6, pipeModel1, pipeModel2, pipeModel3, pipeModel4, pipeModel5, pipeModel6, length);
                    break;
                case "大口井":
                    GreatWellsEntity greatWellsEntity = new GreatWellsEntity();
                    projectService.setCommonProperty(greatWellsEntity, userId, category, remark, name, code, manageModel, townId, villageId,
                            groupId, situation, constructTime, constructUnit, propertyOwner, manager, longitude, latitude, timeStamp);
                    projectService.saveGreatWells(greatWellsEntity, irrigateArea, waterCapacity, size, depth, modelAndMaterial, imagePath);
                    break;
                case "水电站":
                    HydropowerEntity hydropowerEntity = new HydropowerEntity();
                    projectService.setCommonProperty(hydropowerEntity, userId, category, remark, name, code, manageModel, townId, villageId,
                            groupId, situation, constructTime, constructUnit, propertyOwner, manager, longitude, latitude, timeStamp);
                    projectService.saveHydropower(hydropowerEntity, irrigateArea, paddyFieldArea, drainageArea, irrigateFee, drainageFee,
                            annualFee, watercourseLocation, machineArea, affiliation, sumElectricCapacity, averageCapacity, internalImagePath,
                            externalImagePath, problem, transformerCapacity1, transformerCapacity2, transformerCapacity3, transformerModel1,
                            transformerModel2, transformerModel3, turbineCount1, turbineCount2, turbineCount3, turbineFactoryDate1,
                            turbineFactoryDate2, turbineFactoryDate3, turbineModel1, turbineModel2, turbineModel3, turbineTurnsOrFlow1,
                            turbineTurnsOrFlow2, turbineTurnsOrFlow3, generatorFactoryDate1, generatorFactoryDate2, generatorFactoryDate3,
                            generatorModel1, generatorModel2, generatorModel3, generatorPower1, generatorPower2, generatorPower3);
                    break;
                case "水塘":
                    PondEntity pondEntity = new PondEntity();
                    projectService.setCommonProperty(pondEntity, userId, category, remark, name, code, manageModel, townId, villageId,
                            groupId, situation, constructTime, constructUnit, propertyOwner, manager, longitude, latitude, timeStamp);
                    projectService.savePond(pondEntity, mainFunction, lastDredgingTime, waterArea, waterCapacity, imagePath);
                    break;
                case "泵站":
                    PumpStationEntity pumpStationEntity = new PumpStationEntity();
                    projectService.setCommonProperty(pumpStationEntity, userId, category, remark, name, code, manageModel, townId, villageId,
                            groupId, situation, constructTime, constructUnit, propertyOwner, manager, longitude, latitude, timeStamp);
                    projectService.savePumpStation(pumpStationEntity, watercourseLocation, irrigateArea, paddyFieldArea, drainageArea,
                            irrigateFee, drainageFee, annualFee, nature, machineArea, totalInstalledCapacity, riverElevation, poolHeight,
                            internalImagePath, externalImagePath, problem, transformerCapacity1, transformerCapacity2, transformerCapacity3,
                            transformerModel1, transformerModel2, transformerModel3, pumpCount1, pumpCount2, pumpCount3, pumpFactoryDate1,
                            pumpFactoryDate2, pumpFactoryDate3, pumpLiftOrFlow1, pumpLiftOrFlow2, pumpLiftOrFlow3, pumpModel1, pumpModel2,
                            pumpModel3, electricFactoryDate1, electricFactoryDate2, electricFactoryDate3, electricMotorModel1,
                            electricMotorModel2, electricMotorModel3, electricPower1, electricPower2, electricPower3);
                    break;
                case "水闸":
                    SluiceEntity sluiceEntity = new SluiceEntity();
                    projectService.setCommonProperty(sluiceEntity, userId, category, remark, name, code, manageModel, townId, villageId,
                            groupId, situation, constructTime, constructUnit, propertyOwner, manager, longitude, latitude, timeStamp);
                    projectService.saveSluice(sluiceEntity, watercourseLocation, model, holeCount, door, hoistTonnage, holeHeight,
                            holeWidth, doorHeight, doorWidth, hoistModel, buildingSituation, doorSituation, hoistSituation, imagePath);
                    break;
                case "河道":
                    WatercourseEntity watercourseEntity = new WatercourseEntity();
                    projectService.setCommonProperty(watercourseEntity, userId, category, remark, name, code, manageModel, townId, villageId,
                            groupId, situation, constructTime, constructUnit, propertyOwner, manager, longitude, latitude, timeStamp);
                    projectService.saveWatercourse(watercourseEntity, length, lastDredgingTime, estuaryHeight, estuaryWidth, leftWidth,
                            rightWidth, hediHeight, hediWidth, flowVillages, nature, sectionImagePath, startImagePath, middleImagePath,
                            endImagePath, endpointLatitude, endpointLongitude);
                    break;
                case "水厂":
                    WaterWorksEntity waterWorksEntity = new WaterWorksEntity();
                    projectService.setCommonProperty(waterWorksEntity, userId, category, remark, name, code, manageModel, townId, villageId,
                            groupId, situation, constructTime, constructUnit, propertyOwner, manager, longitude, latitude, timeStamp);
                    projectService.saveWaterWorks(waterWorksEntity, provideAmount, waterModel, haveCleaner, isRegularCheck, dayProvideAmount,
                            provideVillageCount, providePopulation, haveProtectArea, imagePath);
                    break;
                default:
                    break;
            }
            return responseService.success(response);
        }
        catch (IOException e){
            return responseService.saveFileError(response);
        }
        catch (Exception e) {
            e.printStackTrace();
            return responseService.serverError(response);
        }
    }

}
