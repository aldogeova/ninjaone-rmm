package com.ninjaone.inventory.domain.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "inventory-service")
public class DeviceConfigData {
    private String associateServiceRequestTopicName;
    private String associateServiceResponseTopicName;
}
