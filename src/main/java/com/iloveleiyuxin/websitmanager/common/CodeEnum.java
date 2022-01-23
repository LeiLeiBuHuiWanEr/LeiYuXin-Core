package com.iloveleiyuxin.websitmanager.common;

/**
 * 应答代码常量
 * @author LeiYuXin's Boyfriend
 */
public class CodeEnum {
    //正常
    public static final int SUCCESS = 200;
    public static final int EMPTY_LIST_OR_MAP = 2001;
    //无权限
    public static final int NEED_AUTHORIZE = 4011;
    public static final int CLIENT_NEED_AUTHORIZE = 4012;
    //错误
    public static final int BAD_REQUEST = 400;
    public static final int NEED_PARAM = 4001;
    public static final int PARAM_NOT_VALID = 4002;
    public static final int FORBIDDEN = 403;
    public static final int NOT_FOUND = 404;
    public static final int LEIYUXIN_FALLEN_IN_LOVE = 4041;
    public static final int METHOD_NOT_ALLOWED = 405;
    public static final int REQUEST_REJECTED = 4051;
    //异常
    public static final int DO_NOT_LOGIN = 9000;
    public static final int LOGIN_FAIL = 9001;
    public static final int WRONG_KAPTCHA = 9002;

    public static final int ERROR_QUERYING_DATABASE = 9100;



}
