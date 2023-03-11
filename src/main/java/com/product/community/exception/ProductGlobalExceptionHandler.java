package com.product.community.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.product.community.model.StandardMessage;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ProductGlobalExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<StandardMessage> handleCityNotFoundException(
    		UsernameNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(new StandardMessage("error","Invalid user or password"), HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<StandardMessage> handleExpiredJwtException(
    		ExpiredJwtException ex, WebRequest request) {
        return new ResponseEntity<>(new StandardMessage("error","JWT expired login again"), HttpStatus.FORBIDDEN);
    }
	
	@ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardMessage> handleIllegalArgumentException(
    		IllegalArgumentException ex, WebRequest request) {
        return new ResponseEntity<>(new StandardMessage("error",ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(QuestionNotFoundException.class)
    public ResponseEntity<StandardMessage> handleQuestionNotException(
    		QuestionNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(new StandardMessage("error",ex.getMessage()), HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<StandardMessage> handleQuestionNotException(
    		Exception ex, WebRequest request) {
        return new ResponseEntity<>(new StandardMessage("error",ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
	@ExceptionHandler(SignatureException.class)
    public ResponseEntity<StandardMessage> handleQuestionNotException(
    		SignatureException ex, WebRequest request) {
        return new ResponseEntity<>(new StandardMessage("error",ex.getMessage()), HttpStatus.NOT_ACCEPTABLE);
    }
	
	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex, HttpHeaders headers, 
        HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now());
        body.put("status", status.value());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
