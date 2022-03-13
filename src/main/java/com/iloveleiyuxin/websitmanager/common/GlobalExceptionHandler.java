package com.iloveleiyuxin.websitmanager.common;

import com.iloveleiyuxin.websitmanager.common.exception.LackParamException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Objects;

/**
 * 全局异常处理类
 * @author ZhangWanTing's Bestie, LeiYuXin's Boyfriend
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = AccessDeniedException.class)
    public Response handler(AccessDeniedException e) {
        log.info("security权限不足：----------------{}", e.getMessage());
        return Response.fail(CodeEnum.FORBIDDEN,"权限不足");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Response handler(MethodArgumentNotValidException e) {
        log.info("实体校验异常：----------------{}", e.getMessage());
        BindingResult bindingResult = e.getBindingResult();
        ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
        return Response.fail(CodeEnum.BAD_REQUEST,objectError.getDefaultMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public Response handler(IllegalArgumentException e) {
        log.error("Assert异常：----------------{}", e.getMessage());
        return Response.fail(CodeEnum.BAD_REQUEST,e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    public Response handler(RuntimeException e) {
        if(e instanceof LackParamException){
            log.error("缺失参数异常！！！");
            return Response.fail(CodeEnum.NEED_PARAM,e.getMessage());
        }
        log.error("运行时异常：----------------{}", e);
        return Response.fail(CodeEnum.BAD_REQUEST,e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public Response handler(NoHandlerFoundException exception){
        log.error("HTTP Status : 404 Not Found when"+exception.getHttpMethod() + exception.getRequestURL());
        return Response.fail(CodeEnum.NOT_FOUND, "HTTP Status : 404 Not Found when"+ " "+exception.getHttpMethod() + " "+ exception.getRequestURL());
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public Response handler(HttpRequestMethodNotSupportedException exception){
        return Response.fail(CodeEnum.METHOD_NOT_ALLOWED, "HTTP Status : 405 Method Not Allowed when"+ " "+exception.getMethod() + " ,you need to "+ Objects.requireNonNull(exception.getSupportedMethods())[0]);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = RequestRejectedException.class)
    public Response handler(RequestRejectedException exception){
        return Response.fail(CodeEnum.REQUEST_REJECTED, "别尼玛瞎玩了，我的系统不是你说玩就玩的，你看看你发的什么㺃JB请求？？？");
    }
}
