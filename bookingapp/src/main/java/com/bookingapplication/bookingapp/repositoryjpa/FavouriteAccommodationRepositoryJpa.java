package com.bookingapplication.bookingapp.repositoryjpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookingapplication.bookingapp.domain.FavouriteAccommodation;

public interface FavouriteAccommodationRepositoryJpa extends JpaRepository<FavouriteAccommodation, Long> {


}
