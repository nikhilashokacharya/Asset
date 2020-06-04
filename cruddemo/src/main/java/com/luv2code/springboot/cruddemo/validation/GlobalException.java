package com.luv2code.springboot.cruddemo.validation;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.luv2code.springboot.cruddemo.exceptions.UserNotFoundException;
import com.luv2code.springboot.cruddemo.response.Response;
@ControllerAdvice

    public class GlobalException{
    	@ExceptionHandler(MethodArgumentNotValidException.class)
    	public ResponseEntity<?> handleCustomValidationError(MethodArgumentNotValidException e){
    		Response<?> response = new Response(true,e.getBindingResult().getFieldError().getDefaultMessage(),new ArrayList());
    		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    	}
    }
