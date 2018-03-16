package com.ingesup.java.carhibou.models;

public class ApiResponse {
	private CustomError error;
	private Object result;
	
	public ApiResponse(CustomError error, Object result) {
		this.error = error;
		this.result = result;
	}
	
	public ApiResponse() {
		this.error = null;
		this.result = null;
	}

	public CustomError getError() {
		return error;
	}

	public void setError(CustomError error) {
		this.error = error;
	}
	
	public void setError(String error) {
		this.error = new CustomError(error);
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
	
	
}
