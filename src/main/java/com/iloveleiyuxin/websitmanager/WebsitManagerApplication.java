package com.iloveleiyuxin.websitmanager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot启动器
 * <p>
 * iloveleiyuxin.com网站中台，由于使用静态页面，网站静态资源非常混乱，
 * 开发此项目以维护网站资源以及简化对网站的管理
 * </p>
 * @author spring.io
 *
 * @since 2022.1.1
 */
@SpringBootApplication
@Slf4j
public class WebsitManagerApplication {

    public static void main(String[] args) {
        Long time = System.currentTimeMillis();
        SpringApplication.run(WebsitManagerApplication.class, args);
        log.info("===LeiYuXin-Core启动耗时："+(System.currentTimeMillis()-time)+"毫秒===");
    }

}

/*
                      必得敬业福

             /@@@\
             ,@@@@@\       @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
               ,@@@@@\     @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                 \@@@@/.
                  ,@`
                              @@@@@@@@@@@@@@@@@@@@@@@@@@
      =@@@@@@@@@@@@@@@@@@^    @@@@@@@@@@@@@@@@@@@@@@@@@@
      =@@@OOOOOOO/\O@@@@/     @@@@@               .@@@@@
                   /@@@/      @@@@@               .@@@@@
                  /@@@@.      @@@@@.            ...@@@@@
                 /@@@@        @@@@@@@@@@@@@@@@@@@@@@@@@@
                @@@@@         @@@@@@@@@@@@@@@@@@@@@@@@@@
              =@@@@/
            ,@@@@@@^,@\    ,@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@`
           /@@@@@@@@@@@@   =@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@`
         /@@@@@@@@@^\@@@@^.=@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@.
       /@@@@@`.@@@@^.,@@@@@=@@@@        .@@@@O        =@@@@.
     ,@@@@@/   @@@@^...\@`.=@@@@        .@@@@O        =@@@@.
       \@/     @@@@^       =@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@.
               @@@@^       =@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@.
               @@@@^       =@@@@         @@@@O       .=@@@@.
               @@@@^       =@@@@....     @@@@O.      .=@@@@.
               @@@@^       =@@@@....     @@@@O.      .=@@@@.
            . .@@@@^       =@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@.
            ...@@@@^       =@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@.
            ..=@@@@^       =@@@@                       @@@@^
            ..=@@@@^       ,@@@@....                ..,@@@@`





 */
