package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;

import com.example.demo.service.UserServiceImpl;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ResetPasswordApplication {
	


	public static void main(String[] args) {
		SpringApplication.run(ResetPasswordApplication.class, args);
	}
	public Docket apis() {
		return new Docket(DocumentationType.SWAGGER_2)
					.select()
					.paths(PathSelectors.ant("/password/*"))
					.apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
					.build();
	}

}
