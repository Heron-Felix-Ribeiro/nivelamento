package com.senac.controle_financeiro.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    // Url para acessar a interface da documentação do swagger -> http://localhost:8080/swagger-ui/index.html#/

    @Bean
    public OpenAPI customOpenApi () {

        return new OpenAPI()
                .info(new Info()
                        .title("Controle_Financeiro")
                        .version("1.0")
                        .description("Documentação da API de controle financeiro da aula de fullstack")
                );

    }

}
