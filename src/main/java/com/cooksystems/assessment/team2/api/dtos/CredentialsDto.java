package com.cooksystems.assessment.team2.api.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CredentialsDto {
	
	private String username;
	
	private String password;
}
