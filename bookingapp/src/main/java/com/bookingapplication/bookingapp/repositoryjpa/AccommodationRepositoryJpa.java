package com.bookingapplication.bookingapp.repositoryjpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookingapplication.bookingapp.domain.Accommodation;

public interface AccommodationRepositoryJpa extends JpaRepository<Accommodation, Long> {

}
