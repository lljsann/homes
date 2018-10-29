package com.chinaxaxt.xtzncms.common.exception;

/**
 * 
 * @author 吴佳涛
 *
 */
public class CustomException extends Exception{
	
	private static final long serialVersionUID = 5348812826638851457L;

	private String msg;
	
	private String errorCode;
	
	
	public CustomException(String msg) {
		super(msg);
		this.msg = msg;
	}

	public CustomException(String msg, String errorCode) {
		super(msg);
		this.msg = msg;
		this.errorCode = errorCode;
	}

	public CustomException() {
		super();
	}


	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	

}
