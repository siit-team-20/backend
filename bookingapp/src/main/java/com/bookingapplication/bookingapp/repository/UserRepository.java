package com.bookingapplication.bookingapp.repository;

import java.util.Collection;

import com.bookingapplication.bookingapp.domain.User;

public interface UserRepository {

	Collection<User> findAll();

	User create(User user);

	User findOne(Long id);
	
	User update(User user);

	void delete(Long id);
	
}
