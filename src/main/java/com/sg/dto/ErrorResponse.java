package com.sg.dto;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {

	private HttpStatus status;
	private String message;
	private String path;
	private LocalDate date;
}
