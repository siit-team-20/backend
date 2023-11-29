package com.bookingapplication.bookingapp.repository;

import java.util.Collection;

import com.bookingapplication.bookingapp.domain.User;
import com.bookingapplication.bookingapp.domain.UserType;

public interface UserRepository {

	Collection<User> findAll();

	User create(User user);

	User findOne(String email);
	
	User findByType(UserType type);
	
	User update(User user);

	void delete(String email);
	
}
