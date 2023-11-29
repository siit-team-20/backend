package com.bookingapplication.bookingapp.service;

import java.util.Collection;

import com.bookingapplication.bookingapp.domain.User;
import com.bookingapplication.bookingapp.domain.UserType;

public interface UserService {
	
	Collection<User> findAll();

	User findOne(String email);
	
	Collection<User> findByType(UserType type);

	User create(User user) throws Exception;

	User update(User user) throws Exception;

	void delete(String email);

}
