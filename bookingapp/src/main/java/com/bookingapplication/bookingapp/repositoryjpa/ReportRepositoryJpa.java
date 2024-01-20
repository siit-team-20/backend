package com.bookingapplication.bookingapp.repositoryjpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookingapplication.bookingapp.domain.Report;


public interface ReportRepositoryJpa extends JpaRepository<Report, Long> {

	

}
