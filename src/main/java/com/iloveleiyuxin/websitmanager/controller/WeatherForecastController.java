package com.iloveleiyuxin.websitmanager.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iloveleiyuxin.websitmanager.common.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/tools")
public class WeatherForecastController extends BaseController{
    @GetMapping("weather")
    public Response Weather(){
        String weatherApiUrl = "https://www.yiketianqi.com/free/day?appid=35413257&appsecret=PaGV6Xzu&unescape=1&city=";
        String city = req.getParameter("city");
        if (city == null || city.equals("")){
            city = "唐山";
        }
        String s = null;
        try {
            s = http.doGet(weatherApiUrl+city);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String,String> result = null;
        try {
            result = objectMapper.readValue(s, HashMap.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Response.succ(result);
    }

    @GetMapping("dangerousArea")
    public Response dangerous(){
        String url = "https://diqu.gezhong.vip/api.php";
        String s = "";
        try {
            s = http.doGet(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String,Object> result = null;
        try {
            result = objectMapper.readValue(s, HashMap.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        assert result != null;
        return Response.succ(result.get("data"));
    }
}
