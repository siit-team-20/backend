package com.bookingapplication.bookingapp.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bookingapplication.bookingapp.domain.DateRange;
import com.bookingapplication.bookingapp.dtos.DateRangeDTO;
import com.bookingapplication.bookingapp.exceptions.AppException;
import com.bookingapplication.bookingapp.repositoryjpa.DateRangeRepositoryJpa;
import com.bookingapplication.bookingapp.service.DateRangeService;

@Service
public class DateRangeServiceImpl implements DateRangeService{
	
	@Autowired
	private DateRangeRepositoryJpa dateRangeRepositoryJpa;
	
	public DateRangeServiceImpl() {
	}

	@Override
	public Collection<DateRangeDTO> findAll() {
		return toDateRangeDTOs(dateRangeRepositoryJpa.findAll());
	}
	
	@Override
	public Collection<DateRangeDTO> findAll(Long accommodationId) {
		return toDateRangeDTOs(dateRangeRepositoryJpa.findAll().stream().filter(d -> d.getAccommodationId().equals(accommodationId)).collect(Collectors.toList()));
	}

	@Override
	public DateRangeDTO findOne(Long id) {
		DateRange dateRange = dateRangeRepositoryJpa.findById(id)
				.orElseThrow(() -> new AppException("Date range not found", HttpStatus.NOT_FOUND));
        return toDateRangeDTO(dateRange);
	}

	@Override
	public DateRangeDTO create(DateRangeDTO dateRangeDTO) throws Exception {
		DateRange dateRange = toDateRange(dateRangeDTO);
		if (dateRange.getId() != null) {
			throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
		}
		DateRange savedDateRange = dateRangeRepositoryJpa.save(dateRange);
		return toDateRangeDTO(savedDateRange);
	}
	
	@Override
	public DateRangeDTO update(DateRangeDTO dateRangeDTO, Long id) throws Exception {
		DateRange dateRange = dateRangeRepositoryJpa.findById(id)
                .orElseThrow(() -> new AppException("Date range not found", HttpStatus.NOT_FOUND));

		updateDateRange(dateRange, toDateRange(dateRangeDTO));

        DateRange savedDateRange = dateRangeRepositoryJpa.save(dateRange);

        return toDateRangeDTO(savedDateRange);
	}

	@Override
	public void delete(Long id) {
		dateRangeRepositoryJpa.deleteById(id);
	}
	
	@Override
	public void deleteByAccommodationId(Long accommodationId) {
		dateRangeRepositoryJpa.deleteByAccommodationId(accommodationId);
	}
	
	@Override
    public DateRange toDateRange(DateRangeDTO dateRangeDTO) {
		if ( dateRangeDTO == null ) {
            return null;
        }

        DateRange dateRange = new DateRange();

        dateRange.setId( dateRangeDTO.getId() );
        dateRange.setAccommodationId( dateRangeDTO.getAccommodationId() );
        dateRange.setEndDate( dateRangeDTO.getEndDate() );
        dateRange.setStartDate( dateRangeDTO.getStartDate() );
        dateRange.setPrice( dateRangeDTO.getPrice() );
        
        return dateRange;
    }

    @Override
    public DateRangeDTO toDateRangeDTO(DateRange dateRange) {
    	if ( dateRange == null ) {
            return null;
        }

        DateRangeDTO dateRangeDTO = new DateRangeDTO();

        dateRangeDTO.setId( dateRange.getId() );
        dateRangeDTO.setAccommodationId( dateRange.getAccommodationId() );
        dateRangeDTO.setEndDate( dateRange.getEndDate() );
        dateRangeDTO.setStartDate( dateRange.getStartDate() );
        dateRangeDTO.setPrice( dateRange.getPrice() );

        return dateRangeDTO;
    }

    @Override
    public List<DateRangeDTO> toDateRangeDTOs(List<DateRange> dateRanges) {
        if ( dateRanges == null ) {
            return null;
        }

        List<DateRangeDTO> list = new ArrayList<DateRangeDTO>( dateRanges.size() );
        for ( DateRange dateRange : dateRanges ) {
            list.add( toDateRangeDTO( dateRange ) );
        }

        return list;
    }

    @Override
    public void updateDateRange(DateRange target, DateRange source) {
        if ( source == null ) {
            return;
        }

        target.setId( source.getId() );
        target.setAccommodationId( source.getAccommodationId() );
        target.setEndDate( source.getEndDate() );
        target.setStartDate( source.getStartDate());
        target.setPrice( source.getPrice());
    }

}