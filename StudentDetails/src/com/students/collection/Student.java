package com.students.collection;

public class Student {
	
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	
	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}

	public Student(int Id,String first_name, String last_name, String Email){
		this.id = Id;
		this.firstName = first_name;
		this.lastName = last_name;
		this.email = Email;
		
	}
	
	public Student(String first_name, String last_name, String Email){
		
		this.firstName = first_name;
		this.lastName = last_name;
		this.email = Email;	
	}
	
	
}
