package com.saltyfish.framework.controller;

import com.saltyfish.common.bean.Response;
import com.saltyfish.framework.service.AuthService;
import com.saltyfish.framework.service.ResponseService;
import com.saltyfish.framework.service.UnitService;
import com.saltyfish.framework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by weck on 16/9/4.
 * <p>
 * 用于处理行政单位资源的相关请求
 */
@RequestMapping("/unit")
@RestController
public class UnitController {

    @Autowired
    private UnitService unitService;

    @Autowired
    private UserService userService;

    @Autowired
    private ResponseService responseService;

    @Autowired
    private AuthService authService;

    /**
     * 根据乡镇获取村list
     *
     * @param userId 用户id
     * @param token  登录token
     * @param townId 乡镇id
     * @return 村庄list
     */
    @RequestMapping("getVillagesByTown")
    public Response getVillagesByTown(@RequestParam("userId") Integer userId,
                                      @RequestParam("token") String token,
                                      @RequestParam("townId") Integer townId) {
        Response response = new Response();
        try {
            if (!authService.checkLogin(userId, token)) {
                return responseService.notLogin(response);
            } else if (!authService.checkUserTownAccess(userId, townId)) {
                return responseService.noAccess(response);
            } else {
                response.setCode(HttpStatus.OK.value());
                Map<String, Object> data = new HashMap<>();
                data.put("villages", unitService.getVillagesByTown(townId));
                response.setData(data);
                return response;
            }
        } catch (Exception e) {
            return responseService.serverError(response);
        }
    }

    /**
     * 根据村庄获取组lis
     *
     * @param userId    用户id
     * @param token     登录token
     * @param villageId 村庄id
     * @return 组list
     */
    @RequestMapping("getGroupsByVillage")
    public Response getGroupsByVillage(@RequestParam("userId") Integer userId,
                                       @RequestParam("token") String token,
                                       @RequestParam("villageId") Integer villageId) {
        Response response = new Response();
        try {
            if (!authService.checkLogin(userId, token)) {
                return responseService.notLogin(response);
            } else if (!authService.checkUserTownAccess(userId, unitService.getVillageById(villageId).getTown().getId())) {
                return responseService.noAccess(response);
            } else {
                response.setCode(HttpStatus.OK.value());
                Map<String, Object> data = new HashMap<>();
                data.put("groups", unitService.getGroupsByVillage(villageId));
                response.setData(data);
                return response;
            }
        } catch (Exception e) {
            return responseService.serverError(response);
        }
    }

    /**
     * 获取县列表
     *
     * @param userId 用户id
     * @param token  登录token
     * @return 县list
     */
    @RequestMapping("getCounties")
    public Response getCounties(@RequestParam("userId") Integer userId,
                                @RequestParam("token") String token) {
        Response response = new Response();
        try {
            if (!authService.checkLogin(userId, token)) {
                return responseService.notLogin(response);
            } else if (!authService.checkSuperAdmin(userId)) {
                return responseService.noAccess(response);
            } else {
                response.setCode(HttpStatus.OK.value());
                Map<String, Object> data = new HashMap<>();
                data.put("counties", unitService.getCounties());
                response.setData(data);
                return response;
            }
        } catch (Exception e) {
            return responseService.serverError(response);
        }
    }

    /**
     * 添加县
     *
     * @param userId     用户id
     * @param token      登录token
     * @param countyName 县名
     * @param longitude  东经
     * @param latitude   北纬
     * @param timeStamp  时间戳
     * @return 操作结果
     */
    @RequestMapping("/addCounty")
    public Response addCounty(@RequestParam("userId") Integer userId,
                              @RequestParam("token") String token,
                              @RequestParam("countyName") String countyName,
                              @RequestParam(value = "longitude", required = false, defaultValue = "") String longitude,
                              @RequestParam(value = "latitude", required = false, defaultValue = "") String latitude,
                              @RequestParam("timeStamp") Long timeStamp) {
        Response response = new Response();
        try {
            if (!authService.checkLogin(userId, token)) {
                return responseService.notLogin(response);
            } else if (!authService.checkSuperAdmin(userId)) {
                return responseService.noAccess(response);
            } else {
                unitService.addCounty(countyName, longitude, latitude, timeStamp);
                return responseService.success(response);
            }
        } catch (Exception e) {
            return responseService.serverError(response);
        }
    }

