package com.saltyfish.framework.controller;

import com.saltyfish.common.bean.Response;
import com.saltyfish.domain.entity.auth.UserEntity;
import com.saltyfish.framework.service.AuthService;
import com.saltyfish.framework.service.ResponseService;
import com.saltyfish.framework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by weck on 16/9/2.
 * <p>
 * 用户操作请求
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ResponseService responseService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;


    /**
     * 登出
     *
     * @param userId    用户id
     * @param token     登录token
     * @param timeStamp 时间戳
     * @return 操作结果
     */
    @RequestMapping("/logout")
    public Response logout(@RequestParam("userId") Integer userId,
                           @RequestParam("token") String token,
                           @RequestParam("timeStamp") Long timeStamp) {
        Response response = new Response();
        try {
            if (!authService.checkLogin(userId, token)) {
                return responseService.notLogin(response);
            }
            userService.logout(userId, timeStamp);
            return responseService.success(response);
        } catch (Exception e) {
            return responseService.serverError(response);
        }

    }


    /**
     * 修改个人信息
     *
     * @param userId    用户id
     * @param token     登录token
     * @param name      用户名
     * @param realName  真实姓名
     * @param email     邮箱
     * @param phone     手机号
     * @param timeStamp 时间戳
     * @return 操作结果
     */
    @RequestMapping("/modifyUserInfo")
    public Response modifyUserInfo(@RequestParam("userId") Integer userId,
                                   @RequestParam("token") String token,
                                   @RequestParam("name") String name,
                                   @RequestParam(value = "realName", required = false, defaultValue = "") String realName,
                                   @RequestParam(value = "email", required = false, defaultValue = "") String email,
                                   @RequestParam(value = "phone", required = false, defaultValue = "") String phone,
                                   @RequestParam("timeStamp") Long timeStamp) {
        Response response = new Response();
        try {
            if (!authService.checkLogin(userId, token)) {
                return responseService.notLogin(response);
            } else {
                userService.modifyUserInfo(userId, name, realName, email, phone, timeStamp);
                return responseService.success(response);
            }
        } catch (Exception e) {
            return responseService.serverError(response);
        }
    }

    /**
     * 检测密码是否正确
     *
     * @param userId   用户id
     * @param token    登录token
     * @param password 密码
     * @return 操作结果
     */
    @RequestMapping("/checkPassword")
    public Response checkPassword(@RequestParam("userId") Integer userId,
                                  @RequestParam("token") String token,
                                  @RequestParam("password") String password) {
        Response response = new Response();
        try {
            if (!authService.checkLogin(userId, token)) {
                return responseService.notLogin(response);
            } else if (!authService.verifyPassword(userId, password)) {
                return responseService.inCorrectPassword(response);
            }
            return responseService.success(response);
        } catch (Exception e) {
            return responseService.serverError(response);
        }
    }

    /**
     * 修改密码
     *
     * @param userId    用户id
     * @param token     登录token
     * @param password  密码
     * @param timeStamp 时间戳
     * @return
     */
    @RequestMapping("/modifyPassword")
    public Response modifyPassword(@RequestParam("userId") Integer userId,
                                   @RequestParam("token") String token,
                                   @RequestParam("password") String password,
                                   @RequestParam("timeStamp") Long timeStamp) {
        Response response = new Response();
        try {
            if (!authService.checkLogin(userId, token)) {
                return responseService.notLogin(response);
            }
            userService.modifyPassword(userId, password, timeStamp);
            return responseService.success(response);
        } catch (Exception e) {
            return responseService.serverError(response);
        }
    }


    /**
     * 登录
     *
     * @param userName 用户名
     * @param password 密码
     * @return 操作结果
     */
    @RequestMapping("/login")
    public Response login(@RequestParam("userName") String userName,
                          @RequestParam("password") String password) {
        Response rp = new Response();

        try {
            UserEntity userEntity = userService.findByName(userName);
            if (userEntity != null) {
                if (authService.verifyPassword(userEntity, password)) {
                    userService.setToken(userEntity);
                    rp.setCode(HttpStatus.OK.value());
                    Map<String, Object> msg = new HashMap<>();
                    msg.put("user", userEntity);
                    rp.setData(msg);
                    return rp;
                } else {
                    responseService.inCorrectPassword(rp);
                    return rp;
                }
            } else {
                responseService.notFound(rp);
                return rp;
            }
        } catch (Exception e) {
            responseService.serverError(rp);
            return rp;
        }
    }
}
