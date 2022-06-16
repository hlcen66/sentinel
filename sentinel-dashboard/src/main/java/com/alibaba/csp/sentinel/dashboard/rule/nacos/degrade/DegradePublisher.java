package com.alibaba.csp.sentinel.dashboard.rule.nacos.degrade;

import com.alibaba.csp.sentinel.dashboard.config.DashboardConfig;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.AuthorityRuleEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.DegradeRuleEntity;
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

@Component("degradePublisher")
public class DegradePublisher implements DynamicRulePublisher<List<DegradeRuleEntity>> {

    private static final Logger log = LoggerFactory.getLogger(DegradeProvider.class);

    @Autowired
    private ConfigService configService;

    @Autowired
    private Converter<List<DegradeRuleEntity>,String> converter;

    @Override
    public void publish(String app, List<DegradeRuleEntity> rules) throws Exception {
        AssertUtil.notEmpty(app, "app name is not allow empty");
        if(null == rules){
            return;
        }
        String rulesStr = converter.convert(rules);
        log.info("push rules to nacos,authRules:{}",rulesStr);
        configService.publishConfig(app+NacosConfigUtil.DEGRADE_DATA_ID_POSTFIX, DashboardConfig.getNacosGroup(), rulesStr, ConfigType.JSON.getType());
    }
}
