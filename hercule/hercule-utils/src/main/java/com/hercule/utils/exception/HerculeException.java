package com.hercule.utils.exception;

public class HerculeException extends Exception {

	private static final long serialVersionUID = 1L;
	private String cleMessage;
	private int codeException;

	public HerculeException(int codeException, String cleMessage) {
		super(cleMessage);

		if (cleMessage == null) {
			throw new IllegalArgumentException("clé message null");
		}
		
		this.codeException = codeException;
		this.cleMessage = cleMessage;
	}

	
	/**
	 * @return the codeException
	 */
	public int getCodeException() {
		return codeException;
	}


	/**
	 * @param codeException the codeException to set
	 */
	public void setCodeException(int codeException) {
		this.codeException = codeException;
	}


	/**
	 * @return the cleMessage
	 */
	public String getCleMessage() {
		return cleMessage;
	}

	/**
	 * @param cleMessage the cleMessage to set
	 */
	public void setCleMessage(String cleMessage) {
		this.cleMessage = cleMessage;
	}
	
	/**
	 * Message d'erreur
	 * @return
	 */
	public String promptError() {
		return "Code " + this.codeException + " : " + this.cleMessage;
	}
}
