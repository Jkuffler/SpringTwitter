package com.cooksystems.assessment.team2.api.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.lang.NonNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Embeddable
public class Credentials {
	
	@NonNull
	@Column(unique = true)
	private String userName;
	
	@NonNull
	private String password;

}
