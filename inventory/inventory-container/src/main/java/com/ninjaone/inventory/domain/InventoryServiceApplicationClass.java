package com.ninjaone.inventory.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableJpaRepositories(basePackages = "com.ninjaone.inventory.dataaccess")
@EnableJpaAuditing
@EntityScan(basePackages = "com.ninjaone.inventory.dataaccess")
@EnableConfigurationProperties(value = {
        com.ninjaone.inventory.domain.config.DeviceConfigData.class})
@SpringBootApplication(scanBasePackages = "com.ninjaone", exclude = { SecurityAutoConfiguration.class })
public class InventoryServiceApplicationClass {
    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplicationClass.class, args);
    }
}
