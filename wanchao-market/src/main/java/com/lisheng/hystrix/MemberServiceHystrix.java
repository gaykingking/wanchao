package com.lisheng.hystrix;

import com.lisheng.feign.MemberServiceFeign;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceHystrix implements MemberServiceFeign {
    @Override
    public String getUser() {
        return "请求超时了";
    }
}
