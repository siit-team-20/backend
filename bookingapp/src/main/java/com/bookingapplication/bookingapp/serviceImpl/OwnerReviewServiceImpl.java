package com.bookingapplication.bookingapp.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.bookingapplication.bookingapp.domain.OwnerReview;
import com.bookingapplication.bookingapp.dtos.AccommodationDTO;
import com.bookingapplication.bookingapp.dtos.OwnerReviewDTO;
import com.bookingapplication.bookingapp.exceptions.AppException;
import com.bookingapplication.bookingapp.repositoryjpa.OwnerReviewRepositoryJpa;
import com.bookingapplication.bookingapp.service.OwnerReviewService;

@Service
public class OwnerReviewServiceImpl implements OwnerReviewService {

	@Autowired
	private OwnerReviewRepositoryJpa ownerReviewRepositoryJpa;
	
	public OwnerReviewServiceImpl() {
	}
	
	@Override
	public Collection<OwnerReviewDTO> findAll() {
		return toOwnerReviewDtos(ownerReviewRepositoryJpa.findAll());
	}

	@Override
	public Collection<OwnerReviewDTO> findAll(String ownerEmail) {
		return toOwnerReviewDtos(ownerReviewRepositoryJpa.findAll().stream().filter(o -> o.getOwnerEmail().equals(ownerEmail)).collect(Collectors.toList()));
	}
	
	@Override
	public Collection<OwnerReviewDTO> findAll(boolean isReported) {
		return toOwnerReviewDtos(ownerReviewRepositoryJpa.findAll().stream().filter(a -> a.getIsReported() == isReported).collect(Collectors.toList()));
	}
	
	@Override
	public Collection<OwnerReviewDTO> findAll(String ownerEmail, boolean isReported) {
		return toOwnerReviewDtos(ownerReviewRepositoryJpa.findAll().stream().filter(a -> a.getIsReported() == isReported && a.getOwnerEmail().equals(ownerEmail)).collect(Collectors.toList()));
	}

	@Override
	public OwnerReviewDTO findOne(Long id) {
		OwnerReview ownerReview = ownerReviewRepositoryJpa.findById(id)
				.orElseThrow(() -> new AppException("Review not found", HttpStatus.NOT_FOUND));
        return toOwnerReviewDTO(ownerReview);
	}

	@Override
	public OwnerReviewDTO create(OwnerReviewDTO ownerReviewDTO) throws Exception {
		OwnerReview ownerReview = toOwnerReview(ownerReviewDTO);
		if (ownerReview.getId() != null) {
			throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
		}
		OwnerReview savedOwnerReview = ownerReviewRepositoryJpa.save(ownerReview);
		return toOwnerReviewDTO(savedOwnerReview);
	}

	@Override
	public OwnerReviewDTO update(OwnerReviewDTO ownerReviewDTO, Long id) throws Exception {
		OwnerReview ownerReview = ownerReviewRepositoryJpa.findById(id)
                .orElseThrow(() -> new AppException("Review not found", HttpStatus.NOT_FOUND));

		updateOwnerReview(ownerReview, toOwnerReview(ownerReviewDTO));

		OwnerReview savedOwnerReview = ownerReviewRepositoryJpa.save(ownerReview);

        return toOwnerReviewDTO(savedOwnerReview);
	}

	@Override
	public void delete(Long id) {
		ownerReviewRepositoryJpa.deleteById(id);
	}

	@Override
	public OwnerReview toOwnerReview(OwnerReviewDTO ownerReviewDTO) {
		if ( ownerReviewDTO == null ) {
            return null;
        }

		OwnerReview ownerReview = new OwnerReview();

		ownerReview.setId( ownerReviewDTO.getId() );
		ownerReview.setOwnerEmail( ownerReviewDTO.getOwnerEmail() );
		ownerReview.setGuestEmail( ownerReviewDTO.getGuestEmail() );
        ownerReview.setComment( ownerReviewDTO.getComment() );
        ownerReview.setRating( ownerReviewDTO.getRating() );
        ownerReview.setIsReported( ownerReviewDTO.getIsReported() );
        ownerReview.setSubmitDate( ownerReviewDTO.getSubmitDate() );
        
        return ownerReview;
	}

	@Override
	public OwnerReviewDTO toOwnerReviewDTO(OwnerReview ownerReview) {
		if ( ownerReview == null ) {
            return null;
        }

		OwnerReviewDTO ownerReviewDTO = new OwnerReviewDTO();

		ownerReviewDTO.setId( ownerReview.getId() );
		ownerReviewDTO.setOwnerEmail( ownerReview.getOwnerEmail() );
		ownerReviewDTO.setGuestEmail( ownerReview.getGuestEmail() );
		ownerReviewDTO.setComment( ownerReview.getComment() );
		ownerReviewDTO.setRating( ownerReview.getRating() );
		ownerReviewDTO.setIsReported( ownerReview.getIsReported() );
		ownerReviewDTO.setSubmitDate( ownerReview.getSubmitDate() );
        
        return ownerReviewDTO;
	}

	@Override
	public List<OwnerReviewDTO> toOwnerReviewDtos(List<OwnerReview> ownerReviews) {
		if ( ownerReviews == null ) {
	        return null;
	    }
	
	    List<OwnerReviewDTO> list = new ArrayList<OwnerReviewDTO>( ownerReviews.size() );
	    for ( OwnerReview ownerReview : ownerReviews ) {
	        list.add( toOwnerReviewDTO( ownerReview ) );
	    }
	
	    return list;
	}

	@Override
	public void updateOwnerReview(OwnerReview target, OwnerReview source) {
		if (source == null) {
			return;
		}
		
		target.setId(source.getId());
		target.setOwnerEmail(source.getOwnerEmail());
		target.setGuestEmail(source.getGuestEmail());
		target.setComment(source.getComment());
		target.setRating(source.getRating());
		target.setIsReported(source.getIsReported());
		target.setSubmitDate(source.getSubmitDate());
	}

}
