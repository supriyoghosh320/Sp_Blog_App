package com.sg.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.sg.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception ex, WebRequest req) {
		return new ResponseEntity<>(

				new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), req.getDescription(false),
						LocalDate.now()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
