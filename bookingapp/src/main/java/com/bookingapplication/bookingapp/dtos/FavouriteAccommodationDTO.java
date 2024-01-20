package com.bookingapplication.bookingapp.dtos;

public class FavouriteAccommodationDTO {

	private Long id;
	private String guestEmail;
    private Long accommodationId;

    public FavouriteAccommodationDTO() {

    }
    
    public FavouriteAccommodationDTO(Long id, String guestEmail, Long accommodationId) {
		this.id = id;
		this.guestEmail = guestEmail;
		this.accommodationId = accommodationId;
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

	public void setGuestEmail(String guestEmail) {
		this.guestEmail = guestEmail;
	}

	public Long getAccommodationId() {
		return accommodationId;
	}

	public void setAccommodationId(Long accommodationId) {
		this.accommodationId = accommodationId;
	}
	
	public void copyValues(FavouriteAccommodationDTO favouriteAccommodation) {
		this.accommodationId = favouriteAccommodation.getAccommodationId();
		this.guestEmail = favouriteAccommodation.getGuestEmail();
		
	}
	
}
