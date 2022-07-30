package com.devblack.xray.controller;

import com.devblack.xray.core.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<?> notFound(final NotFoundException notFoundException) {
		
		final Map<String, Object> exceptionDto = Map.of(
						"status", HttpStatus.NOT_FOUND.value(),
						"message", notFoundException.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDto);
	}

}
