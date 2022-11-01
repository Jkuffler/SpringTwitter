package com.cooksystems.assessment.team2.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cooksystems.assessment.team2.entities.User;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {
	
	@Override
	public void run(String... args) throws Exception {
		User user1 = new User();
		user1.setFirstName("John");
		user1.setLastName("Cleese");
		user1.setUserName("ArmondHammer2022");
		user1.setDateJoined("10/01/2022");
		user1.setPassword("bondstone");
		user1.setPhoneNumber("407-555-5555");
		
		
	
				
		
		
	}

}
