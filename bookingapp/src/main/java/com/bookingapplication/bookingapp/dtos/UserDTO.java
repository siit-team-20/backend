package com.bookingapplication.bookingapp.dtos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bookingapplication.bookingapp.domain.UserType;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class UserDTO implements UserDetails {
	
	private String email;
    private String token;
    private String name;
    private String surname;
    private String address;
    private String phone;
    private UserType type;
    private boolean isBlocked;

    public UserDTO() {

    }
    
    public UserDTO(String email, String token, String name, String surname, String address, String phone, UserType type, boolean isBlocked) {
    	this.email = email;
    	this.token = token;
    	this.name = name;
    	this.surname = surname;
    	this.address = address;
    	this.phone = phone;
    	this.type = type;
    	this.isBlocked = isBlocked;
    }

    @Override
    @JsonDeserialize(using = CustomAuthorityDeserializer.class)
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
    
    public boolean getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }
    
    public void copyValues(UserDTO user) {
		this.email = user.getEmail();
		this.name = user.getName();
		this.surname = user.getSurname();
		this.token = user.getToken();
		this.phone = user.getPhone();
		this.address = user.getAddress();
		this.isBlocked = user.getIsBlocked();
    }

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}
