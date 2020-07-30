package com.lisheng.feign;

import com.lisheng.hystrix.MemberServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@Component
@FeignClient(name = "ls-member",fallback = MemberServiceHystrix.class)
public interface MemberServiceFeign {
    @GetMapping("/getUser")
    String getUser();
}
