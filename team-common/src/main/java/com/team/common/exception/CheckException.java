package com.team.common.exception;


import com.team.common.constants.result.Constant;

public class CheckException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private int errorCode= Constant.RESPONSE.REP_FAIL;

	public CheckException() {
	}

	public int getErrorCode(){
		return this.errorCode;
	}

	public CheckException(int errorCode, String message){
		super(message);
		this.errorCode=errorCode;
	}

	public CheckException(String message) {
		super(message);
	}

	public CheckException(Throwable cause) {
		super(cause);
	}

	public CheckException(String message, Throwable cause) {
		super(message, cause);
	}

	public CheckException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
