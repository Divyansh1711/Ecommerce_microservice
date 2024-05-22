package com.microService_Project.CloudGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.Customizer;


import java.beans.BeanProperty;

@SpringBootApplication
public class CloudGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudGatewayApplication.class, args);
	}

//
//	@Bean
//	public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
//		return factory -> factory.configureDefault(
//				id -> new Resilience4JConfigBuilder(id)
//						.circuitBreakerConfig(
//								CircuitBreakerConfig.ofDefaults()
//						).build()
//		);
//	}
}
