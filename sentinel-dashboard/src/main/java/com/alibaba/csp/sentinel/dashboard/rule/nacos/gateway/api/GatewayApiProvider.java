package com.alibaba.csp.sentinel.dashboard.rule.nacos.gateway.api;

import com.alibaba.csp.sentinel.dashboard.config.DashboardConfig;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.gateway.ApiDefinitionEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.DegradeRuleEntity;
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

@Component("gatewayApiProvider")
public class GatewayApiProvider implements DynamicRuleProvider<List<ApiDefinitionEntity>> {

    private static final Logger log = LoggerFactory.getLogger(GatewayApiProvider.class);

    @Autowired
    private ConfigService configService;

    @Autowired
    private Converter<String,List<ApiDefinitionEntity>> converter;

    @Override
    public List<ApiDefinitionEntity> getRules(String appName) throws Exception {
        String rules = configService.getConfig(appName + NacosConfigUtil.GATEWAY_API_DATA_ID_POSTFIX, DashboardConfig.getNacosGroup(), 5000);
        log.info("get rules from nacos :{}",rules);
        if(StringUtils.isEmpty(rules)){
            return new ArrayList<>();
        }
        return converter.convert(rules);
    }

}
