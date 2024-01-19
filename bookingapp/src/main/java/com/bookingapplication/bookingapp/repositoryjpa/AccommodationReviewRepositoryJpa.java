package com.bookingapplication.bookingapp.repositoryjpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookingapplication.bookingapp.domain.AccommodationReview;

public interface AccommodationReviewRepositoryJpa extends JpaRepository<AccommodationReview, Long> {

}
