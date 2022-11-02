package com.cooksystems.assessment.team2.api.entities;

import javax.persistence.Embeddable;

import org.springframework.lang.NonNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Embeddable
@Data
public class Profile {

	private String firstName;

	private String lastName;

	@NonNull
	private String email;

	private String phone;

}
