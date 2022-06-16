package com.ninjaone.service.application.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;


@Configuration
public class DocumentationConfiguration {

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Ninja One - RMM - Service Module")
                .description("RMM API referenfe for developers. This Api manage the services of a module. ")
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
                .tags(new Tag("Services", "Operations to create, update, get and delete services."))
                .tags(new Tag("ServiceType", "Operations to create, update and delete type de services (Association between service and type of devices)."))
                .groupName("ninjaone-service-api")
                .select()
                .paths(paths()).build();
    }


    private Predicate<String> paths(){
        return   PathSelectors.regex("/api/service/services.*")
                .or(PathSelectors.regex("/api/service/serviceTypes.*"));
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
