package com.example.demo_mcp_client;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoMcpServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoMcpServerApplication.class, args);
	}

	@Bean
	public ToolCallbackProvider tools(WeatherService weatherService) {
		return MethodToolCallbackProvider.builder().toolObjects(weatherService).build();
	}

}
