package db.repository.impl;

import db.DBManager;
import db.dao.ReportDAORepository;
import db.entity.Report;
import db.entity.dto.EventDTO;
import db.entity.dto.ReportDTO;
import db.repository.ReportRepository;
import exception.DBException;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ReportRepositoryImpl implements ReportRepository {

    private final Logger LOGGER = Logger.getLogger(ReportRepositoryImpl.class);

    private final ReportDAORepository reportDAORepository;

    private final DBManager dbManager;

    public ReportRepositoryImpl(ReportDAORepository reportDAORepository, DBManager dbManager) {
        this.reportDAORepository = reportDAORepository;
        this.dbManager = dbManager;
    }

    @Override
    public Report getReport(Report report) {
        return dbManager.doTransaction(() -> {
            try {
                return reportDAORepository.selectReport(report);
            } catch (DBException ex) {
                LOGGER.error("Cannot get report " + ex);
            }
            return null;
        });
    }

    @Override
    public void createReport(Report report) {
        dbManager.doTransaction(() -> {
            try {
                return reportDAORepository.insertReport(report);
            } catch (DBException ex) {
                LOGGER.error("Cannot create new report " + ex);
            }
            return false;
        });
    }

    @Override
    public void updateReport(Report report) {
        dbManager.doTransaction(() -> {
            try {
                return reportDAORepository.updateReport(report);
            } catch (DBException ex) {
                LOGGER.error("Cannot update report " + ex);
            }
            return false;
        });
    }

    @Override
    public void removeReport(int id) {
        dbManager.doTransaction(() -> {
            try {
                reportDAORepository.deleteReport(id);
            } catch (DBException ex) {
                LOGGER.error("Cannot remove report " + ex);
            }
            return false;
        });
    }

    @Override
    public List<ReportDTO> getReportDTOList() {
        return dbManager.doTransaction(() -> {
            try {
                return reportDAORepository.getReportDTOList();
            } catch (DBException ex) {
                LOGGER.error("Cannot get list report " + ex);
            }
            return new ArrayList<>();
        });
    }

    @Override
    public List<ReportDTO> getReportDTOList(EventDTO eventDTO) {
        return dbManager.doTransaction(() -> {
            try {
                return reportDAORepository.getReportDTOList(eventDTO);
            } catch (DBException ex) {
                LOGGER.error("Cannot get list report " + ex);
            }
            return new ArrayList<>();
        });
    }
}