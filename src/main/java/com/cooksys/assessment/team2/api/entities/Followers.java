package com.cooksys.assessment.team2.api.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name = "followers_following")
public class Followers {
	
	private int follower_id;
	
	private int following_id;

}
