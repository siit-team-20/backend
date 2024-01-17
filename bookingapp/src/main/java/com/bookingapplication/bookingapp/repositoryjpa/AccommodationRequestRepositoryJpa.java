package com.bookingapplication.bookingapp.repositoryjpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookingapplication.bookingapp.domain.AccommodationRequest;

public interface AccommodationRequestRepositoryJpa extends JpaRepository<AccommodationRequest, Long> {

}
