package com.bookingapplication.bookingapp.repositoryjpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bookingapplication.bookingapp.domain.FavouriteAccommodation;

import jakarta.transaction.Transactional;

public interface FavouriteAccommodationRepositoryJpa extends JpaRepository<FavouriteAccommodation, Long> {

	@Modifying
    @Transactional
    @Query(value = "delete from favourite_accommodation f where f.accommodation_id = ?1", nativeQuery = true)
    void deleteByAccommodationId(Long accommodationId);
	
}
