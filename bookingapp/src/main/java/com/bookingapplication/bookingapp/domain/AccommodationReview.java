package com.bookingapplication.bookingapp.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class AccommodationReview {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
    @Column
	private String guestEmail;
    @Column
	private Long accommodationId;
    @Column
	private String comment;
    @Column
	private Rating rating;
    @Column
	private boolean isApproved;
	@Column
	private LocalDate submitDate;
	
	public AccommodationReview() {

	}

	public AccommodationReview(Long id, String guestEmail, Long accommodationId, String comment, Rating rating, boolean isApproved, LocalDate submitDate) {
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
	
	public void copyValues(AccommodationReview accommodationReview) {
		this.guestEmail = accommodationReview.getGuestEmail();
		this.accommodationId = accommodationReview.getAccommodationId();
		this.comment = accommodationReview.getComment();
		this.rating = accommodationReview.getRating();
		this.isApproved = accommodationReview.getIsApproved();
		this.submitDate = accommodationReview.getSubmitDate();
	}

}
