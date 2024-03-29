package db.repository;

import db.entity.Report;
import db.entity.dto.EventDTO;
import db.entity.dto.ReportDTO;

import java.util.List;

public interface ReportRepository {

    Report getReport(Report report);

    void createReport(Report report);

    void updateReport(Report report);

    void removeReport(int id);

    List<ReportDTO> getReportDTOList();

    List<ReportDTO> getReportDTOList(EventDTO eventDTO);
}