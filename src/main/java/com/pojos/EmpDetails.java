package com.pojos;

public class EmpDetails {

	private int id;
	private String email;
	private String firstname;
	private String lastnmae;
	private String avater;
	

	
	
	public int getId() {
		return id;
	}
	public String getEmail() {
		return email;
	}
	public String getFirstname() {
		return firstname;
	}
	public String getLastnmae() {
		return lastnmae;
	}
	public String getAvater() {
		return avater;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public void setLastnmae(String lastnmae) {
		this.lastnmae = lastnmae;
	}
	public void setAvater(String avater) {
		this.avater = avater;
	}
	
	@Override
	public String toString() {
		return "EmpDetails [id=" + id + ", email=" + email + ", firstname=" + firstname + ", lastnmae=" + lastnmae
				+ ", avater=" + avater + "]";
	}
	
	
}
