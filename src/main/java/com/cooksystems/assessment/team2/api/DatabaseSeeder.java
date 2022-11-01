package com.cooksystems.assessment.team2.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cooksystems.assessment.team2.api.entities.User;
import com.cooksystems.assessment.team2.api.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		User user1 = new User();
		user1.setUserName("ArmondHammer2022");
		user1.setFirstName("Armond");
		user1.setLastName("Hammer");
		user1.setPassword("bondstone");
		user1.setDateJoined("11/1/2022");
		user1.setEmail("armondhammer2022@gmail.com");
		user1.setPhoneNumber("407-555-5555");
		
		List<User> listOfUsers = new ArrayList<>();
		listOfUsers.add(user1);
		
		userRepository.saveAllAndFlush(listOfUsers);
		
		
	}

}