    /**
     * 添加村庄
     *
     * @param userId      用户id
     * @param token       登录token
     * @param villageName 村庄名称
     * @param timeStamp   时间戳
     * @param townId      乡镇id
     * @return 操作结果
     */
    @RequestMapping("/addVillage")
    public Response addVillage(@RequestParam("userId") Integer userId,
                               @RequestParam("token") String token,
                               @RequestParam("villageName") String villageName,
                               @RequestParam("timeStamp") Long timeStamp,
                               @RequestParam("townId") Integer townId) {
        Response response = new Response();
        try {
            if (!authService.checkLogin(userId, token)) {
                return responseService.notLogin(response);
            } else if (!authService.checkAdmin(userId) || !authService.checkUserTownAccess(userId, townId)) {
                return responseService.noAccess(response);
            } else {
                unitService.addVillage(userId, townId, villageName, timeStamp);
                return responseService.success(response);
            }
        } catch (Exception e) {
            return responseService.serverError(response);
        }
    }


    /**
     * 修改村庄信息
     *
     * @param userId      用户id
     * @param token       登录token
     * @param villageName 村庄名称
     * @param villageId   村庄id
     * @param timeStamp   时间戳
     * @return 操作结果
     */
    @RequestMapping("/modifyVillage")
    public Response modifyVillage(@RequestParam("userId") Integer userId,
                                  @RequestParam("token") String token,
                                  @RequestParam("villageName") String villageName,
                                  @RequestParam("villageId") Integer villageId,
                                  @RequestParam("timeStamp") Long timeStamp) {
        Response response = new Response();
        try {
            if (!authService.checkLogin(userId, token)) {
                return responseService.notLogin(response);
            } else if (!authService.checkAdmin(userId) || !authService.checkUserTownAccess(userId,
                    unitService.getVillageById(villageId).getTown().getId())) {
                return responseService.noAccess(response);
            } else {
                unitService.modifyVillage(villageName, timeStamp, villageId);
                return responseService.success(response);
            }
        } catch (Exception e) {
            return responseService.serverError(response);
        }
    }

    /**
     * 添加组
     *
     * @param userId    用户id
     * @param token     登录token
     * @param groupName 组名
     * @param timeStamp 时间戳
     * @return 操作结果
     */
    @RequestMapping("/addGroup")
    public Response addGroup(@RequestParam("userId") Integer userId,
                             @RequestParam("token") String token,
                             @RequestParam("groupName") String groupName,
                             @RequestParam("timeStamp") Long timeStamp,
                             @RequestParam("villageId") Integer villageId) {
        Response response = new Response();
        try {
            if (!authService.checkLogin(userId, token)) {
                return responseService.notLogin(response);
            } else if (!authService.checkAdmin(userId) ||
                    !authService.checkUserTownAccess(userId, unitService.getVillageById(villageId).getTown().getId())) {
                return responseService.noAccess(response);
            } else {
                unitService.addGroup(userId, groupName, timeStamp, villageId);
                return responseService.success(response);
            }
        } catch (Exception e) {
            return responseService.serverError(response);
        }
    }

    /**
     * 修改组
     *
     * @param userId    用户id
     * @param token     登录token
     * @param groupName 组名
     * @param timeStamp 时间戳
     * @param groupId   组id
     * @return 操作结果
     */
    @RequestMapping("modifyGroup")
    public Response modifyGroup(@RequestParam("userId") Integer userId,
                                @RequestParam("token") String token,
                                @RequestParam("groupName") String groupName,
                                @RequestParam("timeStamp") Long timeStamp,
                                @RequestParam("groupId") Integer groupId) {
        Response response = new Response();
        try {
            if (!authService.checkLogin(userId, token)) {
                return responseService.notLogin(response);
            } else if (!authService.checkAdmin(userId) ||
                    !authService.checkUserTownAccess(userId, unitService.getByGroupId(groupId).getTown().getId())) {
                return responseService.noAccess(response);
            } else {
                unitService.modifyGroup(groupName, groupId, timeStamp);
                return responseService.success(response);
            }
        } catch (Exception e) {
            return responseService.serverError(response);
        }
    }

