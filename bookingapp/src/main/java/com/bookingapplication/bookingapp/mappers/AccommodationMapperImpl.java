package com.bookingapplication.bookingapp.mappers;

import java.util.ArrayList;
import java.util.List;

import com.bookingapplication.bookingapp.domain.Accommodation;
import com.bookingapplication.bookingapp.dtos.AccommodationDTO;

public class AccommodationMapperImpl implements AccommodationMapper {

    @Override
    public Accommodation toAccommodation(AccommodationDTO accommodationDTO) {
        if ( accommodationDTO == null ) {
            return null;
        }

        Accommodation accommodation = new Accommodation();

        accommodation.setId( accommodationDTO.getId() );
        accommodation.setOwnerEmail( accommodationDTO.getOwnerEmail() );
        accommodation.setName( accommodationDTO.getName() );
        accommodation.setDescription( accommodationDTO.getDescription() );
        accommodation.setLocation( accommodationDTO.getLocation() );
        accommodation.setMinGuests( accommodationDTO.getMinGuests() );
        accommodation.setMaxGuests( accommodationDTO.getMaxGuests() );
        accommodation.setAccommodationType( accommodationDTO.getAccommodationType() );
        List<String> list = accommodationDTO.getBenefits();
        if ( list != null ) {
            accommodation.setBenefits( new ArrayList<String>( list ) );
        }
        accommodation.setAvailabilityStart( accommodationDTO.getAvailabilityStart() );
        accommodation.setAvailabilityEnd( accommodationDTO.getAvailabilityEnd() );
        accommodation.setIsApproved( accommodationDTO.getIsApproved() );
        accommodation.setIsPriceByGuest( accommodationDTO.getIsPriceByGuest() );
        accommodation.setIsAutomaticAcceptance(accommodationDTO.getIsAutomaticAcceptance());
        accommodation.setPrice( accommodationDTO.getPrice() );
        accommodation.setReservationCancellationDeadline( accommodationDTO.getReservationCancellationDeadline() );

        return accommodation;
    }

    @Override
    public AccommodationDTO toAccommodationDTO(Accommodation accommodation) {
        if ( accommodation == null ) {
            return null;
        }

        AccommodationDTO accommodationDTO = new AccommodationDTO();

        accommodationDTO.setId( accommodation.getId() );
        accommodationDTO.setOwnerEmail( accommodation.getOwnerEmail() );
        accommodationDTO.setName( accommodation.getName() );
        accommodationDTO.setDescription( accommodation.getDescription() );
        accommodationDTO.setLocation( accommodation.getLocation() );
        accommodationDTO.setMinGuests( accommodation.getMinGuests() );
        accommodationDTO.setMaxGuests( accommodation.getMaxGuests() );
        accommodationDTO.setAccommodationType( accommodation.getAccommodationType() );
        List<String> list = accommodation.getBenefits();
        if ( list != null ) {
            accommodationDTO.setBenefits( new ArrayList<String>( list ) );
        }
        accommodationDTO.setAvailabilityStart( accommodation.getAvailabilityStart() );
        accommodationDTO.setAvailabilityEnd( accommodation.getAvailabilityEnd() );
        accommodationDTO.setIsApproved( accommodation.getIsApproved() );
        accommodationDTO.setIsPriceByGuest( accommodation.getIsPriceByGuest() );
        accommodationDTO.setIsAutomaticAcceptance(accommodation.getIsAutomaticAcceptance());
        accommodationDTO.setPrice( accommodation.getPrice() );
        accommodationDTO.setReservationCancellationDeadline( accommodation.getReservationCancellationDeadline() );

        return accommodationDTO;
    }

    @Override
    public List<AccommodationDTO> toAccommodationDtos(List<Accommodation> accommodations) {
        if ( accommodations == null ) {
            return null;
        }

        List<AccommodationDTO> list = new ArrayList<AccommodationDTO>( accommodations.size() );
        for ( Accommodation accommodation1 : accommodations ) {
            list.add( toAccommodationDTO( accommodation1 ) );
        }

        return list;
    }

    @Override
    public void updateAccommodation(Accommodation target, Accommodation source) {
        if ( source == null ) {
            return;
        }

        target.setId( source.getId() );
        target.setOwnerEmail( source.getOwnerEmail() );
        target.setName( source.getName() );
        target.setDescription( source.getDescription() );
        target.setLocation( source.getLocation() );
        target.setMinGuests( source.getMinGuests() );
        target.setMaxGuests( source.getMaxGuests() );
        target.setAccommodationType( source.getAccommodationType() );
        if ( target.getBenefits() != null ) {
            List<String> list = source.getBenefits();
            if ( list != null ) {
                target.getBenefits().clear();
                target.getBenefits().addAll( list );
            }
            else {
                target.setBenefits( null );
            }
        }
        else {
            List<String> list = source.getBenefits();
            if ( list != null ) {
                target.setBenefits( new ArrayList<String>( list ) );
            }
        }
        target.setAvailabilityStart( source.getAvailabilityStart() );
        target.setAvailabilityEnd( source.getAvailabilityEnd() );
        target.setIsApproved( source.getIsApproved() );
        target.setIsPriceByGuest( source.getIsPriceByGuest() );
        target.setIsAutomaticAcceptance(source.getIsAutomaticAcceptance());
        target.setPrice( source.getPrice() );
        target.setReservationCancellationDeadline( source.getReservationCancellationDeadline() );
    }
}

