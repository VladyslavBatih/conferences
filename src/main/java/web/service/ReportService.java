package web.service;

import db.entity.Report;
import db.entity.dto.ReportDTO;

import java.util.List;

public interface ReportService {

    void addReport(Report report);

    void updateReportInfo(Report report);

    Report findReport(Report report);

    List<ReportDTO> getReportDTOList();
}