package com.alibaba.csp.sentinel.dashboard.rule.nacos.flow;

import com.alibaba.csp.sentinel.dashboard.config.DashboardConfig;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.DegradeRuleEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRuleProvider;
import com.alibaba.csp.sentinel.dashboard.rule.nacos.NacosConfigUtil;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.nacos.api.config.ConfigService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("flowProvider")
public class FlowProvider implements DynamicRuleProvider<List<FlowRuleEntity>> {

    private static final Logger log = LoggerFactory.getLogger(FlowProvider.class);

    @Autowired
    private ConfigService configService;

    @Autowired
    private Converter<String,List<FlowRuleEntity>> converter;

    @Override
    public List<FlowRuleEntity> getRules(String appName) throws Exception {
        String dataId = appName + NacosConfigUtil.FLOW_DATA_ID_POSTFIX;
        String groupId = DashboardConfig.getNacosGroup();
        String rules = configService.getConfig(dataId,groupId,5000);
        log.info("get rules from nacos dataId:{},groupId:{},rules:{}",dataId,groupId,rules);
        if(StringUtils.isEmpty(rules)){
            return new ArrayList<>();
        }
        return converter.convert(rules);
    }

}
