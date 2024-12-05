package com.yc.SecurePro;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import com.yc.SecurePro.dto.req.RegisterRequestDto;
import com.yc.SecurePro.entity.enums.Role;
import com.yc.SecurePro.service.Interfaces.IAccountService;

@SpringBootApplication
public class SecureProApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecureProApplication.class, args);
	}

	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	@Bean
	CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager){
		PasswordEncoder passwordEncoder = passwordEncoder();

		return args->{
			UserDetails u1 = jdbcUserDetailsManager.loadUserByUsername("User 1");
			if(u1 ==null){
				jdbcUserDetailsManager.createUser(
				User.withUsername("User 1").password(passwordEncoder.encode("1234")).roles(Role.USER.name()).build()
				);
			}

			UserDetails u2 = jdbcUserDetailsManager.loadUserByUsername("User 2");
			if(u2 ==null){
				jdbcUserDetailsManager.createUser(
					User.withUsername("User 2").password(passwordEncoder.encode("1234")).roles(Role.USER.name()).build()
				);
			}

			UserDetails u3 = jdbcUserDetailsManager.loadUserByUsername("Admin 1");
			if(u3 ==null){
				jdbcUserDetailsManager.createUser(
				User.withUsername("Admin 1").password(passwordEncoder.encode("1234")).roles(Role.USER.name(),Role.ADMIN.name()).build()
			);
			}

		};
	}

	//@Bean
	CommandLineRunner commandLineRunnerUserDetails(IAccountService accountService){
		PasswordEncoder passwordEncoder = passwordEncoder();

		return args->{
			accountService.addNewRole("USER");
			accountService.addNewRole("ADMIN");
			accountService.register(new RegisterRequestDto("User 1", "user1@gmail.com", "1234", "1234"));
			// accountService.register(new RegisterRequestDto("User 2", "user2@gmail.com", "1234", "1234"));
			// accountService.register(new RegisterRequestDto("Admin", "admin@gmail.com", "1234", "1234"));

			// accountService.addRoleToUser("User 1", "USER");
			// accountService.addRoleToUser("User 2", "USER");
			// accountService.addRoleToUser("Admin", "USER");
			// accountService.addRoleToUser("Admin", "Admin");

		};
	}
}
