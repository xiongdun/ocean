/**
 * 
 */
package com.brucex.common.exception;

/**
 * @description dao exception
 * @author xiongdun
 * @datetime 2017年4月11日下午10:01:46
 */
public class DaoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3827447743167562376L;
	
	public DaoException() {
		super();
	}
	
	public DaoException(String message) {
		super(message);
	}

	public DaoException(Throwable throwable) {
		super(throwable);
	}
	
	public DaoException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
