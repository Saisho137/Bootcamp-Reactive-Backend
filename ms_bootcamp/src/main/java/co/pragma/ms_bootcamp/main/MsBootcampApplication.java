package co.pragma.ms_bootcamp.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "co.pragma.ms_bootcamp")
public class MsBootcampApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsBootcampApplication.class, args);
	}

}
