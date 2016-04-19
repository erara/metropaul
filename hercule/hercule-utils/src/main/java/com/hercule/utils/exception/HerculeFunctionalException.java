package com.hercule.utils.exception;

public class HerculeFunctionalException extends HerculeException {

	private static final long serialVersionUID = 1L;
	private static int codeException = -1000;
	
	public HerculeFunctionalException(String cleMessage) {
		super(codeException, cleMessage);
	}
	
	public HerculeFunctionalException(int codeException, String cleMessage) {
		super(codeException, cleMessage);
	}

}
