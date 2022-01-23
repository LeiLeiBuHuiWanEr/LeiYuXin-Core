package com.iloveleiyuxin.websitmanager.security;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.iloveleiyuxin.websitmanager.common.exception.KaptchaException;
import com.iloveleiyuxin.websitmanager.common.Const;
import com.iloveleiyuxin.websitmanager.common.exception.LeiYuXinFallenInLoveException;
import com.iloveleiyuxin.websitmanager.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class KaptchaFilter extends OncePerRequestFilter{
    private final String loginUrl = "/login";
    @Autowired
    RedisUtils redisUtil;

    @Autowired
    LoginFailureHandler loginFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException, LeiYuXinFallenInLoveException {

        String url = httpServletRequest.getRequestURI();

        if ("/login".equals(url) && httpServletRequest.getMethod().equals("POST")) {

            try{
                // 校验验证码
                validate(httpServletRequest);
            } catch (KaptchaException e) {
                // 交给认证失败处理器
                loginFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    // 校验验证码逻辑
    private void validate(HttpServletRequest httpServletRequest) {

        String code = httpServletRequest.getParameter("kaptcha");
        String key = httpServletRequest.getParameter("token");

        if (StringUtils.isBlank(code) || StringUtils.isBlank(key)) {
            log.warn("你都不给我填验证码，你登录个锤子？？？");
            throw new KaptchaException("没有验证码，你登录个锤子？？");
        }
        if(redisUtil.hget(Const.KAPTCHA_KEY, key) == null){
            log.warn("Redis里面都没有验证码，你的验证码哪儿来的啊？你登录个锤子？？");
            throw new KaptchaException("没有验证码，你登录个锤子？？");
        }
        if (!code.equals(redisUtil.hget(Const.KAPTCHA_KEY, key))) {
            throw new KaptchaException("验证码错误");
        }

        // 一次性使用
        redisUtil.hdel(Const.KAPTCHA_KEY, key);
    }
}
