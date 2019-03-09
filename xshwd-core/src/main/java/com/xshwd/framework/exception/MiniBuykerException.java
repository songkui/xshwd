package com.xshwd.framework.exception;

public class MiniBuykerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int code;

	private String msg;

	public MiniBuykerException(int code, String msg) {

		this.code = code;

		this.msg = msg;

	}

	public MiniBuykerException() {

	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
