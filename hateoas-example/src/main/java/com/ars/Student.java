package com.ars;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name = "students")
@Table(name = "students")
public class Student  extends RepresentationModel<Student> {

	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Integer id;
	  private String firstName;
	  private String lastName;
	  private String email;
	  private String overallReport;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	
	public Student(Integer id, String firstName) {
		super();
		this.id = id;
		this.firstName = firstName;
	}
	public Student() {
		super();
	}
}