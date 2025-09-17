package org.example.pimob.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@OpenAPIDefinition(
//        info = @Info(
//                title = "API de Portal Imobiliário",
//                version = "1.0.0",
//                description = "Api para o PIMOB - Portal Imobiliário",
//                termsOfService = "http://swagger.io/terms/",
//                contact = @Contact(
//                        name = "Jefferson Ferreira",
//                        email = "jefferson14489@gmail.com",
//                        url = ""
//                ),
//                license = @License(
//                        name = "Apache 2.0",
//                        url = "http://www.apache.org/licenses/LICENSE-2.0.html"
//                )
//        )
//        servers = @Server(
//                url = "http://localhost:8080",
//                description = "Servidor de Desenvolvimento Local"
//        )
//)
public class SwaggerConfig {

  @Bean
  public OpenAPI customOpenApiConfig() {
    return new OpenAPI().info(
            new Info()
                    .title("API de Portal Imobiliário")
                    .version("1.0.0")
                    .description("Api para o PIMOB - Portal Imobiliário")
    );
  }
}
