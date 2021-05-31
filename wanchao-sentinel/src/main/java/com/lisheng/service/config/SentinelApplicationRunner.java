package com.lisheng.service.config;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class SentinelApplicationRunner implements ApplicationRunner {
    //限流规则名称
    public static final String GETORDER_KEY="orderToMember";

    private Logger log= LoggerFactory.getLogger(SentinelApplicationRunner.class);
    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<FlowRule> rules=new ArrayList<FlowRule>();
        FlowRule rule1 = new FlowRule();
        rule1.setResource(GETORDER_KEY);
        //QPS控制在2以内
        rule1.setCount(1);
        //QPS限流
        rule1.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule1.setLimitApp("default");
        rules.add(rule1);
        FlowRuleManager.loadRules(rules);
        log.info(">>>限流配置加载成功<<<");
    }
}
