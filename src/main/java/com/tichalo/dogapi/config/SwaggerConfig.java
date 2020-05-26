package com.tichalo.dogapi.config;

import com.sun.org.apache.bcel.internal.generic.FALOAD;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
@ApiResponses( value = {
        @ApiResponse(code = 400, message = "This is a bad request, please follow the docs for accuracy"),
        @ApiResponse(code = 401, message = "Due to security constraints, your request cannot be processed"),
        @ApiResponse(code = 500, message = "Something went wromg while processing your request")
})
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false);
    }

    private ApiInfo apiInfo(){
        return new ApiInfo(
                "Dog Api",
                "This API returns a list of dogs",
                "1.0",
                "https://udacity.om",
                new Contact("Muma Justice", "www.udacity.com", "ticha.mumaj@gmail.com"),
                "Licence of API", "https://bla.com", Collections.emptyList()
        );
    }
}
