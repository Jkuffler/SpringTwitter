package com.cooksystems.assessment.team2.api.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name = "tweet")
public class Tweet {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private int author;
	
	private String posted;
	
	private boolean deleted;
	
	private String content;
	
	private int inReplyTo;
	
	private int repostOf;
	
	@ManyToOne()
	User user;
	
}
