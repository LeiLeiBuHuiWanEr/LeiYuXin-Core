package com.iloveleiyuxin.websitmanager.common.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 验证码错误
 */
public class KaptchaException extends AuthenticationException {

    public KaptchaException(String msg) {
        super(msg);
    }
}