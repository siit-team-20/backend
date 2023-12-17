package com.bookingapplication.bookingapp.dtos;

import com.bookingapplication.bookingapp.domain.Accommodation;

public class AccommodationRequestDTO {

	public Long id;
	public Accommodation oldAccommodation;
	public Accommodation newAccommodation;
	public AccommodationRequestType type;
	
	public AccommodationRequestDTO(Long id, Accommodation oldAccommodation, Accommodation newAccommodation, AccommodationRequestType type) {
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
