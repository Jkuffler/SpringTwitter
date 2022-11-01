package com.cooksystems.assessment.team2.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name = "hashtag")
public class Hashtag {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String label;
	
	private String firstUsed;
	
	private String lastUsed;

}
