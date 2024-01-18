package com.bookingapplication.bookingapp.repositoryjpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bookingapplication.bookingapp.domain.DateRange;

import jakarta.transaction.Transactional;

public interface DateRangeRepositoryJpa extends JpaRepository<DateRange, Long> {

	@Modifying
    @Transactional
    @Query(value = "delete from date_range d where d.accommodation_id = ?1", nativeQuery = true)
    void deleteByAccommodationId(Long accommodationId);
	
}
