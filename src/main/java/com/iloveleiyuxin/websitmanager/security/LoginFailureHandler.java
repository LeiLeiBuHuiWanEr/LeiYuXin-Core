package com.iloveleiyuxin.websitmanager.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iloveleiyuxin.websitmanager.common.CodeEnum;
import com.iloveleiyuxin.websitmanager.common.Response;
import com.iloveleiyuxin.websitmanager.common.exception.KaptchaException;
import com.iloveleiyuxin.websitmanager.common.exception.LeiYuXinFallenInLoveException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
        Response result = null;
        if(exception instanceof KaptchaException){
            result = Response.fail(CodeEnum.WRONG_KAPTCHA,"验证码错误");
        }
        else{
            result = Response.fail(CodeEnum.LOGIN_FAIL,"用户名或密码错误");
        }


        outputStream.write(objectMapper.writeValueAsString(result).getBytes("UTF-8"));

        outputStream.flush();
        outputStream.close();
    }
}
