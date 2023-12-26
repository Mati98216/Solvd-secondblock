package com.solvd.laba.service.impl;
import com.google.protobuf.ServiceException;
import com.solvd.laba.dao.interfaces.ExperimentResultDAO;
import com.solvd.laba.domain.ExperimentResult;
import com.solvd.laba.service.interfaces.ExperimentResultService;

import java.sql.SQLException;
import java.util.List;
public class ExperimentResultServiceImpl implements ExperimentResultService {
    private ExperimentResultDAO experimentResultDAO;

    public ExperimentResultServiceImpl(ExperimentResultDAO experimentResultDAO) {
        this.experimentResultDAO = experimentResultDAO;
    }

    @Override
    public void addExperimentResult(ExperimentResult experimentResult) throws ServiceException {
        try {
            experimentResultDAO.addExperimentResult(experimentResult);
        } catch (SQLException | InterruptedException e) {
            throw new ServiceException("Error adding experiment result", e);
        }
    }

    @Override
    public ExperimentResult getExperimentResultById(int id) throws ServiceException {
        try {
            return experimentResultDAO.getExperimentResultById(id);
        } catch (SQLException e) {
            throw new ServiceException("Error retrieving experiment result by ID", e);
        }
    }

    @Override
    public List<ExperimentResult> getAllExperimentResults() throws ServiceException {
        try {
            return experimentResultDAO.getAllExperimentResults();
        } catch (SQLException e) {
            throw new ServiceException("Error retrieving all experiment results", e);
        }
    }

    @Override
    public void updateExperimentResult(ExperimentResult experimentResult) throws ServiceException {
        try {
            experimentResultDAO.updateExperimentResult(experimentResult);
        } catch (SQLException e) {
            throw new ServiceException("Error updating experiment result", e);
        }
    }

    @Override
    public void deleteExperimentResult(int id) throws ServiceException {
        try {
            experimentResultDAO.deleteExperimentResult(id);
        } catch (SQLException e) {
            throw new ServiceException("Error deleting experiment result", e);
        }
    }

}
