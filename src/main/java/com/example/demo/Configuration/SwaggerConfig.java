package com.example.demo.Configuration;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Value("${open.api.title}")
    private String title;
    @Value("${open.api.version}")
    private String version;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(title)
                        .version(version)
                        .description("Bài tập CRUD Student")
                        .license(new License().name("Apache 2.0").url("http://www.apache.org/licenses/LICENSE-2.0.html"))
                )
                .servers(List.of(new Server().url("localhost:8080/").description("Đây là test Server được tạo bởi Lâm Đức Phát")));
    }

    @Bean
    public GroupedOpenApi publicApi1() {
        return GroupedOpenApi.builder()
                .group("admin-api")
                .pathsToMatch("/api/v1/admin/**")
                .build();
    }

    @Bean
    public GroupedOpenApi publicApi2() {
        return GroupedOpenApi.builder()
                .group("layout-api")
                .pathsToMatch("/api/v1/layout/**")
                .build();
    }

    @Bean
    public GroupedOpenApi publicApi3() {
        return GroupedOpenApi.builder()
                .group("student-api")
                .pathsToMatch("/api/v1/admin/student/**")
                .build();
    }
}