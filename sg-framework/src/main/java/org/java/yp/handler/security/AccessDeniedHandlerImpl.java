package org.java.yp.handler.security;

import com.alibaba.fastjson.JSON;
import org.java.yp.domain.ResponseResult;
import org.java.yp.enmu.AppHttpCodeEnum;
import org.java.yp.utils.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 杨鹏
 * @version 1.0
 * Create by 2022/8/28 22:02
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception) throws IOException, ServletException {
        exception.printStackTrace();
        ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NO_OPERATOR_AUTH);
        //响应给前端
        WebUtils.renderString(response, JSON.toJSONString(result));
    }
}
