package com.sagar.demo.core

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    fun api(): Docket {
        val apiInfo = ApiInfo(
            "Api Documentation",
            "Api Documentation",
            "1.0",
            "urn:tos",
            Contact("Sagar Kumar Nayak", "urllllll", "emaillllll"),
            "Apache 2.0",
            "http://www.apache.org/licenses/LICENSE-2.0",
            ArrayList()
        )
        return Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo)
    }
}