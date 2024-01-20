package com.bookingapplication.bookingapp.repositoryImpl;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import com.bookingapplication.bookingapp.dtos.ReportDTO;
import com.bookingapplication.bookingapp.repository.ReportRepository;

public class InMemoryReportRepository implements ReportRepository {

	private static AtomicLong counter = new AtomicLong();

	private final ConcurrentMap<Long, ReportDTO> reports = new ConcurrentHashMap<Long, ReportDTO>();

	@Override
	public Collection<ReportDTO> findAll() {
		return this.reports.values();
	}

	@Override
	public ReportDTO create(ReportDTO report) {
		Long id = report.getId();

		if (id == null) {
			id = counter.incrementAndGet();
			report.setId(id);
		}

		this.reports.put(id, report);
		return report;
	}

	@Override
	public ReportDTO findOne(Long id) {
		return this.reports.get(id);
	}

	@Override
	public void delete(Long id) {
		this.reports.remove(id);
	}

	@Override
	public ReportDTO update(ReportDTO report) {
		Long id = report.getId();

		if (id != null) {
			this.reports.put(id, report);
		}

		return report;
	}

}
