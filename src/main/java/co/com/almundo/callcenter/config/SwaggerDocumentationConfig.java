package co.com.almundo.callcenter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-08-03T13:56:00.032-05:00")

@EnableSwagger2
@Configuration
public class SwaggerDocumentationConfig {

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Call Center Api")
            .description("API que permite gestionar llamadas")
            .license("")
            .licenseUrl("http://unlicense.org")
            .termsOfServiceUrl("")
            .version("1.0.0")
            .contact(new Contact("","", "andresbox5@gmail.com"))
            .build();
    }

    @Bean
    public Docket customImplementation(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                    .apis(RequestHandlerSelectors.basePackage("co.com.almundo.callcenter.config"))
                    .apis(RequestHandlerSelectors.basePackage("co.com.almundo.callcenter"))
                    .apis(RequestHandlerSelectors.basePackage("co.com.almundo.callcenter.controller"))
                    .apis(RequestHandlerSelectors.basePackage("co.com.almundo.callcenter.config.model"))
                    .apis(RequestHandlerSelectors.basePackage("co.com.almundo.callcenter.utilities"))
                    .paths(PathSelectors.any())
                    .build()
                .apiInfo(apiInfo());
    }

}
