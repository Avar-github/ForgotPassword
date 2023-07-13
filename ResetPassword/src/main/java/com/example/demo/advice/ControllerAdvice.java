package com.example.demo.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.exception.UserNotFoundException;

@RestControllerAdvice
public class ControllerAdvice {
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleUser(UserNotFoundException userNotFoundException){
		return new ResponseEntity<String>(userNotFoundException.getMessage(),HttpStatus.BAD_REQUEST);
	}

}
