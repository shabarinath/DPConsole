package com.dpconsole.exception;
/**
 * @author SHABARINATH
 * 26-Dec-2018 11:12:10 PM 2018 
 */

public class ItemNotFoundException extends Exception {

	private short errorCode;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 */
	public ItemNotFoundException() {
		super();
	}

	/**
	 * Constructor.
	 * 
	 * @param message
	 */
	public ItemNotFoundException(String message) {
		super(message);
	}
	
	/**
	 * Constructor.
	 * 
	 * @param message
	 */
	public ItemNotFoundException(String message, short errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public short getErrorCode() {
		return errorCode;
	}	
}

