package com.javaf.jndi.example;

/**
 * 
 * @author fabiojm - Fábio José de Moraes
 *
 */
public class NoMoreMoneyException extends Exception {
	private static final long serialVersionUID = 6165286869491747837L;

	public NoMoreMoneyException(){
		
	}
	
	public NoMoreMoneyException(String message){
		super(message);
	}
	
	public NoMoreMoneyException(Throwable cause){
		super(cause);
	}
	
	public NoMoreMoneyException(String message, Throwable cause){
		super(message, cause);
	}
}
