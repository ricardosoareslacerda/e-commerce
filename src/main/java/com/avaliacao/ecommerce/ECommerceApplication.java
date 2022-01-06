package com.avaliacao.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"com.avaliacao.ecommerce.model"})
@EnableJpaRepositories(basePackages = {"com.avaliacao.ecommerce.repository"})
@ComponentScan(basePackages = {"com.avaliacao.ecommerce.service", "com.avaliacao.ecommerce.controller", "com.avaliacao.ecommerce.controller.util"})
@SpringBootApplication
public class ECommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}

}
