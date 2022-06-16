package com.ninjaone.service.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.ninjaone.service.dataaccess")
@EntityScan(basePackages = "com.ninjaone.service.dataaccess")
@SpringBootApplication(scanBasePackages = "com.ninjaone")
public class ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }

}
