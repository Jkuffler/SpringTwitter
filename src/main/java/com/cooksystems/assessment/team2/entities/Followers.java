package com.cooksystems.assessment.team2.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name = "followers_following")
public class Followers {
	
	@ManyToOne
	@JoinColumn
	private int follower_id;
	@ManyToOne()
	private int following_id;

}
