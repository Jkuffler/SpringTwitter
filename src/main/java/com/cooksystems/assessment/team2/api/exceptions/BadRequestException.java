package com.cooksystems.assessment.team2.api.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = -3223590466752901603L;
	
	private String message;
}
