package com.example.demo;

import com.example.demo.domain.delegators.RealityInputPortDelegator;
import com.example.demo.domain.ports.realities.RealitiesInputPort;
import com.example.demo.inputs.mappers.InputMapper;
import com.example.demo.inputs.mappers.InputMapperImpl;
import com.example.demo.outputs.mappers.OutputMapper;
import com.example.demo.outputs.mappers.OutputMapperImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RealityEstateApplication {

	public static void main(String[] args) {
		SpringApplication.run(RealityEstateApplication.class, args);
	}

	@Bean
	public InputMapper inputMapper() {
		return new InputMapperImpl();
	}

	@Bean
	public OutputMapper outputMapper() {
		return new OutputMapperImpl();
	}
}
