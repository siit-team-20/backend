package com.bookingapplication.bookingapp.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class FavouriteAccommodation {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column
	private String guestEmail;
	@Column
    private Long accommodationId;

    public FavouriteAccommodation() {

    }
    
    public FavouriteAccommodation(Long id, String guestEmail, Long accommodationId) {
		this.id = id;
		this.guestEmail = guestEmail;
		this.accommodationId = accommodationId;
	}
    
    public void copyValues(FavouriteAccommodation favouriteAccommodation) {
    	this.guestEmail = favouriteAccommodation.getGuestEmail();
    		this.accommodationId = favouriteAccommodation.getAccommodationId();
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

}
