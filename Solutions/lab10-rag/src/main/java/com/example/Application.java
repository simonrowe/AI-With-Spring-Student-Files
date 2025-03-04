package com.example;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;


@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	//	TODO-04: Define a bean method named "vectorStore" of type VectorStore.
	//  The method should accept an EmbeddingModel parameter.
	//	Have it use the SimpleVectorStore's builder method to inject the given EmbeddingModel.
	//  Use @Profile to assign this bean to the "simple-vector-store" profile.
	@Bean
	@Profile("simple-vector-store")
	public VectorStore vectorStore(EmbeddingModel embeddingModel) {
		return SimpleVectorStore.builder(embeddingModel).build();
	}
	
}
