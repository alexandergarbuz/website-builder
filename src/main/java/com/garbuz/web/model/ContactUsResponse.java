package com.garbuz.web.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
