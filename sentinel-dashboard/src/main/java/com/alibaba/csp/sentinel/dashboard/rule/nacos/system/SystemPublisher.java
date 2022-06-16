package com.alibaba.csp.sentinel.dashboard.rule.nacos.system;

import com.alibaba.csp.sentinel.dashboard.config.DashboardConfig;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.SystemRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRulePublisher;
import com.alibaba.csp.sentinel.dashboard.rule.nacos.NacosConfigUtil;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.util.AssertUtil;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.common.utils.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("systemPublisher")
public class SystemPublisher implements DynamicRulePublisher<List<SystemRuleEntity>> {

    private static final Logger log = LoggerFactory.getLogger(SystemProvider.class);

    @Autowired
    private ConfigService configService;

    @Autowired
    private Converter<List<SystemRuleEntity>,String> converter;

    @Override
    public void publish(String app, List<SystemRuleEntity> rules) throws Exception {
        AssertUtil.notEmpty(app, "app name is not allow empty");
        if(null == rules){
            return;
        }
        String rulesStr = converter.convert(rules);
        log.info("push rules to nacos,authRules:{}",rulesStr);
        configService.publishConfig(app+NacosConfigUtil.SYS_DATA_ID_POSTFIX, DashboardConfig.getNacosGroup(), rulesStr, ConfigType.JSON.getType());
    }
}
