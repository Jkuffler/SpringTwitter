package com.cooksys.assessment.team2.api.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -2256328494518532992L;
	
	private String message;
	
}
