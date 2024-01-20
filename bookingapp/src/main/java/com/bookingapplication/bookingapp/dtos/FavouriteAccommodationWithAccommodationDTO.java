package com.bookingapplication.bookingapp.dtos;

public class FavouriteAccommodationWithAccommodationDTO {
	
	private Long id;
	private String guestEmail;
    private AccommodationDTO accommodation;

    public FavouriteAccommodationWithAccommodationDTO() {

    }
    
    public FavouriteAccommodationWithAccommodationDTO(Long id, String guestEmail, AccommodationDTO accommodation) {
		this.id = id;
		this.guestEmail = guestEmail;
		this.accommodation = accommodation;
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

	public AccommodationDTO getAccommodation() {
		return accommodation;
	}

	public void setAccommodation(AccommodationDTO accommodation) {
		this.accommodation = accommodation;
	}
	
	public void copyValues(FavouriteAccommodationWithAccommodationDTO favouriteAccommodation) {
		this.accommodation = favouriteAccommodation.getAccommodation();
		this.guestEmail = favouriteAccommodation.getGuestEmail();
		
	}

}
