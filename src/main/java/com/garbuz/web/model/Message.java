package com.garbuz.web.model;

import java.io.Serializable;
import java.util.Objects;


public class Message implements Serializable {
    private static final long serialVersionUID = 1L;

	private String toAddress;
    private String subject;
    private String body;
	

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String text) {
		this.body = text;
	}

	@Override
	public int hashCode() {
		return Objects.hash(body, subject, toAddress);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		return Objects.equals(body, other.body) && Objects.equals(subject, other.subject)
				&& Objects.equals(toAddress, other.toAddress);
	}

	@Override
	public String toString() {
		return "Message [toAddress=" + toAddress + ", subject=" + subject + ", body=" + body + "]";
	}
}
