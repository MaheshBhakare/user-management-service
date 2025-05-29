/**
 * 
 */
package com.microservice.userservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Mahesh Bhakare
 * @version 17
 * @date 29/05/2025
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException ex) {
//		ApiResponse builderPattern = new ApiResponse();
//		builderPattern.setMessage(ex.getMessage());
//		builderPattern.setSuccess(true);
//		builderPattern.setStatus(HttpStatus.BAD_REQUEST);

		// above line is example of builder pattern i.e., we build the object in single
		// line. instead of upper 4 line.

		ApiResponse builderPattern = ApiResponse.builder().message(ex.getMessage()).success(true)
				.status(HttpStatus.NOT_FOUND).build();
		return new ResponseEntity<ApiResponse>(builderPattern, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ApiResponse> handlerIllegalArgumentException(IllegalArgumentException ex) {
		ApiResponse builderPattern = ApiResponse.builder().message(ex.getMessage()).success(true)
				.status(HttpStatus.BAD_REQUEST).build();
		return new ResponseEntity<ApiResponse>(builderPattern, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse> handlerException(Exception ex) {
		ApiResponse builderPattern = ApiResponse.builder().message(ex.getMessage()).success(true)
				.status(HttpStatus.NOT_FOUND).build();
		return new ResponseEntity<ApiResponse>(builderPattern, HttpStatus.NOT_FOUND);
	}

}
