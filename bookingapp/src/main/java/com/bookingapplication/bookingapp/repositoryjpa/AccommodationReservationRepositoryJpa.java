package com.bookingapplication.bookingapp.repositoryjpa;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bookingapplication.bookingapp.domain.AccommodationReservation;

public interface AccommodationReservationRepositoryJpa extends JpaRepository<AccommodationReservation, Long> {

}
