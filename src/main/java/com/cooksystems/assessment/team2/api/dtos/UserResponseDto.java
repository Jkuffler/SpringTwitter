package com.cooksystems.assessment.team2.api.dtos;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserResponseDto {

	private String userName;
	
	private Timestamp joined;
	
	private ProfileDto profile;
	

	
	

}
