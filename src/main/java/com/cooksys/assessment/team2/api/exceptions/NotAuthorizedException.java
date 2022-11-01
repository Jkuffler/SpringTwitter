package com.cooksys.assessment.team2.api.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NotAuthorizedException extends RuntimeException {

	private static final long serialVersionUID = -7009695057179285123L;

	private String message;
}
