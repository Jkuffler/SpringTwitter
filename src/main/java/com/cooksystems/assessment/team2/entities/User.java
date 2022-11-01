package com.cooksystems.assessment.team2.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

	@Column(unique = true)
	private String userName;

	private String password;

	private String dateJoined;

	private boolean deleted;

	private String firstName;

	private String lastName;

	private String email;

	private String phoneNumber;

	@OneToMany(mappedBy = "user")
	private List<Tweet> tweets;
	
	@OneToMany(mappedBy = "user")
	private List<UserLikes> userLikes;
	
	@OneToMany(mappedBy = "user")
	private List<UserMentions> userMentions;

}
