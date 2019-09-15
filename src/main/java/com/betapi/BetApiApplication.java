package com.betapi;

import com.betapi.model.User;
import com.betapi.services.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@SpringBootApplication()
public class BetApiApplication {
	@Autowired Environment env;
	@Autowired
	UserRepository userRepository;
	private Logger logger = LoggerFactory.getLogger(BetApiApplication.class);

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(BetApiApplication.class);
		springApplication.addListeners(new ApplicationPidFileWriter());
		springApplication.run(args);
	}

	@PostConstruct
	public void setupLocalDatabase(){
		if(Arrays.asList(env.getActiveProfiles()).contains("local")){
			logger.info("Creating root user ...");
			User root = new User();
			root.setUserName("root");
			root.setPassword("root");
			root.setRole("ADMIN");
			userRepository.save(root);
			logger.info("Created root user with success");
			logger.info("Username : root");
			logger.info("Password : root");
		}
	}


}
