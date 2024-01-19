package com.bookingapplication.bookingapp.repositoryjpa;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bookingapplication.bookingapp.domain.OwnerReview;

public interface OwnerReviewRepositoryJpa extends JpaRepository<OwnerReview, Long> {

}
