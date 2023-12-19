package com.bookingapplication.bookingapp.dtos;

public class AccommodationRequestDTO {

	public Long id;
	public AccommodationDTO oldAccommodation;
	public AccommodationDTO newAccommodation;
	public AccommodationRequestType type;
	
	public AccommodationRequestDTO(Long id, AccommodationDTO oldAccommodation, AccommodationDTO newAccommodation, AccommodationRequestType type) {
		super();
		this.id = id;
		this.oldAccommodation = oldAccommodation;
		this.newAccommodation = newAccommodation;
		this.type = type;
	}
	
	public void copyValues(AccommodationRequestDTO accommodationRequest) {
		this.oldAccommodation = accommodationRequest.oldAccommodation;
		this.newAccommodation = accommodationRequest.newAccommodation;
		this.type = accommodationRequest.type;
	}
	
}
