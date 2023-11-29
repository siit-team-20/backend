package com.bookingapplication.bookingapp.domain;


public class AccommodationReview {
	
	private Long id;
	private String guestEmail;
	private Long accommodationId;
	private String comment;
	private Rating rating;
	private boolean isApproved;
	
	public AccommodationReview() {

	}

	public AccommodationReview(Long id, String guestEmail, Long accommodationId, String comment, Rating rating, boolean isApproved) {
		super();
		this.id = id;
		this.guestEmail = guestEmail;
		this.accommodationId = accommodationId;
		this.comment = comment;
		this.rating = rating;
		this.isApproved = isApproved;
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
	
	public void copyValues(AccommodationReview accommodationReview) {
		this.guestEmail = accommodationReview.getGuestEmail();
		this.accommodationId = accommodationReview.getAccommodationId();
		this.comment = accommodationReview.getComment();
		this.rating = accommodationReview.getRating();
		this.isApproved = accommodationReview.getIsApproved();
	}

}
