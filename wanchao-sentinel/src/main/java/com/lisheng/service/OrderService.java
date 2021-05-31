package com.lisheng.service;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@Slf4j
public class OrderService {

    //限流规则名称
    public static final String GETORDER_KEY="orderToMember";

    private Logger log= LoggerFactory.getLogger(OrderService.class);
    @RequestMapping("/orderToMember")
    public String orderToMember(){
        Entry entry=null;
        try {
            entry= SphU.entry(GETORDER_KEY);
            return "orderToMember接口";
        } catch (Exception e) {
            return "当前访问人数过多，请稍后重试！";
        }finally {
            if(entry!=null){
                entry.exit();
            }
        }
    }
    @SentinelResource(value = GETORDER_KEY,blockHandler = "getOrderQpsException")
    @RequestMapping("/orderToMemberSentinelResource")
    public String orderToMemberSentinelResource(){
        return "orderToMemberSentinelResource";
    }
    //被限流后返回的提示
    public String getOrderQpsException(BlockException e){
        e.printStackTrace();
        return "该接口已经被限流了！";
    }
    @RequestMapping("/getOrderConsole")
    /**
     * 注意：如果没有使用@SentinelResource注解的情况下，默认的资源名称为接口路径地址，需要加/
     */
    public String getOrderConsole(){
        return "getOrderConsole";
    }
    @RequestMapping("/getOrderSemaphore")
    @SentinelResource(value = "getOrderSemaphore",blockHandler = "getOrderQpsException")
    public String getOrderSemaphore(){
        try {
            Thread.sleep(500);
        }catch (Exception e){

        }
        log.info(Thread.currentThread().getName());
        return "getOrderSemaphore";
    }
    @RequestMapping("/getOrderDowngradeRtType")
    @SentinelResource(value = "getOrderDowngradeRtType",fallback = "getOrderDowngradeRtTypeFallback")
    public String getOrderDowngradeRtType(){
        try{
            Thread.sleep(300);
        }catch (Exception e){}
        return "getOrderDowngradeRtType";
    }

    public String getOrderDowngradeRtTypeFallback(){
        return "服务降级了，当前服务器请求次数过多，请稍后重试!";
    }







}
