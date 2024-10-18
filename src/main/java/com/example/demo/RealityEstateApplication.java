package com.example.demo;

import com.example.demo.inputs.mappers.*;
import com.example.demo.outputs.mappers.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RealityEstateApplication {

	public static void main(String[] args) {
		SpringApplication.run(RealityEstateApplication.class, args);
	}

	@Bean
	public MediaInputMapper mediaInputMapper() {
		return new MediaInputMapperImpl();
	}

	@Bean
	public MediaOutputMapper outputMapper() {
		return new MediaOutputMapperImpl();
	}

	@Bean
	public RealityInputMapper realityInputMapper() {
		return new RealityInputMapperImpl();
	}

	@Bean
	public RealityOutputMapper realityOutputMapper() {
		return new RealityOutputMapperImpl();
	}

	@Bean
	public UserInputMapper userInputMapper() {
		return new UserInputMapperImpl();
	}

	@Bean
	public UserOutputMapper userOutputMapper() {
		return new UserOutputMapperImpl();
	}
}
