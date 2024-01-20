package com.bookingapplication.bookingapp.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.bookingapplication.bookingapp.domain.AccommodationReview;
import com.bookingapplication.bookingapp.dtos.AccommodationReviewDTO;
import com.bookingapplication.bookingapp.exceptions.AppException;
import com.bookingapplication.bookingapp.repositoryjpa.AccommodationReviewRepositoryJpa;
import com.bookingapplication.bookingapp.service.AccommodationReviewService;

@Service
public class AccommodationReviewServiceImpl implements AccommodationReviewService {

	@Autowired
	private AccommodationReviewRepositoryJpa accommodationReviewRepositoryJpa;
	
	public AccommodationReviewServiceImpl() {
	}
	
	@Override
	public Collection<AccommodationReviewDTO> findAll() {
		return toAccommodationReviewDtos(accommodationReviewRepositoryJpa.findAll());
	}

	@Override
	public Collection<AccommodationReviewDTO> findAll(String guestEmail) {
		return toAccommodationReviewDtos(accommodationReviewRepositoryJpa.findAll().stream().filter(a -> a.getGuestEmail().equals(guestEmail)).collect(Collectors.toList()));
	}

	@Override
	public Collection<AccommodationReviewDTO> findAll(Long accommodationId) {
		return toAccommodationReviewDtos(accommodationReviewRepositoryJpa.findAll().stream().filter(a -> a.getAccommodationId().equals(accommodationId)).collect(Collectors.toList()));
	}

	@Override
	public AccommodationReviewDTO findOne(Long id) {
		AccommodationReview accommodationReview = accommodationReviewRepositoryJpa.findById(id)
				.orElseThrow(() -> new AppException("Review not found", HttpStatus.NOT_FOUND));
        return toAccommodationReviewDTO(accommodationReview);
	}

	@Override
	public AccommodationReviewDTO create(AccommodationReviewDTO accommodationReviewDTO) throws Exception {
		AccommodationReview accommodationReview = toAccommodationReview(accommodationReviewDTO);
		if (accommodationReview.getId() != null) {
			throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
		}
		AccommodationReview savedAccommodationreReview = accommodationReviewRepositoryJpa.save(accommodationReview);
		return toAccommodationReviewDTO(savedAccommodationreReview);
	}

	@Override
	public AccommodationReviewDTO update(AccommodationReviewDTO accommodationReviewDTO, Long id) throws Exception {
		AccommodationReview accommodationReview = accommodationReviewRepositoryJpa.findById(id)
                .orElseThrow(() -> new AppException("Review not found", HttpStatus.NOT_FOUND));

		updateAccommodationReview(accommodationReview, toAccommodationReview(accommodationReviewDTO));

        AccommodationReview savedAccommodationreReview = accommodationReviewRepositoryJpa.save(accommodationReview);

        return toAccommodationReviewDTO(savedAccommodationreReview);
	}

	@Override
	public void delete(Long id) {
		accommodationReviewRepositoryJpa.deleteById(id);
	}

	@Override
	public AccommodationReview toAccommodationReview(AccommodationReviewDTO accommodationReviewDTO) {
		if ( accommodationReviewDTO == null ) {
            return null;
        }

        AccommodationReview accommodationReview = new AccommodationReview();

        accommodationReview.setId( accommodationReviewDTO.getId() );
        accommodationReview.setGuestEmail( accommodationReviewDTO.getGuestEmail() );
        accommodationReview.setAccommodationId( accommodationReviewDTO.getAccommodationId() );
        accommodationReview.setComment( accommodationReviewDTO.getComment() );
        accommodationReview.setRating( accommodationReviewDTO.getRating() );
        accommodationReview.setIsApproved( accommodationReviewDTO.getIsApproved() );
        
        return accommodationReview;
	}

	@Override
	public AccommodationReviewDTO toAccommodationReviewDTO(AccommodationReview accommodationReview) {
		if ( accommodationReview == null ) {
            return null;
        }

        AccommodationReviewDTO accommodationReviewDTO = new AccommodationReviewDTO();

        accommodationReviewDTO.setId( accommodationReview.getId() );
        accommodationReviewDTO.setGuestEmail( accommodationReview.getGuestEmail() );
        accommodationReviewDTO.setAccommodationId( accommodationReview.getAccommodationId() );
        accommodationReviewDTO.setComment( accommodationReview.getComment() );
        accommodationReviewDTO.setRating( accommodationReview.getRating() );
        accommodationReviewDTO.setIsApproved( accommodationReview.getIsApproved() );
        
        return accommodationReviewDTO;
	}

	@Override
	public List<AccommodationReviewDTO> toAccommodationReviewDtos(
			List<AccommodationReview> accommodationReviews) {
		if ( accommodationReviews == null ) {
	        return null;
	    }
	
	    List<AccommodationReviewDTO> list = new ArrayList<AccommodationReviewDTO>( accommodationReviews.size() );
	    for ( AccommodationReview accommodationReview1 : accommodationReviews ) {
	        list.add( toAccommodationReviewDTO( accommodationReview1 ) );
	    }
	
	    return list;
	}

	@Override
	public void updateAccommodationReview(AccommodationReview target, AccommodationReview source) {
		if (source == null) {
			return;
		}
		
		target.setId(source.getId());
		target.setGuestEmail(source.getGuestEmail());
		target.setAccommodationId(source.getAccommodationId());
		target.setComment(source.getComment());
		target.setRating(source.getRating());
		target.setIsApproved(source.getIsApproved());
	}

}
