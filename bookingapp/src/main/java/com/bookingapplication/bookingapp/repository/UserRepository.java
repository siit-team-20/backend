package com.bookingapplication.bookingapp.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookingapplication.bookingapp.domain.User;
import com.bookingapplication.bookingapp.domain.UserType;

public interface UserRepository extends JpaRepository<User, Long> {

	//Collection<User> findAll();
	List<User> findAll();
	
	// isto kao findOne ??
	Optional<User> findByLogin(String login);

	User create(User user);

	User findOne(String email);
	
	User findByType(UserType type);
	
	User update(User user);

	void delete(String email);
	
}
