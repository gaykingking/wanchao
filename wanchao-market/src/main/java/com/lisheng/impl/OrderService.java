package com.lisheng.impl;

import com.lisheng.feign.MemberServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class OrderService {
    @Autowired
    private MemberServiceFeign memberServiceFeign;
    @RequestMapping("/feign")
    public String orderFeignToMember(){
        return memberServiceFeign.getUser();
    }
}
