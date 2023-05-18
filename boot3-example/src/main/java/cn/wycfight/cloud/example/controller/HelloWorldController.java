package cn.wycfight.cloud.example.controller;

import cn.wycfight.cloud.springboot.starter.log.core.annotation.MLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/hello")
    @MLog
    public String hello(String hello) {
        return "say :" + hello;
    }

}
