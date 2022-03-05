package ua.gov.mkip.saveheritage;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.gov.mkip.saveheritage.models.User;
import ua.gov.mkip.saveheritage.repositories.UserRepository;

@SpringBootApplication
@RequiredArgsConstructor
public class ServiceApplication {

	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			System.out.println("Creating user...");
			User user = new User();
			user.setUsername("elik");
			user.setPassword(passwordEncoder.encode("elik"));
			user.setEmail("elik@ukr.net");
			user.setRole("admin");
			user.setOrganizationName("minCult");
			userRepository.save(user);
		};
	}


	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
	}

}
