package com.mario.rule;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zxz
 * @date 2023年08月29日 10:22
 */
@Component
public class CustomRules {

    //硬编码创建规则
    @PostConstruct
    public void initFlowRule() {
        init(Arrays.asList("sayHello", "sayHelloEx","getNaCosServiceName"));
        System.out.println("限流熔断规则初始化完成......");
    }

    public void init(List<String> resources) {
        /* 1.创建存放限流规则的集合 */
        List<FlowRule> rules = new ArrayList<>();
        resources.forEach(e -> {
            /* 2.创建限流规则 */
            FlowRule rule = new FlowRule();
            /* 定义资源，表示 Sentinel 会对哪个资源生效 */
            rule.setResource(e);
            /* 定义限流的类型(此处使用 QPS 作为限流类型) */
            rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
            /* 定义 QPS 每秒通过的请求数 */
            rule.setCount(2);
            /* 3.将限流规则存放到集合中 */
            rules.add(rule);
        });
        /* 4.加载限流规则 */
        FlowRuleManager.loadRules(rules);
    }



}
