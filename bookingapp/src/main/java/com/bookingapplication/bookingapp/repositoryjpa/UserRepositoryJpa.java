package com.bookingapplication.bookingapp.repositoryjpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookingapplication.bookingapp.domain.User;

public interface UserRepositoryJpa extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);
}
