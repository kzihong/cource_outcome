package com.gdut.course.exception;
/**
 * 自定义异常
 * @author David
 *
 */
@SuppressWarnings("serial")
public class ErrorException extends Exception {
	private String message;
	public ErrorException(String message){
		this.message = message;
	}
	@Override
	public String getMessage() {
		return this.message;
	}
	
}
