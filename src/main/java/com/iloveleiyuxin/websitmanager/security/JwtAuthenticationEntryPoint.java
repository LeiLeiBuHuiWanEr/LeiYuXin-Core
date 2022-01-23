package com.iloveleiyuxin.websitmanager.security;

import cn.hutool.json.JSONUtil;
import com.iloveleiyuxin.websitmanager.common.CodeEnum;
import com.iloveleiyuxin.websitmanager.common.Const;
import com.iloveleiyuxin.websitmanager.common.Response;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ServletOutputStream outputStream = response.getOutputStream();

        Response resp = Response.fail(CodeEnum.DO_NOT_LOGIN,"请先登录");

        outputStream.write(JSONUtil.toJsonStr(resp).getBytes("UTF-8"));

        outputStream.flush();
        outputStream.close();
    }
}
