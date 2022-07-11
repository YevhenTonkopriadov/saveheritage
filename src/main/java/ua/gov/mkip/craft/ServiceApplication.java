package ua.gov.mkip.craft;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.gov.mkip.craft.models.User;
import ua.gov.mkip.craft.models.enums.Role;
import ua.gov.mkip.craft.models.enums.Sex;
import ua.gov.mkip.craft.repositories.UserRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@SpringBootApplication
@RequiredArgsConstructor
public class ServiceApplication {

	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			if (userRepository.findByUsername("Tonkopriadov") == null) {
				User user = new User();
				user.setUsername("Tonkopriadov");
				user.setPassword(passwordEncoder.encode("Tonkopriadov"));
				user.setFirstName("Євген");
				user.setLastName("Тонкопрядов");
				user.setMiddleName("Олександрович");
				user.setDateOfBirth(LocalDate.of(1988,02,11));
				user.setEmail("Tonkopriadov@mkip.gov.ua");
				user.setPhone("+380(97)6863783");
				user.setSex(Sex.MAN);
				user.setRoles(Set.of(Role.ADMIN));
				userRepository.save(user);
				System.out.println("створено користувача Tonkopriadov");
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
	}

}
