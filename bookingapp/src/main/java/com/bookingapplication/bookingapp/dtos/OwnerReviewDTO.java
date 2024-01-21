package com.bookingapplication.bookingapp.dtos;

import java.time.LocalDate;

import com.bookingapplication.bookingapp.domain.Rating;

public class OwnerReviewDTO {
	
	private Long id;
	private String ownerEmail;
	private String guestEmail;
	private Rating rating;
    private String comment;
    private boolean isReported;
	private LocalDate submitDate;

    public OwnerReviewDTO() {

    }
    
    public OwnerReviewDTO(Long id, String ownerEmail, String guestEmail, Rating rating, String comment, boolean isReported, LocalDate submitDate) {
		super();
		this.id = id;
		this.ownerEmail = ownerEmail;
		this.guestEmail = guestEmail;
		this.rating = rating;
		this.comment = comment;
		this.isReported = isReported;
		this.submitDate = submitDate;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOwnerEmail() {
		return ownerEmail;
	}

	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}
	
	public String getGuestEmail() {
		return guestEmail;
	}

	public void setGuestEmail(String guestEmail) {
		this.guestEmail = guestEmail;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public boolean getIsReported() {
		return isReported;
	}

	public void setIsReported(boolean isReported) {
		this.isReported = isReported;
	}
	
	public LocalDate getSubmitDate() {
		return submitDate;
	}
	
	public void setSubmitDate(LocalDate submitDate) {
		this.submitDate = submitDate;
	}

    public void copyValues(OwnerReviewDTO ownerReview) {
    		this.ownerEmail = ownerReview.getOwnerEmail();
    		this.guestEmail = ownerReview.getGuestEmail();
    		this.rating = ownerReview.getRating();
    		this.comment = ownerReview.getComment();
    		this.isReported = ownerReview.getIsReported();
    		this.submitDate = ownerReview.getSubmitDate();
    }

}
