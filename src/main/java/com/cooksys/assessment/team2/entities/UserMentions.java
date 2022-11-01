package com.cooksys.assessment.team2.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name = "user_mentions")
public class UserMentions {
	
	private int user_id;
	
	private int tweet_id;

}
