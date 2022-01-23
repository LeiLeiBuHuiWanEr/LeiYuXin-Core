package com.iloveleiyuxin.websitmanager.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 全局统一返回格式
 * @author LeiYuXin's Boyfriend
 */
@Data
public class Response implements Serializable {
    private int code; // 200是正常，非200表示异常
    private String msg;
    private Object data;
    private final String COPYRIGHT = "LeiYuXin-Core Version "+Const.LEIYUXIN_CORE_VERSION + " ©Copyright 2017-2022 iloveleiyuxin.com All Rights Reserved";

    public static Response succ(Object data) {
        return succ(CodeEnum.SUCCESS, "操作成功", data);
    }
    public static Response succ(int code, String msg, Object data) {
        Response r = new Response();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }
    public static Response fail(String msg) {
        return fail(CodeEnum.LEIYUXIN_FALLEN_IN_LOVE, msg, null);
    }
    public static Response fail(int code,String msg) {
        return fail(code, msg, null);
    }
    public static Response fail(String msg, Object data) {
        return fail(CodeEnum.LEIYUXIN_FALLEN_IN_LOVE, msg, data);
    }
    public static Response fail(int code, String msg, Object data) {
        Response r = new Response();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }
}
