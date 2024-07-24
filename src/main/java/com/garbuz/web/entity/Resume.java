package com.garbuz.web.entity;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Resume")
/**
 * When you have JPA / Hibernate Entities with @Entity annotation, and when you
 * fetch data from the database using a repository or using getMethod() from the
 * parent entity for the field which is being lazy-loaded from the parent
 * entity, hibernate returns an object which will have all the fields/properties
 * of the class which are mapped to DB table. On top of these fields, this
 * object will also have two extra fields which are hibernateLazyInitializer and
 * handler that is used to lazily load an entity. If you have any use case of
 * serializing this entity in JSON String format using Jackson library directly
 * or indirectly (Maybe if you're returning entity as it to any REST API
 * response or if you're storing entity to JSON data store like Elasticsearch),
 * the JPA entity will be serialized with all the fields and
 * hibernateLazyInitializer and handler as extra fields. So, if you do not
 * ignore these fields, they will be serialized in JSON format which you can see
 * if you read the JSON string. So, to avoid this unnecessary serialization, you
 * have to write this piece of code on JPA / Hibernate entity which will tell
 * Jackson library that "Serialized JSON should not have fields
 * hibernateLazyInitializer and handler. If you find them in object, just ignore
 * them":
 * 
 * https://stackoverflow.com/questions/67353793/what-does-jsonignorepropertieshibernatelazyinitializer-handler-do
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Resume {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "objective", length = 500)
	private String objective;
	@Column(name = "summary", length = 1000)
	private String summary;
	@Column(name = "email")
	private String email;
	@Column(name = "phone")
	private String phone;
	@Column(name = "address")
	private String address;
	@Column(name = "city")
	private String city;
	@Column(name = "state", length = 2)
	private String state;
	@Column(name = "zip", length = 5)
	private String zip;


	public Resume() {
	}

	public Resume(String firstName, String lastName, String objective, String summary, String email, String phone,
			String address, String city, String state, String zip) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.objective = objective;
		this.summary = summary;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getObjective() {
		return objective;
	}

	public void setObjective(String objective) {
		this.objective = objective;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, city, email, firstName, id, lastName, objective, phone, state, summary, zip);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Resume other = (Resume) obj;
		return Objects.equals(address, other.address) && Objects.equals(city, other.city)
				&& Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(id, other.id) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(objective, other.objective) && Objects.equals(phone, other.phone)
				&& Objects.equals(state, other.state) && Objects.equals(summary, other.summary)
				&& Objects.equals(zip, other.zip);
	}

	@Override
	public String toString() {
		return "Resume [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", objective=" + objective
				+ ", summary=" + summary + ", email=" + email + ", phone=" + phone + ", address=" + address + ", city="
				+ city + ", state=" + state + ", zip=" + zip + "]";
	}

}
