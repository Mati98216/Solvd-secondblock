package com.solvd.laba.service.jdbc;

import com.google.protobuf.ServiceException;
import com.solvd.laba.dao.impl.AnalysisDAOImpl;
import com.solvd.laba.dao.interfaces.AnalysisDAO;
import com.solvd.laba.database.DatabaseConfig;
import com.solvd.laba.domain.Analysis;
import com.solvd.laba.service.interfaces.AnalysisService;

import java.util.List;

public class AnalysisServiceImpl implements AnalysisService {

    private final AnalysisDAO analysisDAO;

    public AnalysisServiceImpl() {

        this.analysisDAO = new AnalysisDAOImpl(DatabaseConfig.getConnectionPool());
    }

    @Override
    public void addAnalysis(Analysis analysis) throws ServiceException {
        try {

            analysisDAO.create(analysis);
        } catch (Exception e) {
            throw new ServiceException("Error adding analysis", e);
        }
    }

    @Override
    public Analysis getAnalysisById(int id) throws ServiceException {
        try {
            return analysisDAO.read(id);
        } catch (Exception e) {
            throw new ServiceException("Error retrieving analysis", e);
        }
    }

    @Override
    public List<Analysis> getAllAnalysis() throws ServiceException {
        try {
            return analysisDAO.findAll();
        } catch (Exception e) {
            throw new ServiceException("Error retrieving all analysis", e);
        }
    }

    @Override
    public void updateAnalysis(Analysis analysis) throws ServiceException {
        try {
            analysisDAO.update(analysis);
        } catch (Exception e) {
            throw new ServiceException("Error updating analysis", e);
        }
    }

    @Override
    public void deleteAnalysis(int id) throws ServiceException {
        try {
            analysisDAO.delete(id);
        } catch (Exception e) {
            throw new ServiceException("Error deleting analysis", e);
        }
    }
}

