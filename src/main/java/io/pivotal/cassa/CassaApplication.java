package io.pivotal.cassa;

import net.datafaker.Faker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CassaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CassaApplication.class, args);
	}

	@Bean
	Faker faker() {
		return new Faker();
	}
}
