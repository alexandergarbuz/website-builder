package com.garbuz.web.model;

import java.io.Serializable;
import java.util.Objects;


public class ContactUsMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String name;
    private String email;
    private String phone;
    private String message;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public int hashCode() {
		return Objects.hash(email, message, name, phone);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof ContactUsMessage))
			return false;
		ContactUsMessage other = (ContactUsMessage) obj;
		return Objects.equals(email, other.email) && Objects.equals(message, other.message)
				&& Objects.equals(name, other.name) && Objects.equals(phone, other.phone);
	}
	@Override
	public String toString() {
		return "ContactUsMessage [name=" + name + ", email=" + email + ", phone=" + phone + ", message=" + message
				+ "]";
	}
}
