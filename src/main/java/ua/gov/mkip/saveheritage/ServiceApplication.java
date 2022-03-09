package ua.gov.mkip.saveheritage;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.gov.mkip.saveheritage.models.Role;
import ua.gov.mkip.saveheritage.models.User;
import ua.gov.mkip.saveheritage.repositories.UserRepository;
import java.util.Set;

@SpringBootApplication
@RequiredArgsConstructor
public class ServiceApplication {

	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;

	/*@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			User user = new User();
			user.setUsername("Tonkopriadov");
			user.setPassword(passwordEncoder.encode("Tonkopriadov"));
			user.setEmail("Tonkopriadov@mkip.gov.ua");
			user.setRoles(Set.of(Role.ADMIN));
			user.setOrganizationName("MKIP");
			userRepository.save(user);
		};
	}*/

	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
	}

}
