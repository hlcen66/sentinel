package com.alibaba.csp.sentinel.dashboard.rule.nacos.auth;

import com.alibaba.csp.sentinel.dashboard.config.DashboardConfig;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.AbstractRuleEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.AuthorityRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRulePublisher;
import com.alibaba.csp.sentinel.dashboard.rule.nacos.NacosConfigUtil;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.util.AssertUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.common.utils.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("authPublisher")
public class AuthPublisher implements DynamicRulePublisher<List<AuthorityRuleEntity>> {

    private static final Logger log = LoggerFactory.getLogger(AuthProvider.class);

    @Autowired
    private ConfigService configService;


    @Override
    public void publish(String app, List<AuthorityRuleEntity> rules) throws Exception {
        AssertUtil.notEmpty(app, "app name is not allow empty");
        if(null == rules){
            return;
        }
        //String rulesStr = converter.convert(rules);
        String rulesStr = JSON.toJSONString(rules.stream().map(AbstractRuleEntity::toRule).collect(Collectors.toList()));
        log.info("push auth rules to nacos,rules:{}",rulesStr);
        configService.publishConfig(app+NacosConfigUtil.AUTH_DATA_ID_POSTFIX, DashboardConfig.getNacosGroup(), rulesStr, ConfigType.JSON.getType());
    }
}
