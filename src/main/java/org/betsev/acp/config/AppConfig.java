package org.betsev.acp.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.Arrays;
import java.util.List;

/**
 * Created by sevburmaka on 12/7/16.
 */
@Configuration
public class AppConfig implements SchedulingConfigurer {
    @Bean
    public DozerBeanMapper dozerBeanMapper() {
        List<String> mappingFiles = Arrays.asList(
                "config/dozer-bean-mappings.xml"
        );

        DozerBeanMapper mapper=  new DozerBeanMapper(mappingFiles);

        return mapper;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

    }
}
