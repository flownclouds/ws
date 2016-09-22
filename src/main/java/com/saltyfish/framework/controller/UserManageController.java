package com.saltyfish.framework.controller;

import com.saltyfish.common.bean.Response;
import com.saltyfish.framework.service.AuthService;
import com.saltyfish.framework.service.ResponseService;
import com.saltyfish.framework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by weck on 16/9/18.
 * <p>
 * 用户管理
 */
@RestController
@RequestMapping("/userManage")
public class UserManageController {
    @Autowired
    private ResponseService responseService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    /**
     * 获取用户列表
     *
     * @param userId 用户id
     * @param token  登录token
     * @param page   页码
     * @param size   每页数量
     * @return 用户page
     */
    @RequestMapping("/getUserList")
    public Response getUserList(@RequestParam("userId") Integer userId,
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
                Map<String, Object> data = new HashMap<>();
                data.put("users", userService.getUserList(userId, page, size));
                response.setData(data);
                response.setCode(HttpStatus.OK.value());
                return response;
            }
        } catch (Exception e) {
            return responseService.serverError(response);
        }
    }

    /**
     * 删除用户
     *
     * @param userId       用户id
     * @param token        登录token
     * @param targetUserId 目标用户id
     * @param timeStamp    时间戳
     * @return 操作结果
     */
    @RequestMapping("/deleteUser")
    public Response deleteUser(@RequestParam("userId") Integer userId,
                               @RequestParam("token") String token,
                               @RequestParam("targetUserId") Integer targetUserId,
                               @RequestParam("timeStamp") Long timeStamp) {
        Response response = new Response();
        try {
            if (!authService.checkLogin(userId, token)) {
                return responseService.notLogin(response);
            } else if (!authService.checkAdmin(userId)) {
                return responseService.noAccess(response);
            }
            userService.deleteUser(targetUserId, timeStamp);
            return responseService.success(response);
        } catch (Exception e) {
            return responseService.serverError(response);
        }
    }

    /**
     * 管理员修改用户信息
     *
     * @param userId       用户id
     * @param token        登录token
     * @param roleId       角色id
     * @param timeStamp    时间戳
     * @param targetUserId 目标用户ud
     * @param townIds      权限内的乡镇id
     * @return 操作结果
     */
    @RequestMapping("/modifyUser")
    public Response modifyAccess(@RequestParam("userId") Integer userId,
                                 @RequestParam("token") String token,
                                 @RequestParam("timeStamp") Long timeStamp,
                                 @RequestParam("targetUserId") Integer targetUserId,
                                 @RequestParam("roleId") Integer roleId,
                                 @RequestParam("name") String name,
                                 @RequestParam("password") String password,
                                 @RequestParam(value = "phone", required = false, defaultValue = "") String phone,
                                 @RequestParam(value = "email", required = false, defaultValue = "") String email,
                                 @RequestParam(value = "realName", required = false, defaultValue = "") String realName,
                                 @RequestParam(value = "isActive", required = false, defaultValue = "1") Integer isActive,
                                 @RequestParam("townIds") List<Integer> townIds) {
        Response response = new Response();
        try {
            if (!authService.checkLogin(userId, token)) {
                return responseService.notLogin(response);
            } else if (!authService.checkAdmin(userId)) {
                return responseService.noAccess(response);
            } else {
                userService.modifyUser(targetUserId, password, phone, email, realName, isActive, timeStamp, roleId, townIds);
                return responseService.success(response);
            }
        } catch (Exception e) {
            return responseService.serverError(response);
        }
    }

    /**
     * 超级管理员添加管理员
     *
     * @param userId    用户id
     * @param token     登录token
     * @param name      添加用户名
     * @param password  添加明文密码
     * @param phone     添加手机号
     * @param email     添加邮箱
     * @param realName  添加真实姓名
     * @param isActive  添加是否启用
     * @param timeStamp 时间戳
     * @param countyId  添加所属县id
     * @return 操作结果
     */
    @RequestMapping("/addAdmin")
    public Response addAdmin(@RequestParam("userId") Integer userId,
                             @RequestParam("token") String token,
                             @RequestParam("name") String name,
                             @RequestParam("password") String password,
                             @RequestParam(value = "phone", required = false, defaultValue = "") String phone,
                             @RequestParam(value = "email", required = false, defaultValue = "") String email,
                             @RequestParam(value = "realName", required = false, defaultValue = "") String realName,
                             @RequestParam(value = "isActive", required = false, defaultValue = "1") Integer isActive,
                             @RequestParam("timeStamp") Long timeStamp,
                             @RequestParam("countyId") Integer countyId) {
        Response response = new Response();
        try {
            if (!authService.checkLogin(userId, token)) {
                return responseService.notLogin(response);
            } else if (!authService.checkSuperAdmin(userId)) {
                return responseService.noAccess(response);
            } else {
                userService.addAdmin(name, password, phone, email, realName, isActive, timeStamp, countyId);
                return responseService.success(response);
            }
        } catch (Exception e) {
            return responseService.serverError(response);
        }
    }


    /**
     * 添加新用户
     *
     * @param userId    用户id
     * @param token     登录token
     * @param name      添加用户名
     * @param password  添加密码
     * @param phone     添加手机号
     * @param email     添加邮件
     * @param realName  添加真实姓名
     * @param roleId    添加角色id
     * @param isActive  添加是否启用
     * @param timeStamp 时间戳
     * @return 操作结果
     */
    @RequestMapping("/addUser")
    public Response addUser(@RequestParam("userId") Integer userId,
                            @RequestParam("token") String token,
                            @RequestParam("name") String name,
                            @RequestParam("password") String password,
                            @RequestParam(value = "phone", required = false, defaultValue = "") String phone,
                            @RequestParam(value = "email", required = false, defaultValue = "") String email,
                            @RequestParam(value = "realName", required = false, defaultValue = "") String realName,
                            @RequestParam(value = "roleId", required = false, defaultValue = "1") Integer roleId,
                            @RequestParam(value = "isActive", required = false, defaultValue = "1") Integer isActive,
                            @RequestParam("timeStamp") Long timeStamp,
                            @RequestParam("townIds") List<Integer> townIds) {
        Response response = new Response();
        try {
            if (!authService.checkLogin(userId, token)) {
                return responseService.notLogin(response);
            } else if (!authService.checkAdmin(userId)) {
                return responseService.noAccess(response);
            } else {
                userService.addUser(userId, name, password, phone, email, realName, roleId, isActive, timeStamp, townIds);
                return responseService.success(response);
            }
        } catch (Exception e) {
            return responseService.serverError(response);
        }
    }

}
