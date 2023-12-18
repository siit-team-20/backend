package com.bookingapplication.bookingapp.dtos;

public class FavouriteAccommodationDTO {

	private Long id;
	private Long guestid;
    private Long accommodationId;

    public FavouriteAccommodationDTO() {

    }
    
    public void copyValues(FavouriteAccommodationDTO favouriteAccommodation) {
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
