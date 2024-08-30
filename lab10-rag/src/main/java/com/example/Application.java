package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	//	TODO-04: Define a bean method named "vectorStore" of type VectorStore.
	//  The method should accept an EmbeddingModel parameter.
	//	Have it instantiate and return a new SimpleVectorStore injected with the given EmbeddingModel.
	//  Use @Profile to assign this bean to the "simple-vector-store" profile.

	
}
