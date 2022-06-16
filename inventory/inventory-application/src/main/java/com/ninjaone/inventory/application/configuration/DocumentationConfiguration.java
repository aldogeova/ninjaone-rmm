package com.ninjaone.inventory.application.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.spi.service.contexts.SecurityContext;


@Configuration
public class DocumentationConfiguration {

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Ninja One - RMM - Inventory Module")
                .description("RMM API referenfe for developers. This Api manage the inventory devices. ")
                .termsOfServiceUrl("https://www.ninjaone.com/privacy-policy/")
                .contact(new Contact("Aldo Navarrete", "https://www.linkedin.com/in/aldo-navarrete/", "aldogeova@gmail.com"))
                .license("Ninja One SAAS")
                .licenseUrl("https://www.ninjaone.com/license-agreement/")
                .version("1.0.0")
                .build();
    }


    @Bean
    public Docket rmmApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .tags(new Tag("OperativeSystem", "Operations to create, update, get and delete operative systems."))
                .tags(new Tag("DeviceClassification", "Operations to create, update, get and delete device classification as Laptop, Desktop, etc."))
                .tags(new Tag("Device", "Operations to create, update, get and delete devices."))
                .tags(new Tag("TypeDevice", "Operations to create, update, get and delete type of devices."))
                .tags(new Tag("Charges", "Operations to show the charges by client or by device."))
                .groupName("ninjaone-inventory-api")
                .select()
                .paths(paths()).build();
    }


    private Predicate<String> paths(){
        return   PathSelectors.regex("/api/inventory/deviceClassifications.*")
                .or(PathSelectors.regex("/api/inventory/operativeSystems.*"))
                .or(PathSelectors.regex("/api/inventory/typeDevices.*"))
                .or(PathSelectors.regex("/api/inventory/devices.*"))
                .or(PathSelectors.regex("/api/inventory/charges.*"));
    }


    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }

}
