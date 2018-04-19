package com.att.demo.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.att.demo.controller.AccountController;

@Configuration
@EnableSwagger2
@ComponentScan(basePackageClasses=AccountController.class)
public class SwaggerConfig {

	private static final String SWAGGER_API_VERSION="1.0";
	private static final String LICENSE_TEXT="License";
	private static final String title="Demo Rest APIs";
	private static final String description="RestFul Apis for demo";
	
	private ApiInfo apiInfo(){
		return new ApiInfoBuilder()
		.version(SWAGGER_API_VERSION)
		.license(LICENSE_TEXT)
		.title(title)
		.description(description)
		.build();
	}
	
	@Bean
	public Docket demoApis(){
		
		return new Docket(DocumentationType.SWAGGER_2)
		.apiInfo(apiInfo())
		.pathMapping("/")
		.select()
		.paths(PathSelectors.regex("/.*"))
		.build();
		
	}
	
}
