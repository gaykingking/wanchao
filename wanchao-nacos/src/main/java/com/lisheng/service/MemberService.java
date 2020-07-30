package com.lisheng.service;

import org.springframework.web.bind.annotation.*;

@RestController
public class MemberService {
    @GetMapping("/getUser")
    public String getUser(){
        return "你好，openFeign!";
    };
}
