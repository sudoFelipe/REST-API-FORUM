package br.com.sudofelipe.forum.config.swagger;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.sudofelipe.forum.modelo.Usuario;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfigurations {

	@Bean 
	public Docket forumApi() {
		
		return new Docket(DocumentationType.SWAGGER_2)	// Tipo de documentação
		        .select()								// Seleção
		        .apis(RequestHandlerSelectors.basePackage("br.com.sudofelipe.forum"))	//Indicação dos pacotes de leitura para a geração da documentação
		        .paths(PathSelectors.ant("/**"))	//Limitar os path's (caminhos de acesso) obs: não está sendo limitado
		        .build()	// Construção
		        .ignoredParameterTypes(Usuario.class)	//Ignora os dados relativos ao usuário (dados sensíveis)
		        .globalOperationParameters(Arrays.asList(	//Montagem do parâmetro que será passado para API
                        new ParameterBuilder()
                                .name("Authorization")
                                .description("Header para token JWT")
                                .modelRef(new ModelRef("string"))
                                .parameterType("header")
                                .required(false)
                                .build()));
	}
}
