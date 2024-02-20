package dev.ajim.springdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class SpringDemoApplication implements CommandLineRunner {
	@Value("${spring.profiles.active: default}")
	String activeProfile;

	@Value("${demo.message: nothing}")
	String demoMessage;
	public static void main(String[] args) {
		SpringApplication.run(SpringDemoApplication.class, args);
	}

	@Profile("dev")
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Application started on " + activeProfile + " profile");
		System.out.println("Message: " + demoMessage);
	}
}
