package com.bookingapplication.bookingapp.service;

import java.util.Collection;
import java.util.List;

import com.bookingapplication.bookingapp.domain.DateRange;
import com.bookingapplication.bookingapp.dtos.DateRangeDTO;

public interface DateRangeService {
	
	Collection<DateRangeDTO> findAll();
	Collection<DateRangeDTO> findAll(Long accommodationId);
	DateRangeDTO findOne(Long id);
	DateRangeDTO create(DateRangeDTO dateRange) throws Exception;
	DateRangeDTO update(DateRangeDTO dateRange, Long id) throws Exception;
	void delete(Long id);
	void deleteByAccommodationId(Long accommodationId);
	
	DateRange toDateRange(DateRangeDTO dateRangeDTO);

	DateRangeDTO toDateRangeDTO(DateRange dateRange);

	List<DateRangeDTO> toDateRangeDTOs(List<DateRange> dateRanges);

	void updateDateRange(DateRange target, DateRange source);

}
