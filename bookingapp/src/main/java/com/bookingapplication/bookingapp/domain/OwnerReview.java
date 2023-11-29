package com.bookingapplication.bookingapp.domain;

public class OwnerReview {

<<<<<<< HEAD
	private Long id;
	private String ownerEmail;
	private String guestEmail;
=======
	private Long ownerId;
	private Long guestId;
>>>>>>> 34dd06141f656fd943b96aa48bfc363bcac2f29f
	private Rating rating;
    private String comment;

    public OwnerReview() {

    }
<<<<<<< HEAD
    
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
=======

    public void copyValues(OwnerReview ownerReview) {
>>>>>>> 34dd06141f656fd943b96aa48bfc363bcac2f29f
    		this.rating = ownerReview.getRating();
    		this.comment = ownerReview.getComment();
    }

<<<<<<< HEAD
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
=======
	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
>>>>>>> 34dd06141f656fd943b96aa48bfc363bcac2f29f
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

<<<<<<< HEAD
=======
	public Long getGuestId() {
		return guestId;
	}

	public void setGuestId(Long guestId) {
		this.guestId = guestId;
	}

>>>>>>> 34dd06141f656fd943b96aa48bfc363bcac2f29f
}
