package com.iloveleiyuxin.websitmanager.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iloveleiyuxin.websitmanager.common.exception.LeiYuXinFallenInLoveException;

public class JSON {
    static ObjectMapper objectMapper = new ObjectMapper();

    public static String Obj2Json(Object obj){
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new LeiYuXinFallenInLoveException("解析JSON错误"+e.getMessage());
        }
    }

    public static Object Json2Obj(String json,Class clazz){
        try {
            return objectMapper.readValue(json,clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new LeiYuXinFallenInLoveException("解析JSON错误"+e.getMessage());
        }
    }
}