    /**
     * 添加乡镇
     *
     * @param userId    用户id
     * @param token     登录token
     * @param townName  乡镇名称
     * @param timeStamp 时间戳
     * @return 操作结果
     */
    @RequestMapping("/addTown")
    public Response addTown(@RequestParam("userId") Integer userId,
                            @RequestParam("token") String token,
                            @RequestParam("townName") String townName,
                            @RequestParam("timeStamp") Long timeStamp) {
        Response response = new Response();
        try {
            if (!authService.checkLogin(userId, token)) {
                return responseService.notLogin(response);
            } else if (!authService.checkAdmin(userId)) {
                return responseService.noAccess(response);
            } else {
                unitService.addTown(userId, townName, timeStamp);
                return responseService.success(response);
            }
        } catch (Exception e) {
            return responseService.serverError(response);
        }
    }

    /**
     * 修改乡镇
     *
     * @param userId    用户id
     * @param token     登录token
     * @param townName  乡镇名称
     * @param timeStamp 时间戳
     * @param townId    当前修改乡镇的id
     * @return 操作结果
     */
    @RequestMapping("/modifyTown")
    public Response modifyTown(@RequestParam("userId") Integer userId,
                               @RequestParam("token") String token,
                               @RequestParam("townName") String townName,
                               @RequestParam("timeStamp") Long timeStamp,
                               @RequestParam("townId") Integer townId) {
        Response response = new Response();
        try {
            if (!authService.checkLogin(userId, token)) {
                return responseService.notLogin(response);
            } else if (!authService.checkAdmin(userId)) {
                return responseService.noAccess(response);
            } else {
                unitService.modifyTown(townName, townId, timeStamp);
                return responseService.success(response);
            }
        } catch (Exception e) {
            return responseService.serverError(response);
        }
    }

    /**
     * 更新县
     *
     * @param userId     用户id
     * @param token      登录token
     * @param countyName 县名称
     * @param longitude  东经
     * @param latitude   北纬
     * @param timeStamp  时间戳
     * @param countyId   当前修改县的id
     * @return 操作结果
     */
    @RequestMapping("/updateCounty")
    public Response updateCounty(@RequestParam("userId") Integer userId,
                                 @RequestParam("token") String token,
                                 @RequestParam("countyName") String countyName,
                                 @RequestParam(value = "longitude", required = false, defaultValue = "") String longitude,
                                 @RequestParam(value = "latitude", required = false, defaultValue = "") String latitude,
                                 @RequestParam("timeStamp") Long timeStamp,
                                 @RequestParam("countyId") Integer countyId) {
        Response response = new Response();
        try {
            if (!authService.checkLogin(userId, token)) {
                return responseService.notLogin(response);
            } else if (!authService.checkSuperAdmin(userId)) {
                return responseService.noAccess(response);
            } else {
                unitService.updateCounty(countyId, countyName, longitude, latitude, timeStamp);
                return responseService.success(response);
            }
        } catch (Exception e) {
            return responseService.serverError(response);
        }
    }

    /**
     * 获取权限范围内的乡镇
     *
     * @param userId 用户id
     * @param token  登录token
     * @return 乡镇list
     */
    @RequestMapping("/getAccessedTowns")
    public Response getAccessedTowns(@RequestParam("userId") Integer userId,
                                     @RequestParam("token") String token) {
        Response response = new Response();
        try {
            if (!authService.checkLogin(userId, token)) {
                return responseService.notLogin(response);
            } else {
                Map<String, Object> data = new HashMap<>();
                data.put("towns", unitService.getAccessedTowns(userId));
                response.setData(data);
                response.setCode(HttpStatus.OK.value());
                return response;
            }
        } catch (Exception e) {
            return responseService.serverError(response);
        }
    }
}
