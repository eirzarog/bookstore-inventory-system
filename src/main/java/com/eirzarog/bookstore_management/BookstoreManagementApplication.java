package com.eirzarog.bookstore_management;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "Bookstore API",
				version = "1.0",
				description = "REST API for managing books, authors, and orders"
		)
)
@SpringBootApplication
public class BookstoreManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreManagementApplication.class, args);
	}

}
