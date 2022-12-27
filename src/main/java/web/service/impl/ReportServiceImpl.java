package web.service.impl;

import db.entity.Report;
import db.entity.dto.EventDTO;
import db.entity.dto.ReportDTO;
import db.repository.ReportRepository;
import web.bean.ReportBean;
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
    public void addReport(ReportBean reportBean) {
        reportRepository.createReport(getEntity(reportBean));
    }

    @Override
    public void updateReportInfo(Report report) {
        reportRepository.updateReport(report);
    }

    @Override
    public void removeReport(int id) {
        reportRepository.removeReport(id);
    }

    @Override
    public Report findReport(Report report) {
        return reportRepository.getReport(report);
    }

    @Override
    public Report findReport(ReportBean reportBean) {
        return reportRepository.getReport(getEntity(reportBean));
    }

    @Override
    public List<ReportDTO> getReportDTOList() {
        return reportRepository.getReportDTOList();
    }

    @Override
    public List<ReportDTO> getReportDTOList(EventDTO eventDTO) {
        return reportRepository.getReportDTOList(eventDTO);
    }

    private Report getEntity(ReportBean reportBean) {
        Report report = new Report();
        report.setTopic(reportBean.getTopic());
        report.setEventId(reportBean.getEventId());
        report.setSpeakerId(23);
        return report;
    }
}