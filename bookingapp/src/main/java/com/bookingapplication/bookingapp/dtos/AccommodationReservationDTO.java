package com.bookingapplication.bookingapp.dtos;

import java.time.LocalDate;
import java.util.Date;

import com.bookingapplication.bookingapp.domain.AccommodationReservation;
import com.bookingapplication.bookingapp.domain.ReservationStatus;

public class AccommodationReservationDTO {

	private Long id;
    private String guestEmail;
    private Long accommodationId;
    private LocalDate date;
    private int days;
    private int guestNumber;
    private double price;
    private ReservationStatus status;
    
    public AccommodationReservationDTO() {

    }
    
    public AccommodationReservationDTO(Long id, String guestEmail, Long accommodationId, LocalDate date, int days, int guestNumber, double price, ReservationStatus status) {
    	super();
		this.id = id;
		this.guestEmail = guestEmail;
		this.accommodationId = accommodationId;
		this.date = date;
		this.days = days;
		this.guestNumber = guestNumber;
		this.price = price;
		this.status = status;
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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}
	public int getGuestNumber() {
		return guestNumber;
	}

	public void setGuestNumber(int guestNumber) {
		this.guestNumber = guestNumber;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public ReservationStatus getStatus() {
		return status;
	}
	public void setStatus(ReservationStatus status) {
		this.status = status;
	}

	public void copyValues(AccommodationReservationDTO accommodationReservation) {
		this.accommodationId = accommodationReservation.getAccommodationId();
		this.date = accommodationReservation.getDate();
		this.days = accommodationReservation.getDays();
		this.guestEmail = accommodationReservation.getGuestEmail();
		this.guestNumber = accommodationReservation.getGuestNumber();
		this.price = accommodationReservation.getPrice();
		this.status = accommodationReservation.getStatus();
	}
}
