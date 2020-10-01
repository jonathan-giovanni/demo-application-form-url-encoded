package com.hv.demo.application_form_url_encoded.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;


/**
 * @author Jonathan Giovanni Hernandez
 * @company TEST
 * @created 11/08/2020
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "DEMO-MICROSERVICE-FORM-URL-ENCODED",
                "Servicio de prueba del Content-Type: application/x-www-form-urlencoded",
                "v1.0",
                "Terms of service",new Contact("Jonathan Hernandez","https://www.linkedin.com/in/jonathan-giovanni-hern%C3%A1ndez-v%C3%A1squez/","hv12i04001@gmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }

}
