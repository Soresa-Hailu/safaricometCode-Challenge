package com.student.exception;

public class StudentServiceException extends RuntimeException{

	private static final long serialVersionUID = -6845007273139721665L;

	public StudentServiceException(String message) {
		super(message);
	}
}
