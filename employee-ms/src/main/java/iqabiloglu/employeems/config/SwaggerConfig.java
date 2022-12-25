package iqabiloglu.employeems.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        var apiInfo = new ApiInfoBuilder().title("Employee Project")
                                          .version("1.0")
                                          .license(null)
                                          .licenseUrl(null)
                                          .termsOfServiceUrl(null)
                                          .description("The Employe project ui to show the integration of " +
                                                               "swagger ui.")
                                          .build();

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo)
                                                   .useDefaultResponseMessages(false)
                                                   .select()
                                                   .apis(RequestHandlerSelectors.basePackage("iqabiloglu.employeems"))
                                                   .paths(PathSelectors.regex("/.*"))
                                                   .build();
    }


}
