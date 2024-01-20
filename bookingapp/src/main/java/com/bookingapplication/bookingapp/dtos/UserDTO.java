package com.bookingapplication.bookingapp.dtos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.bookingapplication.bookingapp.domain.UserType;

public class UserDTO {
	
	private String email;
    private String token;
    private String name;
    private String surname;
    private String address;
    private String phone;
    private UserType type;

    public UserDTO() {

    }
    
    public UserDTO(String email, String token, String name, String surname, String address, String phone, UserType type) {
    	this.email = email;
    	this.token = token;
    	this.name = name;
    	this.surname = surname;
    	this.address = address;
    	this.phone = phone;
    	this.type = type;
    }
    
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
    	list.add(new SimpleGrantedAuthority("ROLE_" + this.getType()));
    	return list;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
    
    public void copyValues(UserDTO user) {
		this.email = user.getEmail();
		this.name = user.getName();
		this.surname = user.getSurname();
		this.token = user.getToken();
		this.phone = user.getPhone();
		this.address = user.getAddress();
    }

}
