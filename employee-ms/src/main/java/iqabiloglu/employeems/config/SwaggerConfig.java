package iqabiloglu.employeems.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api( ) {

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                                                      .useDefaultResponseMessages(false)
                                                      .select()
                                                      .apis(RequestHandlerSelectors.basePackage(
                                                              "iqabiloglu.employeems"))
                                                      .paths(PathSelectors.any())
                                                      .build();
    }

    private ApiInfo apiInfo( ) {
        return new ApiInfoBuilder().
                title("Employee Project")
                .version("1.0")
                .description("The Employee project ui to show the integration of swagger ui.")
                .build();
    }


}
