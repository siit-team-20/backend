package com.bookingapplication.bookingapp.domain;

import java.util.Date;

public class AccommodationReservation {

	private Long id;
    private Long guestId;
    private Long accommodationId;
    private Date date;
    private int days;
    
    

    public AccommodationReservation() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public Long getGuestId() {
		return guestId;
	}

	public void setGuestId(Long guestId) {
		this.guestId = guestId;
	}

	public Long getAccommodationId() {
		return accommodationId;
	}

	public void setAccommodationId(Long accommodationId) {
		this.accommodationId = accommodationId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public void copyValues(AccommodationReservation accommodationReservation) {
		this.accommodationId = accommodationReservation.getAccommodationId();
		this.date = accommodationReservation.getDate();
		this.days = accommodationReservation.getDays();
		this.guestId = accommodationReservation.getGuestId();
}

}
