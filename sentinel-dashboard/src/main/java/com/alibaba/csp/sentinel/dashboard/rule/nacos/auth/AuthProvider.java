package com.alibaba.csp.sentinel.dashboard.rule.nacos.auth;

import com.alibaba.csp.sentinel.dashboard.config.DashboardConfig;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.AuthorityRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRuleProvider;
import com.alibaba.csp.sentinel.dashboard.rule.nacos.NacosConfigUtil;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.ConfigService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component("authProvider")
public class AuthProvider implements DynamicRuleProvider<List<AuthorityRuleEntity>> {

    private static final Logger log = LoggerFactory.getLogger(AuthProvider.class);

    @Autowired
    private ConfigService configService;

    @Override
    public List<AuthorityRuleEntity> getRules(String appName) throws Exception {
        String rules = configService.getConfig(appName + NacosConfigUtil.AUTH_DATA_ID_POSTFIX, DashboardConfig.getNacosGroup(), 5000);
        log.info("get auth rules from nacos :{}",rules);
        if(StringUtils.isEmpty(rules)){
            return new ArrayList<>();
        }
        //return converter.convert(rules);
        List<AuthorityRule> authRules = JSON.parseArray(rules,AuthorityRule.class);
        return authRules.stream().map(item-> AuthorityRuleEntity.fromAuthorityRule(appName, null,null,item)).collect(Collectors.toList());
    }

}
