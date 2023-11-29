package com.bookingapplication.bookingapp.domain;

public class FavouriteAccommodation {

	private Long guestid;
    private Long accommodationId;

    public FavouriteAccommodation() {

    }
    
    public void copyValues(FavouriteAccommodation favouriteAccommodation) {
    		this.accommodationId = favouriteAccommodation.getAccommodationId();
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
