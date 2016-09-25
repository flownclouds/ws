package com.saltyfish.framework.controller;

import com.saltyfish.common.bean.Response;
import com.saltyfish.domain.entity.conservation.*;
import com.saltyfish.domain.entity.superbean.ConservationEntity;
import com.saltyfish.domain.repository.ConservationRepository;
import com.saltyfish.framework.service.AuthService;
import com.saltyfish.framework.service.ExcelService;
import com.saltyfish.framework.service.ProjectService;
import com.saltyfish.framework.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    private ProjectService projectService;

    @Autowired
    private ConservationRepository conservationRepository;


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
                switch (category) {
                    case "渡槽":
                        excelService.exportAqueductSummary(httpServletResponse, projectService.getAqueducts(userId));
                        break;
                    case "桥梁":
                        excelService.exportBridgeSummary(httpServletResponse, projectService.getBridges(userId));
                        break;
                    case "渠道":
                        excelService.exportChannelSummary(httpServletResponse, projectService.getChannels(userId));
                        break;
                    case "涵洞":
                        excelService.exportCulvertSummary(httpServletResponse, projectService.getCulverts(userId));
                        break;
                    case "塘坝":
                        excelService.exportDamSummary(httpServletResponse, projectService.getDams(userId));
                        break;
                    case "深水井":
                        excelService.exportDeepWellsSummary(httpServletResponse, projectService.getDeepWells(userId));
                        break;
                    case "管滴灌":
                        excelService.exportDripIrrigationPipeSummary(httpServletResponse, projectService.getDripIrrigationPipe(userId));
                        break;
                    case "大口井":
                        excelService.exportGreatWellsSummary(httpServletResponse, projectService.getGreatWells(userId));
                        break;
                    case "水电站":
                        excelService.exportHydropowerSummary(httpServletResponse, projectService.getHydropowers(userId));
                        break;
                    case "水塘":
                        excelService.exportPondSummary(httpServletResponse, projectService.getPonds(userId));
                        break;
                    case "泵站":
                        excelService.exportPumpStationSummary(httpServletResponse, projectService.getPumpStations(userId));
                        break;
                    case "水闸":
                        excelService.exportSluiceSummary(httpServletResponse, projectService.getSluices(userId));
                        break;
                    case "河道":
                        excelService.exportWatercourseSummary(httpServletResponse, projectService.getWatercourses(userId));
                        break;
                    case "水厂":
                        excelService.exportWaterWorksSummary(httpServletResponse, projectService.getWaterWorks(userId));
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
                                  @RequestParam("projectId") String projectId,
                                  HttpServletResponse httpServletResponse) {
        Response response = new Response();
        try {
            if (!authService.checkLogin(userId, token)) {
                return responseService.notLogin(response);
            } else if (!authService.checkUserProjectTownAccess(userId, projectId)) {
                return responseService.noAccess(response);
            } else {
                ConservationEntity conservationEntity = conservationRepository.findById(projectId);
                String category = conservationEntity.getCategory();
                switch (category) {
                    case "渡槽":
                        excelService.exportAqueduct((AqueductEntity) conservationEntity, httpServletResponse);
                        break;
                    case "桥梁":
                        excelService.exportBridge((BridgeEntity) conservationEntity, httpServletResponse);
                        break;
                    case "渠道":
                        excelService.exportChannel((ChannelEntity) conservationEntity, httpServletResponse);
                        break;
                    case "涵洞":
                        excelService.exportCulvert((CulvertEntity) conservationEntity, httpServletResponse);
                        break;
                    case "塘坝":
                        excelService.exportDam((DamEntity) conservationEntity, httpServletResponse);
                        break;
                    case "深水井":
                        excelService.exportDeepWells((DeepWellsEntity) conservationEntity, httpServletResponse);
                        break;
                    case "管滴灌":
                        excelService.exportDripIrrigationPipe((DripIrrigationPipeEntity) conservationEntity, httpServletResponse);
                        break;
                    case "大口井":
                        excelService.exportGreateWells((GreatWellsEntity) conservationEntity, httpServletResponse);
                        break;
                    case "水电站":
                        excelService.exportHydropower((HydropowerEntity) conservationEntity, httpServletResponse);
                        break;
                    case "水塘":
                        excelService.exportPond((PondEntity) conservationEntity, httpServletResponse);
                        break;
                    case "泵站":
                        excelService.exportPumpStation((PumpStationEntity) conservationEntity, httpServletResponse);
                        break;
                    case "水闸":
                        excelService.exportSluice((SluiceEntity) conservationEntity, httpServletResponse);
                        break;
                    case "河道":
                        excelService.exportWatercourse((WatercourseEntity) conservationEntity, httpServletResponse);
                        break;
                    case "水厂":
                        excelService.exportWaterWorks((WaterWorksEntity) conservationEntity, httpServletResponse);
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
