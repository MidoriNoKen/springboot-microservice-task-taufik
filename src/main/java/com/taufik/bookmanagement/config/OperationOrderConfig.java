package com.taufik.bookmanagement.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OperationOrderConfig {

    @Bean
    public GroupedOpenApi customGroupedOpenApi() {
        return GroupedOpenApi.builder()
                .group("books")
                .addOperationCustomizer((operation, handlerMethod) -> {
                    // This customizer will be used to identify operations
                    // The actual ordering will be done via application properties
                    return operation;
                })
                .build();
    }
}
