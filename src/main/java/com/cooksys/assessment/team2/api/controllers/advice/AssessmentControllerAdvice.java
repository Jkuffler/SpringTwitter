package com.cooksys.assessment.team2.api.controllers.advice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cooksys.assessment.team2.api.dtos.ErrorDto;
import com.cooksys.assessment.team2.api.exceptions.BadRequestException;
import com.cooksys.assessment.team2.api.exceptions.NotAuthorizedException;
import com.cooksys.assessment.team2.api.exceptions.NotFoundException;

@ControllerAdvice(basePackages = { "com.cooksys.lemonadestand.controllers"})
@ResponseBody
public class AssessmentControllerAdvice {
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BadRequestException.class)
	public ErrorDto handleBadRequestException(HttpServletRequest request, BadRequestException badRequestException) {
		return new ErrorDto(badRequestException.getMessage());
	}
	
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(NotAuthorizedException.class)
	public ErrorDto handleNotAuthorizedException(HttpServletRequest request, NotAuthorizedException notAuthorizedException) {
		return new ErrorDto(notAuthorizedException.getMessage());
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public ErrorDto handleNotFoundException(HttpServletRequest request, NotFoundException notFoundException) {
		return new ErrorDto(notFoundException.getMessage());
	}
}
