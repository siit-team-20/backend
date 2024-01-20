package com.bookingapplication.bookingapp.domain;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class AccommodationReservation {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
    @Column
    private String guestEmail;
    @Column
    private Long accommodationId;
    @Column
    private LocalDate date;
    @Column
    private int days;
    @Column
    private int guestNumber;
    @Column
    private double price;
    @Column
    private ReservationStatus status;
    
    public AccommodationReservation() {

    }
    
    public AccommodationReservation(Long id, String guestEmail, Long accommodationId, LocalDate date, int days, int guestNumber, double price, ReservationStatus status) {
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

	public void copyValues(AccommodationReservation accommodationReservation) {
		this.accommodationId = accommodationReservation.getAccommodationId();
		this.date = accommodationReservation.getDate();
		this.days = accommodationReservation.getDays();
		this.guestEmail = accommodationReservation.getGuestEmail();
		this.guestNumber = accommodationReservation.getGuestNumber();
		this.price = accommodationReservation.getPrice();
		this.status = accommodationReservation.getStatus();
	}

	

}
