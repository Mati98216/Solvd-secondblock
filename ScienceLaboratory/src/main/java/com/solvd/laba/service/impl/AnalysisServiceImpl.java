package com.solvd.laba.service.impl;

import com.google.protobuf.ServiceException;
import com.solvd.laba.dao.interfaces.AnalysisDAO;
import com.solvd.laba.domain.Analysis;
import com.solvd.laba.service.interfaces.AnalysisService;

import java.sql.SQLException;
import java.util.List;

public class AnalysisServiceImpl implements AnalysisService {
    private AnalysisDAO analysisDAO;

    public AnalysisServiceImpl(AnalysisDAO analysisDAO) {
        this.analysisDAO = analysisDAO;
    }

    @Override
    public void addAnalysis(Analysis analysis) throws ServiceException {
        try {
            analysisDAO.addAnalysis(analysis);
        } catch (SQLException | InterruptedException e) {
            throw new ServiceException("Error adding analysis", e);
        }
    }

    @Override
    public Analysis getAnalysisById(int id) throws ServiceException {
        try {
            return analysisDAO.getAnalysisById(id);
        } catch (SQLException e) {
            throw new ServiceException("Error retrieving analysis by ID", e);
        }
    }

    @Override
    public List<Analysis> getAllAnalyses() throws ServiceException {
        try {
            return analysisDAO.getAllAnalyses();
        } catch (SQLException e) {
            throw new ServiceException("Error retrieving all analyses", e);
        }
    }

    @Override
    public void updateAnalysis(Analysis analysis) throws ServiceException {
        try {
            analysisDAO.updateAnalysis(analysis);
        } catch (SQLException e) {
            throw new ServiceException("Error updating analysis", e);
        }
    }

    @Override
    public void deleteAnalysis(int id) throws ServiceException {
        try {
            analysisDAO.deleteAnalysis(id);
        } catch (SQLException e) {
            throw new ServiceException("Error deleting analysis", e);
        }
    }

}
