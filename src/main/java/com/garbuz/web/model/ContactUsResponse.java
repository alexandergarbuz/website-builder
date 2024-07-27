package com.garbuz.web.model;

import java.util.ArrayList;
import java.util.List;

public class ContactUsResponse {
	public static final String SUCCESS = "Success";
	public static final String ERROR = "ERROR";
	
	private List<String> errors = new ArrayList<>();
	private String status = SUCCESS;
	
	public ContactUsResponse() {
	}
	public ContactUsResponse(List<String> errors, String status) {
		this.errors = errors;
		this.status = status;
	}
	public List<String> getErrors() {
		return errors;
	}
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ContactUsResponse [errors=" + errors + ", status=" + status + "]";
	}
	
}
