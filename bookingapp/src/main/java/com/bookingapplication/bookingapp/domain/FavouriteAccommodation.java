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
	private Long guestid;
	@Column
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
