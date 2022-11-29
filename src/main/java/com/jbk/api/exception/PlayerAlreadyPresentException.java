package com.jbk.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class PlayerAlreadyPresentException extends RuntimeException{
	public PlayerAlreadyPresentException(String msg) {
		super(msg);
}
}
	
	 