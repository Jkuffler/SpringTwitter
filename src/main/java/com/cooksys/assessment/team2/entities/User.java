package com.cooksys.assessment.team2.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name = "user_table")
public class User {
	
	@Id
	@GeneratedValue
	private Long idLong;
	
	private String userName;
	
	private String password;
	
	private String dateJoined;
	
	private boolean deleted;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String phoneNumber;

}
