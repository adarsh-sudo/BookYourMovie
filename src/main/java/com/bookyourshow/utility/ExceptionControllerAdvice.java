package com.bookyourshow.utility;

import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bookyourshow.exception.BookYourShowException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionControllerAdvice {
	
	
	private static final Log LOGGER =  LogFactory.getLog(ExceptionControllerAdvice.class);
	
	@Autowired
	private Environment environment;
	
	
	@ExceptionHandler(BookYourShowException.class)
	public ResponseEntity<ErrorInfo> bookYourShowExceptionHandler(BookYourShowException exception)
	{
		LOGGER.error(exception.getMessage() , exception);
		
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
		errorInfo.setMessage(environment.getProperty(exception.getMessage()));
		
		return new ResponseEntity<>(errorInfo , HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> generalExceptionHandler(Exception exception)
	{
		LOGGER.error(exception.getMessage() , exception);
		
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorInfo.setMessage(environment.getProperty("General.EXCEPTION_MESSAGE"));
		
		return new ResponseEntity<>(errorInfo , HttpStatus.INTERNAL_SERVER_ERROR);
		
		
		
	}
	
	@ExceptionHandler({ MethodArgumentNotValidException.class, ConstraintViolationException.class})
	public ResponseEntity<ErrorInfo> validatorExceptionHandler(Exception exception)
	{
		LOGGER.error(exception.getMessage() , exception);
		String errorMsg;
		
		if(exception instanceof MethodArgumentNotValidException methodArgumentNotValidException)
		{
			errorMsg = methodArgumentNotValidException.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(", "));
			
			
		}
		
		else
		{
			ConstraintViolationException constraintViolationException = (ConstraintViolationException)exception;
			errorMsg = constraintViolationException.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(", "));
		}
		
		
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
		errorInfo.setMessage(errorMsg);
		
		return new ResponseEntity<>(errorInfo , HttpStatus.BAD_REQUEST);
		
		
	}
	

}
