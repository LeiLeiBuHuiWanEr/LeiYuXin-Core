package com.iloveleiyuxin.websitmanager.common.exception;

/**
 * 缺少参数异常
 * @author LeiYuXin's Boyfriend
 */
public class LackParamException extends LeiYuXinFallenInLoveException{
    private static final long serialVersionUID = 1L;

    public LackParamException(){
        super();
    }

    public LackParamException(String s){
        super(s);
    }
}
