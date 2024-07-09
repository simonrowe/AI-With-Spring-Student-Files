package com.example;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	// @Bean
	// @Profile({"redis-vector-store","openai-embedding"})
	// public EmbeddingModel embeddingModel() {
	// 	// Can be any other EmbeddingModel implementation.
	// 	return new OpenAiEmbeddingModel(new OpenAiApi(System.getenv("SPRING_AI_OPENAI_API_KEY")));
	// }	
}
