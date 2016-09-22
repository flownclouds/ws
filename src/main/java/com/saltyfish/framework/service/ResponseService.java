package com.saltyfish.framework.service;

import com.saltyfish.common.bean.Response;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by weck on 16/9/3.
 * <p>
 * 返回service
 */
@Service
public class ResponseService {

    /**
     * 未登录返回
     *
     * @param response 返回对象
     * @return response
     */
    public Response notLogin(Response response) {
        response.setCode(HttpStatus.UNAUTHORIZED.value());
        response.setError("请登录");
        return response;
    }

    /**
     * 服务器错误
     *
     * @param response 返回对象
     * @return response
     */
    public Response serverError(Response response) {
        response.setError("服务器错误");
        response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return response;
    }

    /**
     * 没有权限
     *
     * @param response 返回对象
     * @return response
     */
    public Response noAccess(Response response) {
        response.setCode(HttpStatus.FORBIDDEN.value());
        response.setError("对不起,您没有权限");
        return response;
    }

    /**
     * 用户名不存在
     *
     * @param response 返回对象
     * @return response
     */
    public Response notFound(Response response) {
        response.setCode(HttpStatus.NOT_FOUND.value());
        response.setError("用户名不存在");
        return response;
    }

    /**
     * 密码不正确
     *
     * @param response 返回对象
     * @return response
     */
    public Response inCorrectPassword(Response response) {
        response.setCode(HttpStatus.NOT_ACCEPTABLE.value());
        response.setError("密码不正确");
        return response;
    }

    /**
     * 成功
     *
     * @param response 返回对象
     * @return response
     */
    public Response success(Response response) {
        response.setCode(HttpStatus.OK.value());
        Map<String, Object> msg = new HashMap<>();
        msg.put("details", "成功");
        response.setData(msg);
        return response;
    }


    /**
     * 错误路径
     *
     * @param response 返回对象
     * @return response
     */
    public Response errorPath(Response response) {
        response.setCode(HttpStatus.NOT_FOUND.value());
        response.setError("请求url错误");
        return response;
    }

    public Response saveFileError(Response response) {
        response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setError("保存文件出错");
        return response;
    }
}
