package com.garbuz.web.model.page;

public class ContactUsPage extends HomePage{

	private String csrfHeader;
	private String csrfToken;
	public String getCsrfHeader() {
		return csrfHeader;
	}
	public void setCsrfHeader(String csrfHeader) {
		this.csrfHeader = csrfHeader;
	}
	public String getCsrfToken() {
		return csrfToken;
	}
	public void setCsrfToken(String csrfToken) {
		this.csrfToken = csrfToken;
	}
	@Override
	public String toString() {
		return "ContactUsPage [csrfHeader=" + csrfHeader + ", csrfToken=" + csrfToken + ", getTitle()=" + getTitle() + "]";
	}
	
}
