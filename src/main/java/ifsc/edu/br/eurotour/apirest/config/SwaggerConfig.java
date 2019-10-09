package ifsc.edu.br.eurotour.apirest.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket productAPI() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("ifsc.edu.br.eurotour.apirest"))
				.paths(PathSelectors.regex("/api.*"))
				.build()
				.apiInfo(createMetaInfo());
				
	}

	private ApiInfo createMetaInfo() {
		ApiInfo lApiInfo = new ApiInfo("API REST Euro Tour",
				"API REST Para um trabalho de IA sobre Algoritmos de busca",
				"1.0",
				"Uso restrito para estudantes",
				new Contact("Mapa IA", "", "wilson.fcj@aluno.ifsc.edu.br"),
				"Chefe Apache License Version 2.0",
				"http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<>());
		return lApiInfo;
	}
	
}
