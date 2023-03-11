package com.product.community.exception;

public class QuestionNotFoundException extends Exception {

	public QuestionNotFoundException() {
		
	}

	public QuestionNotFoundException(String message) {
		super(message);
		
	}

	public QuestionNotFoundException(Throwable cause) {
		super(cause);
		
	}

	public QuestionNotFoundException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public QuestionNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
