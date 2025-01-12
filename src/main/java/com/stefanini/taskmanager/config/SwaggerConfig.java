package com.stefanini.taskmanager.config;

import com.stefanini.taskmanager.services.impl.ApiDataServiceImpl;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        Map<String, String> apiInformations = new ApiDataServiceImpl().getApiInformation();
        return new OpenAPI()
                .info(new Info()
                        .title(apiInformations.get("name"))
                        .version(apiInformations.get("version"))
                        .description("Documentação da API de gerenciamento de tarefas usando SpringDoc OpenAPI"));
    }
}