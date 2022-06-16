package com.ninjaone.service.domain.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "service-service")
public class ServiceConfigData {
    private String associateServiceRequestTopicName;
    private String associateServiceResponseTopicName;
}
