/**
 * 
 */
package com.ems.exception;

/**
 * @author Christopher Dsouza
 *
 */
public class AdException extends Exception {
	
	public AdException(String message)
	{
		super(message);
	}
	
	public AdException(String message, Throwable cause)
	{
		super(message,cause);
	}
}
