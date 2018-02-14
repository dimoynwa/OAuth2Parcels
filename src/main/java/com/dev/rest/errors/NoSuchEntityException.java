package com.dev.rest.errors;

public class NoSuchEntityException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoSuchEntityException() {
		super();
	}
	
	public NoSuchEntityException(String message) {
		super(message);
	}
	
}
