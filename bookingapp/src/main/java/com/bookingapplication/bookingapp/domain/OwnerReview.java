package com.bookingapplication.bookingapp.domain;

public class OwnerReview {

	private Long ownerId;
	private Long guestId;
	private Rating rating;
    private String comment;

    public OwnerReview() {

    }

    public void copyValues(OwnerReview ownerReview) {
    		this.rating = ownerReview.getRating();
    		this.comment = ownerReview.getComment();
    }

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
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

	public Long getGuestId() {
		return guestId;
	}

	public void setGuestId(Long guestId) {
		this.guestId = guestId;
	}

}
