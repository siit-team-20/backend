package com.bookingapplication.bookingapp.domain;

public class FavouriteAccommodation {

	private Long id;
	private Long guestid;
    private Long accommodationId;

    public FavouriteAccommodation() {

    }
    
    public void copyValues(FavouriteAccommodation favouriteAccommodation) {
    		this.accommodationId = favouriteAccommodation.getAccommodationId();
    }
    
    public Long getId() {
    	return id;
    }
    
    public void setId(Long id) {
    	this.id = id;
    }

	public Long getGuestid() {
		return guestid;
	}

	public void setGuestid(Long guestid) {
		this.guestid = guestid;
	}

	public Long getAccommodationId() {
		return accommodationId;
	}

	public void setAccommodationId(Long accommodationId) {
		this.accommodationId = accommodationId;
	}

}
