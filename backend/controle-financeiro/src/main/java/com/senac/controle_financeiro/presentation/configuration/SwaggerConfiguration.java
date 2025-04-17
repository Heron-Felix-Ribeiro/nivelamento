package com.senac.controle_financeiro.presentation.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    // Url para acessar a interface da documentação do swagger -> http://localhost:8080/swagger-ui/index.html#/

    @Bean
    public OpenAPI customOpenApi () {

        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components().addSecuritySchemes("bearerAuth",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                .info(new Info()
                        .title("Controle_Financeiro")
                        .version("1.0")
                        .description("Documentação da API de controle financeiro da aula de fullstack")
                );

    }

}
