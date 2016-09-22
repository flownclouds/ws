package com.saltyfish.framework.controller;

import com.saltyfish.common.bean.Response;
import com.saltyfish.framework.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by weck on 16/9/4.
 * <p>
 * 错误url处理
 */
@RestController
public class ErrorHandleController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @Autowired
    private ResponseService responseService;

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    @RequestMapping(value = ERROR_PATH)
    public Response errorHandle() {
        return responseService.errorPath(new Response());
    }
}
