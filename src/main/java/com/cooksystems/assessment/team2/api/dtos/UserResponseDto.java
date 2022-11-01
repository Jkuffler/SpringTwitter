package com.cooksystems.assessment.team2.api.dtos;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserResponseDto {

	private String userName;
	
	private boolean deleted;

	public List<TweetResponseDto> tweets;

}
