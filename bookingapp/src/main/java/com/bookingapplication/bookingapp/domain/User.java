package com.bookingapplication.bookingapp.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "app_user")
public class User {
	
	/*
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	*/
    @Id
	private String email;
    @Column
    private String password;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String address;
    @Column
    private String phone;
    @Column
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

    public void setEmail(String email) {
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
    
    public UserType getType() {
    	return type;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public void copyValues(User user) {
		this.email = user.getEmail();
		this.name = user.getName();
		this.surname = user.getSurname();
		this.password = user.getPassword();
		this.phone = user.getPhone();
		this.address = user.getAddress();
    }
	
}
