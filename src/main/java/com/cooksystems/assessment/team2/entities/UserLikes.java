package com.cooksystems.assessment.team2.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name = "user_likes")
public class UserLikes {
	
	private int user_id;
	
	private int twitter_id;

}
