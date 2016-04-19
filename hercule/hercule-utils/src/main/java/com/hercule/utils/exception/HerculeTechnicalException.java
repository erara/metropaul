package com.hercule.utils.exception;

public class HerculeTechnicalException extends HerculeException {

	private static final long serialVersionUID = 1L;
	private static int codeException = -9000;
	
	public HerculeTechnicalException(String cleMessage) {
		super(codeException, cleMessage);
	}


	public HerculeTechnicalException(int codeException, String cleMessage) {
		super(codeException, cleMessage);
	}
}
