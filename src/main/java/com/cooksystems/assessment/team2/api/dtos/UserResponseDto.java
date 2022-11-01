package com.cooksystems.assessment.team2.api.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserResponseDto {

	private String userName;
	
	private boolean deleted;

	private String email;

}
