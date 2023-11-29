package com.bookingapplication.bookingapp.domain;

public class User {

	private String email;
    private String password;
    private String name;
    private String surname;
    private String address;
    private String phone;
    private UserType type;

    public User() {

    }
    
    public User(String email, String password, String name, String surname, String address, String phone, UserType type) {
    	this.email = email;
    	this.password = password;
    	this.name = name;
    	this.surname = surname;
    	this.address = address;
    	this.phone = phone;
    	this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmil(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setType(UserType type) {
    	this.type = type;
    }
    
    public UserType geType() {
    	return type;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
	
}
