package com.movie.movieRest;

import com.movie.movieRest.Repo.UserRepository;
import com.movie.movieRest.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MovieRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieRestApplication.class, args);
	}


//	@Bean
//	CommandLineRunner test(UserRepository userRepository) {
//		return args -> {
//			User user = new User();
//			user.setEmail("dummy@test.com");
//			user.setPassword("123456");
//			userRepository.save(user);
//			System.out.println("Saved dummy user");
//		};
//	}

}
