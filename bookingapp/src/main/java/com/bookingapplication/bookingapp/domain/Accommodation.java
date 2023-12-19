package com.bookingapplication.bookingapp.domain;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
public class Accommodation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
    @Column
	private String ownerEmail;
    @Column
	private String name;
    @Column
	private String description;
    @Column
	private String location;
    @Column
	private int minGuests;
    @Column
	private int maxGuests;
    @Column
	private AccommodationType accommodationType;
    @Column
	private List<String> benefits;
    @Column
	private LocalDate availabilityStart;
    @Column
	private LocalDate availabilityEnd;
    @Column
	private boolean isApproved;
    @Column
	private boolean isPriceByGuest;
    @Column
	private double price;
    @Column
	private int reservationCancellationDeadline;
	//private List<String> pictures;

	public Accommodation() {

	}

	public Accommodation(Long id, String ownerEmail, String name, String description, String location, int minGuests, int maxGuests, AccommodationType accommodationType, List<String> benefits, LocalDate start, LocalDate end, boolean isApproved, boolean isPriceByGuest, double price, int reservationCancellationDeadline) {
		super();
		this.id = id;
		this.ownerEmail = ownerEmail;
		this.name = name;
		this.description = description;
		this.location = location;
		this.minGuests = minGuests;
		this.maxGuests = maxGuests;
		this.accommodationType = accommodationType;
		this.benefits = benefits;
		this.availabilityStart = start;
		this.availabilityEnd = end;
		this.isApproved = isApproved;
		this.isPriceByGuest = isPriceByGuest;
		this.reservationCancellationDeadline = reservationCancellationDeadline;
		this.price = price;
		//this.pictures = pictures;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getOwnerEmail() {
		return ownerEmail;
	}
	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public int getMinGuests() {
		return minGuests;
	}
	public void setMinGuests(int minGuests) {
		this.minGuests = minGuests;
	}
	
	public int getMaxGuests() {
		return maxGuests;
	}
	public void setMaxGuests(int maxGuests) {
		this.maxGuests = maxGuests;
	}
	
	public AccommodationType getAccommodationType() {
		return accommodationType;
	}
	public void setAccommodationType(AccommodationType accommodationType) {
		this.accommodationType = accommodationType;
	}
	
	public List<String> getBenefits() {
		return benefits;
	}
	public void setBenefits(List<String> benefits) {
		this.benefits = benefits;
	}
	
	public LocalDate getAvailabilityStart() {
		return availabilityStart;
	}
	public void setAvailabilityStart(LocalDate availabilityStart) {
		this.availabilityStart = availabilityStart;
	}
	
	public LocalDate getAvailabilityEnd() {
		return availabilityEnd;
	}
	public void setAvailabilityEnd(LocalDate availabilityEnd) {
		this.availabilityEnd = availabilityEnd;
	}
	
	public boolean getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
	
	public boolean getIsPriceByGuest() {
		return isPriceByGuest;
	}
	public void setIsPriceByGuest(boolean isPriceByGuest) {
		this.isPriceByGuest = isPriceByGuest;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getReservationCancellationDeadline() {
		return reservationCancellationDeadline;
	}
	public void setReservationCancellationDeadline(int reservationCancellationDeadline) {
		this.reservationCancellationDeadline = reservationCancellationDeadline;
	}
	/*
	public List<String> getPictures() {
		return pictures;
	}
	public void setPictures(List<String> pictures) {
		this.pictures = pictures;
	}*/
	
	public void copyValues(Accommodation accommodation) {
		this.ownerEmail = accommodation.getOwnerEmail();
		this.name = accommodation.getName();
		this.description = accommodation.getDescription();
		this.location = accommodation.getLocation();
		this.minGuests = accommodation.getMinGuests();
		this.maxGuests = accommodation.getMaxGuests();
		this.accommodationType = accommodation.getAccommodationType();
		this.benefits = accommodation.getBenefits();
		this.availabilityStart = accommodation.getAvailabilityStart();
		this.availabilityEnd = accommodation.getAvailabilityEnd();
		this.isApproved = accommodation.getIsApproved();
		this.isPriceByGuest = accommodation.getIsPriceByGuest();
		this.price = accommodation.getPrice();
		this.reservationCancellationDeadline = accommodation.getReservationCancellationDeadline();
		//this.pictures = accommodation.getPictures();
	}

}
