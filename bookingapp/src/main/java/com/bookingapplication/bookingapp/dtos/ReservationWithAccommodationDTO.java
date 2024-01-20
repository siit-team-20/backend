package com.bookingapplication.bookingapp.dtos;

import java.time.LocalDate;
import java.util.Date;

import com.bookingapplication.bookingapp.domain.ReservationStatus;

public class ReservationWithAccommodationDTO {
	
	private Long id;
    private String guestEmail;
    private AccommodationDTO accommodation;
    private LocalDate date;
    private int days;
    private int guestNumber;
    private double price;
    private ReservationStatus status;
    
    public ReservationWithAccommodationDTO() {

    }
    
    public ReservationWithAccommodationDTO(Long id, String guestEmail, AccommodationDTO accommodation, LocalDate date, int days, int guestNumber, double price, ReservationStatus status) {
    	super();
		this.id = id;
		this.guestEmail = guestEmail;
		this.accommodation = accommodation;
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

	public AccommodationDTO getAccommodation() {
		return accommodation;
	}

	public void setAccommodation(AccommodationDTO accommodation) {
		this.accommodation = accommodation;
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

	public void copyValues(ReservationWithAccommodationDTO reservationWithAccommodation) {
		this.accommodation = reservationWithAccommodation.getAccommodation();
		this.date = reservationWithAccommodation.getDate();
		this.days = reservationWithAccommodation.getDays();
		this.guestEmail = reservationWithAccommodation.getGuestEmail();
		this.guestNumber = reservationWithAccommodation.getGuestNumber();
		this.price = reservationWithAccommodation.getPrice();
		this.status = reservationWithAccommodation.getStatus();
	}

}
