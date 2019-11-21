package com.betapi;

import com.betapi.models.Role;
import com.betapi.models.User;
import com.betapi.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@SpringBootApplication()
public class BetApiApplication {
	final Environment env;
	final UserRepository userRepository;
	final PasswordEncoder passwordEncoder;
	private Logger logger = LoggerFactory.getLogger(BetApiApplication.class);

	@Autowired
	public BetApiApplication(Environment env, UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.env = env;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}


	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(BetApiApplication.class);
		springApplication.addListeners(new ApplicationPidFileWriter());
		springApplication.run(args);
	}

	@PostConstruct
	public void setupLocalDatabase(){
		if(Arrays.asList(env.getActiveProfiles()).contains("local")){
			logger.info("Creating root user ...");
			User user = new User();
			user.setUsername("root");
			user.setPassword(passwordEncoder.encode("root"));
			user.grantAuthority(Role.ROLE_ADMIN);
			userRepository.save(user);

			logger.info("Created root user with success");
			logger.info("Username : root");
			logger.info("Password : root");
		}
	}


}
