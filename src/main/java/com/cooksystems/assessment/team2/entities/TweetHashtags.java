package com.cooksystems.assessment.team2.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name = "tweet_hashtags")
public class TweetHashtags {
	
	private int tweet_id;
	
	private int hashtag_id;
	

}
