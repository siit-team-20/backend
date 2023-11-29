package com.bookingapplication.bookingapp.domain;

public class OwnerReview {

	private Long id;
	private String ownerEmail;
	private String guestEmail;
	private Rating rating;
    private String comment;

    public OwnerReview() {

    }
    
    public OwnerReview(Long id, String ownerEmail, String guestEmail, Rating rating, String comment) {
		super();
		this.id = id;
		this.ownerEmail = ownerEmail;
		this.guestEmail = guestEmail;
		this.rating = rating;
		this.comment = comment;
	}

    public void copyValues(OwnerReview ownerReview) {
    		this.ownerEmail = ownerReview.getOwnerEmail();
    		this.guestEmail = ownerReview.getGuestEmail();
    		this.rating = ownerReview.getRating();
    		this.comment = ownerReview.getComment();
    }

	public Long getId() {
		return id;
	}

	public void setGuestId(Long id) {
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

}
