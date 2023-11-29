package com.bookingapplication.bookingapp.domain;

import java.time.LocalDate;
import java.util.List;

public class Accommodation {
	
	private String ownerEmail;
	private String name;
	private String description;
	private String location;
	private int minGuests;
	private int maxGuests;
	private AccommodationType accommodationType;
	private List<String> benefits;
	private LocalDate availabilityStart;
	private LocalDate availabilityEnd;
	private boolean isPriceByGuest;
	private double price;
	private List<String> pictures;

	public Accommodation() {

	}

	public Accommodation(String ownerEmail, String name, String description, String location, int minGuests, int maxGuests, AccommodationType accommodationType, List<String> benefits, LocalDate start, LocalDate end, boolean isPriceByGuest, double price, List<String> pictures) {
		super();
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
		this.isPriceByGuest = isPriceByGuest;
		this.price = price;
		this.pictures = pictures;
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
	
	public boolean getIsPriceByGuest() {
		return isPriceByGuest;
	}
	public void setIsPriceByGuest(Boolean isPriceByGuest) {
		this.isPriceByGuest = isPriceByGuest;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public List<String> getPictures() {
		return pictures;
	}
	public void setPictures(List<String> pictures) {
		this.pictures = pictures;
	}
	
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
		this.isPriceByGuest = accommodation.getIsPriceByGuest();
		this.price = accommodation.getPrice();
		this.pictures = accommodation.getPictures();
}

}
