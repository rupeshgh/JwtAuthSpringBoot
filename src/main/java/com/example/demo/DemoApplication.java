package com.example.demo;


import com.example.demo.Model.Roles;
import com.example.demo.Model.User;
import com.example.demo.Service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}



	@Bean
	CommandLineRunner run(UserService userService){

		return args->{
			userService.saveRole(new Roles("ROLE_USER"));
			userService.saveRole(new Roles("ROLE_ADMIN"));
			userService.saveRole(new Roles("ROLE_MANAGER"));

			userService.saveUser(new User("hello1","123","xyz1@gmail.com"));
			userService.saveUser(new User("hello2","123","xyz2@gmail.com"));
			userService.saveUser(new User("hello3","123","xyz3@gmail.com"));
			userService.saveUser(new User("hello4","123","xyz4@gmail.com"));
			userService.saveUser(new User("hello5","123","xyz5@gmail.com"));


			userService.addRoleToUser("xyz1@gmail.com","ROLE_USER");
			userService.addRoleToUser("xyz2@gmail.com","ROLE_USER");
			userService.addRoleToUser("xyz3@gmail.com","ROLE_ADMIN");
			userService.addRoleToUser("xyz4@gmail.com","ROLE_USER");
			userService.addRoleToUser("xyz5@gmail.com","ROLE_MANAGER");
		};
	}


}
