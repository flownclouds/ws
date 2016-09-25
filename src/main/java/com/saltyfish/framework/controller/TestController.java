package com.saltyfish.framework.controller;

import com.saltyfish.common.bean.Response;
import com.saltyfish.domain.repository.ConservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by weck on 16/9/24.
 */

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ConservationRepository conservationRepository;

    @RequestMapping("/querySuper")
    public Response querySuper() {
        Response response = new Response();
        Map<String, Object> data = new HashMap<>();
        data.put("projects", conservationRepository.findAll());
        response.setData(data);
        response.setCode(HttpStatus.OK.value());
        return response;
    }
}
