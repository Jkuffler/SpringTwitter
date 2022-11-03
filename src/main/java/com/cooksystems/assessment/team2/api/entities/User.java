package com.cooksystems.assessment.team2.api.entities;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name = "user_table")
public class User {

	@Id
	@GeneratedValue
	private Long id;

	@CreationTimestamp
	@NonNull
	private Timestamp joined;

	private boolean deleted = false;

	@OneToMany(mappedBy = "author", cascade = (CascadeType.ALL))
	private List<Tweet> tweets;
	
	@Embedded
	private Profile profile;

	@Embedded
	private Credentials credentials;
	
	
	@ManyToMany
	@JoinTable(name = "user_likes", joinColumns = @JoinColumn (name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "tweet_id"))
	private List<Tweet> likedTweets;
	
	@ManyToMany(mappedBy = "mentionedUsers")
	private List<Tweet> mentionedTweets;
	
	@ManyToMany
	@JoinTable(name = "followers_following")
	private List<User> followers;
	
	@ManyToMany(mappedBy = "followers")
	private List<User> following;
	
	
	

}
