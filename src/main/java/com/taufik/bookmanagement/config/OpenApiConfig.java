package com.taufik.bookmanagement.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Value("${server.port:8080}")
    private String serverPort;

    @Bean
    public OpenAPI bookManagementOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Book Management API")
                        .description("""
                                RESTful API for managing books in a library/catalog system.

                                ## Features
                                - Create, read, update (full & partial), and delete books
                                - Bean Validation on request payloads
                                - Consistent error responses for 400 / 404 / 500

                                ## Notes
                                - `isbn` must be unique
                                - `publishedDate` uses ISO-8601 date format (`yyyy-MM-dd`)
                                - PATCH accepts a partial JSON object (only fields to update)
                                """)
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Taufik Ardiansyah Putra")
                                .email("taufik@example.com")
                                .url("https://github.com/MidoriNoKen/springboot-microservice-task-taufik"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:" + serverPort)
                                .description("Local / Docker Compose server")));
    }
}
