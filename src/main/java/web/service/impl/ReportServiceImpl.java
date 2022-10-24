package web.service.impl;

import db.entity.Report;
import db.entity.dto.ReportDTO;
import db.repository.ReportRepository;
import web.service.ReportService;

import java.util.List;

public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public void addReport(Report report) {
        reportRepository.createReport(report);
    }

    @Override
    public void updateReportInfo(Report report) {
        reportRepository.updateReport(report);
    }

    @Override
    public Report findReport(Report report) {
        return reportRepository.getReport(report);
    }

    @Override
    public List<ReportDTO> getReportDTOList() {
        return reportRepository.getReportDTOList();
    }
}