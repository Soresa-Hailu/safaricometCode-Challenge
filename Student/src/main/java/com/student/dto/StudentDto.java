package com.student.dto;


import java.io.Serializable;
import java.util.List;

public class StudentDto implements Serializable {
	
	private static final long serialVersionUID = -3678285051170734391L;
	private long id;
	private String studentId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String userId) {
		this.studentId = userId;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
