package com.saltyfish.framework.controller;

import com.saltyfish.common.bean.Response;
import com.saltyfish.domain.entity.project.WaterConservationEntity;
import com.saltyfish.domain.repository.project.WaterConservationRepository;
import com.saltyfish.framework.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by weck on 16/9/19.
 */
@RequestMapping("/excel")
@RestController
public class ExcelController {

    @Autowired
    private AuthService authService;

    @Autowired
    private ResponseService responseService;

    @Autowired
    private ExcelService excelService;

    @Autowired
    private WaterConservationRepository waterConservationRepository;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UnitService unitService;


    @RequestMapping("/exportSummary")
    public Response exportSummary(@RequestParam("userId") Integer userId,
                                  @RequestParam("token") String token,
                                  @RequestParam("category") String category,
                                  HttpServletResponse httpServletResponse) {
        Response response = new Response();
        try {
            if (!authService.checkLogin(userId, token)) {
                return responseService.notLogin(response);
            } else {
                List<WaterConservationEntity> projects = projectService.getConservationsByCategory(unitService.getAccessedTownIds(userId), category);
                switch (category) {
                    case "渡槽":
                        excelService.exportAqueductSummary(httpServletResponse, projects);
                        break;
                    case "桥梁":
                        excelService.exportBridgeSummary(httpServletResponse, projects);
                        break;
                    case "渠道":
                        excelService.exportChannelSummary(httpServletResponse, projects);
                        break;
                    case "涵洞":
                        excelService.exportCulvertSummary(httpServletResponse, projects);
                        break;
                    case "塘坝":
                        excelService.exportDamSummary(httpServletResponse, projects);
                        break;
                    case "深水井":
                        excelService.exportDeepWellsSummary(httpServletResponse, projects);
                        break;
                    case "管滴灌":
                        excelService.exportDripIrrigationPipeSummary(httpServletResponse, projects);
                        break;
                    case "大口井":
                        excelService.exportGreatWellsSummary(httpServletResponse, projects);
                        break;
                    case "水电站":
                        excelService.exportHydropowerSummary(httpServletResponse, projects);
                        break;
                    case "水塘":
                        excelService.exportPondSummary(httpServletResponse, projects);
                        break;
                    case "泵站":
                        excelService.exportPumpStationSummary(httpServletResponse, projects);
                        break;
                    case "水闸":
                        excelService.exportSluiceSummary(httpServletResponse, projects);
                        break;
                    case "河道":
                        excelService.exportWatercourseSummary(httpServletResponse, projects);
                        break;
                    case "水厂":
                        excelService.exportWaterWorksSummary(httpServletResponse, projects);
                        break;
                    default:
                        break;
                }
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return responseService.serverError(response);
        }
    }

    /**
     * 导出工程
     *
     * @param userId              用户id
     * @param token               登录token
     * @param projectId           工程id
     * @param httpServletResponse 响应
     * @return 文件流
     */
    @RequestMapping("/exportProject")
    public Response exportProject(@RequestParam("userId") Integer userId,
                                  @RequestParam("token") String token,
                                  @RequestParam("projectId") Integer projectId,
                                  HttpServletResponse httpServletResponse) {
        Response response = new Response();
        try {
            if (!authService.checkLogin(userId, token)) {
                return responseService.notLogin(response);
//                return "未登录";
            } else if (!authService.checkUserProjectTownAccess(userId, projectId)) {
                return responseService.noAccess(response);
//                return "没有权限";
            } else {
                WaterConservationEntity waterConservationEntity = waterConservationRepository.findById(projectId);
                String category = waterConservationEntity.getCategory();
                switch (category) {
                    case "渡槽":
                        excelService.exportAqueduct(waterConservationEntity, httpServletResponse);
                        break;
                    case "桥梁":
                        excelService.exportBridge(waterConservationEntity, httpServletResponse);
                        break;
                    case "渠道":
                        excelService.exportChannel(waterConservationEntity, httpServletResponse);
                        break;
                    case "涵洞":
                        excelService.exportCulvert(waterConservationEntity, httpServletResponse);
                        break;
                    case "塘坝":
                        excelService.exportDam(waterConservationEntity, httpServletResponse);
                        break;
                    case "深水井":
                        excelService.exportDeepWells(waterConservationEntity, httpServletResponse);
                        break;
                    case "管滴灌":
                        excelService.exportDripIrrigationPipe(waterConservationEntity, httpServletResponse);
                        break;
                    case "大口井":
                        excelService.exportGreateWells(waterConservationEntity, httpServletResponse);
                        break;
                    case "水电站":
                        excelService.exportHydropower(waterConservationEntity, httpServletResponse);
                        break;
                    case "水塘":
                        excelService.exportPond(waterConservationEntity, httpServletResponse);
                        break;
                    case "泵站":
                        excelService.exportPumpStation(waterConservationEntity, httpServletResponse);
                        break;
                    case "水闸":
                        excelService.exportSluice(waterConservationEntity, httpServletResponse);
                        break;
                    case "河道":
                        excelService.exportWatercourse(waterConservationEntity, httpServletResponse);
                        break;
                    case "水厂":
                        excelService.exportWaterWorks(waterConservationEntity, httpServletResponse);
                        break;
                    default:
                        break;
                }
                return null;
            }
        } catch (Exception e) {
            return responseService.serverError(response);
//            return "服务器错误";
        }
    }
}
