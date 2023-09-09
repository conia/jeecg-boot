package org.jeecg.modules.demo.om.config;

import io.swagger.models.Model;
import org.jeecg.modules.demo.om.util.ModelUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelConfig {

    @Value("${om.model.task.concurrent}")
    private int trainConcurrent;

    @Value("${om.model.task.agent.url}")
    private String taskUrl;

    @Value("${om.model.task.agent.dir}")
    private String agentDir;


    @Bean
    public void initModelUtil(){
        ModelUtil.setTrainConcurrent(trainConcurrent);
        ModelUtil.setTaskUrl(taskUrl);
        ModelUtil.setAgentDir(agentDir);
    }




}
