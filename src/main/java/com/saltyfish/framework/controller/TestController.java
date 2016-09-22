package com.saltyfish.framework.controller;

import com.saltyfish.common.bean.Response;
import com.saltyfish.common.utils.UUIDGenerator;
import com.saltyfish.domain.entity.auth.UserEntity;
import com.saltyfish.domain.repository.auth.UserRepository;
import com.saltyfish.domain.repository.unit.TownRepository;
import com.saltyfish.framework.service.AuthService;
import com.saltyfish.framework.service.FileService;
import com.saltyfish.framework.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by weck on 16/9/2.
 * <p>
 * 测试controller
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TownRepository townRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private ResponseService responseService;

    @Autowired
    private FileService fileService;

    @RequestMapping("/test")
    public UserEntity test() {
        UserEntity userEntity = userRepository.findById(1);
        userEntity.setToken(UUIDGenerator.getUUID());
        userRepository.save(userEntity);
        return userEntity;
    }

    @RequestMapping("/uploadFile")
    public Response uploadFile(@RequestParam("userId") Integer userId,
                               @RequestParam("token") String token,
                               @RequestParam(value = "file", required = false) MultipartFile file,
                               @RequestParam("timeStamp") Long timeStamp) {
        Response response = new Response();
        try {
            if (!authService.checkLogin(userId, token)) {
                return responseService.notLogin(response);
            } else {
                fileService.saveFile(file, timeStamp);
                return responseService.success(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return responseService.serverError(response);
        }
    }
}
