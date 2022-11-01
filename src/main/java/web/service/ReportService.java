package web.service;

import db.entity.Report;
import db.entity.dto.EventDTO;
import db.entity.dto.ReportDTO;
import web.bean.ReportBean;

import java.util.List;

public interface ReportService {

    void addReport(Report report);

    void addReport(ReportBean reportBean);

    void updateReportInfo(Report report);

    Report findReport(Report report);

    Report findReport(ReportBean reportBean);

    List<ReportDTO> getReportDTOList();

    List<ReportDTO> getReportDTOList(EventDTO eventDTO);
}