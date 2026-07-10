package com.helperhub.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI helperHubAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("HelperHub API")
                        .description("HelperHub Service Booking REST API")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Ajay Kumar")
                                .email("ajay@example.com")));
    }
}