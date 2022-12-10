package tn.esprit.spring.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import tn.esprit.spring.service.AdminServiceImpl;
import tn.esprit.spring.service.IAdminService;

@Configuration
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()

				//.apis(RequestHandlerSelectors.any()) //any: documenter toutes les classes dans tous les packages
				.apis(RequestHandlerSelectors.basePackage("tn.esprit.spring")) // basePackage permet de demander à Swagger de ne rien documenter en dehors du package "com.esprit.examen".

				.paths(PathSelectors.any())
				//.paths(PathSelectors.regex("/SpringMVC/client.*")) // accepte seulement les URIs qui commençent par /client. 

				.build().apiInfo(apiInfo());//Informations personnalisées
				//.build();
	}
	@Bean(name="IAdminService")
	public IAdminService IadminService(){
		return new AdminServiceImpl();
	}

	@Bean(name="encoder")
	public static NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}
	@Bean
	public JavaMailSender javaMailSender() {
		return new JavaMailSenderImpl();
	}


	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Eseo bat")
				.description("\"Eseo bat Swagger configuration\"")
				.version("1.1.0")
				.build();
	}


}
