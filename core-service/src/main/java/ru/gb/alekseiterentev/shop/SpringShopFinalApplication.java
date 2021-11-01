package ru.gb.alekseiterentev.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("secret.properties")
public class SpringShopFinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringShopFinalApplication.class, args);
	}

}
