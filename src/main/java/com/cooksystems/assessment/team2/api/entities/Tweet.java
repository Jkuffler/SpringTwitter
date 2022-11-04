package com.cooksystems.assessment.team2.api.entities;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name = "tweet")
public class Tweet implements Comparable<Tweet> {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private User author;
	
	@CreationTimestamp
	private Timestamp posted;
	
	private boolean deleted = false;
	
	private String content;
	
	@ManyToOne
	private Tweet inReplyTo;
	
	@OneToMany(mappedBy = "inReplyTo")
	private List<Tweet> replies;
	
	@ManyToOne
	private Tweet repostOf;
	
	@OneToMany(mappedBy = "repostOf")
	private List<Tweet> reposts;
	
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "tweet_hashtags", joinColumns = @JoinColumn (name = "tweet_id"), 
	inverseJoinColumns = @JoinColumn(name = "hashtag_id"))
	private List<Hashtag> hashtags;
	
	@ManyToMany(mappedBy = "likedTweets")
	private List<User> likedByUsers;
	
	@ManyToMany
	@JoinTable(name = "user_mentions", joinColumns = @JoinColumn (name = "tweet_id"), 
	inverseJoinColumns = @JoinColumn(name = "user_id") )
	private List<User> mentionedUsers;

	@Override
	public int compareTo(Tweet t) {
		if (getPosted() == null || t.getPosted() == null) {
			return 0;
		}
		return getPosted().compareTo(t.getPosted());
	}
	
	
	
	
	
}
