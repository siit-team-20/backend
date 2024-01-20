package com.bookingapplication.bookingapp.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.bookingapplication.bookingapp.domain.Report;
import com.bookingapplication.bookingapp.dtos.ReportDTO;
import com.bookingapplication.bookingapp.exceptions.AppException;
import com.bookingapplication.bookingapp.repositoryjpa.ReportRepositoryJpa;
import com.bookingapplication.bookingapp.service.ReportService;

public class ReportServiceImpl implements ReportService {

	private ReportRepositoryJpa reportRepositoryJpa;
	
	public ReportServiceImpl() {
		
	}
	@Override
	public Collection<ReportDTO> findAll(){
		return toReportDTOs(reportRepositoryJpa.findAll());
	}
	

	@Override
	public ReportDTO findOne(Long id) {
		Report report = reportRepositoryJpa.findById(id)
				.orElseThrow(() -> new AppException("Report not found", HttpStatus.NOT_FOUND));
        return toReportDTO(report);
	}
	

	@Override
	public ReportDTO create(ReportDTO reportDTO) throws Exception {
		Report report = toReport(reportDTO);
		if (report.getId() != null) {
			throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
		}
		Report savedReport = reportRepositoryJpa.save(report);
		return toReportDTO(savedReport);
	}
	
	@Override
	public ReportDTO update(ReportDTO reportDTO, Long id) throws Exception {
		Report report = reportRepositoryJpa.findById(id)
                .orElseThrow(() -> new AppException("Report not found", HttpStatus.NOT_FOUND));

		updateReport(report, toReport(reportDTO));

        Report savedReport = reportRepositoryJpa.save(report);

        return toReportDTO(savedReport);
	}

	@Override
	public void delete(Long id) {
		reportRepositoryJpa.deleteById(id);
	}
	
	
	@Override
    public Report toReport(ReportDTO reportDTO) {
		if ( reportDTO == null ) {
            return null;
        }

        Report report = new Report();

        report.setId( reportDTO.getId() );
        report.setReporterEmail(reportDTO.getReporterEmail());
        report.setReportedEmail(reportDTO.getReportedEmail());
        report.setAccommodationId(reportDTO.getAccommodationId());
        
        
        return report;
    }

    @Override
    public ReportDTO toReportDTO(Report report) {
    	if ( report == null ) {
            return null;
        }

        ReportDTO reportDTO = new ReportDTO();

        reportDTO.setId( report.getId() );
        reportDTO.setReportedEmail(report.getReportedEmail());
        reportDTO.setReporterEmail(report.getReporterEmail());
        reportDTO.setAccommodationId(report.getAccommodationId());

        return reportDTO;
    }

    @Override
    public List<ReportDTO> toReportDTOs(List<Report> reports) {
        if ( reports == null ) {
            return null;
        }

        List<ReportDTO> list = new ArrayList<ReportDTO>( reports.size() );
        for ( Report report : reports ) {
            list.add( toReportDTO( report ) );
        }

        return list;
    }

    @Override
    public void updateReport(Report target, Report source) {
        if ( source == null ) {
            return;
        }

        target.setId( source.getId() );
        target.setAccommodationId( source.getAccommodationId() );
        target.setReportedEmail(source.getReportedEmail());
        target.setReporterEmail(source.getReporterEmail());
    }

}
