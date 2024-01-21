package com.bookingapplication.bookingapp.dtos;
import java.time.LocalDate;

import com.bookingapplication.bookingapp.domain.AccommodationReview;
import com.bookingapplication.bookingapp.domain.Rating;

public class AccommodationReviewDTO {
	
	private Long id;
	private String guestEmail;
	private Long accommodationId;
	private String comment;
	private Rating rating;
	private boolean isApproved;
	private LocalDate submitDate;
	
	public AccommodationReviewDTO() {

	}

	public AccommodationReviewDTO(Long id, String guestEmail, Long accommodationId, String comment, Rating rating, boolean isApproved, LocalDate submitDate) {
		super();
		this.id = id;
		this.guestEmail = guestEmail;
		this.accommodationId = accommodationId;
		this.comment = comment;
		this.rating = rating;
		this.isApproved = isApproved;
		this.submitDate = submitDate;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getGuestEmail() {
		return guestEmail;
	}
	public void setGuestEmail(String email) {
		this.guestEmail = email;
	}
	
	public Long getAccommodationId() {
		return accommodationId;
	}
	public void setAccommodationId(Long accommodationId) {
		this.accommodationId = accommodationId;
	}
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public Rating getRating() {
		return rating;
	}
	public void setRating(Rating rating) {
		this.rating = rating;
	}
	
	public boolean getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
	
	public LocalDate getSubmitDate() {
		return submitDate;
	}
	
	public void setSubmitDate(LocalDate submitDate) {
		this.submitDate = submitDate;
	}
	
	public void copyValues(AccommodationReviewDTO accommodationReview) {
		this.guestEmail = accommodationReview.getGuestEmail();
		this.accommodationId = accommodationReview.getAccommodationId();
		this.comment = accommodationReview.getComment();
		this.rating = accommodationReview.getRating();
		this.isApproved = accommodationReview.getIsApproved();
		this.submitDate = accommodationReview.getSubmitDate();
	}

}
