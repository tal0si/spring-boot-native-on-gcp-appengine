package com.example.jpa;

import com.example.jpa.model.entity.Customer;
import com.example.jpa.repository.CustomerRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import java.util.stream.Stream;

@SpringBootApplication
public class JpaApplication {
	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}
}

@Component
record Initializr(CustomerRepository repository)
		implements ApplicationRunner {

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Stream.of("A", "B", "C", "D")
				.map(c -> new Customer(null, c))
				.map(this.repository::save)
				.forEach(System.out::println);
	}
}
