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
public class OwnerReview {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
    @Column
	private String ownerEmail;
    @Column
	private String guestEmail;
    @Column
	private Rating rating;
    @Column
    private String comment;
    @Column
    private boolean isReported;
	@Column
	private LocalDate submitDate;

    public OwnerReview() {

    }
    
    public OwnerReview(Long id, String ownerEmail, String guestEmail, Rating rating, String comment, boolean isReported, LocalDate submitDate) {
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

    public void copyValues(OwnerReview ownerReview) {
    		this.ownerEmail = ownerReview.getOwnerEmail();
    		this.guestEmail = ownerReview.getGuestEmail();
    		this.rating = ownerReview.getRating();
    		this.comment = ownerReview.getComment();
    		this.isReported = ownerReview.getIsReported();
    		this.submitDate = ownerReview.getSubmitDate();
    }
}
