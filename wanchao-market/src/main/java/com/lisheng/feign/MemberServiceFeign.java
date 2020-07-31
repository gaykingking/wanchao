package com.lisheng.feign;

import com.lisheng.hystrix.MemberServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
@Component
@FeignClient(name = "ls-member",fallback = MemberServiceHystrix.class)
public interface MemberServiceFeign {
    @GetMapping("/getUser")
    String getUser();
}
