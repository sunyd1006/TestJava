package com.syd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
public class Hello {

    // 缺少ResponseBody是显示的404
    @ResponseBody
    @RequestMapping("/hello")
    public String hello(@RequestParam("data") String data){
        System.out.println(data);
        return data;
    }
}
