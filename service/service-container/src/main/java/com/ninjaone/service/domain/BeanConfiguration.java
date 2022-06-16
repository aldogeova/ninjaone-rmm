package com.ninjaone.service.domain;

import com.ninjaone.service.domain.entity.AssociateDeviceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public AssociateDeviceServiceService associateDeviceServiceService() {
        return new AssociateDeviceServiceServiceImpl();
    }

}
