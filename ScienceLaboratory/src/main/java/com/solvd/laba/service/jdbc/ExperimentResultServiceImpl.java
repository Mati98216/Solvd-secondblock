package com.solvd.laba.service.jdbc;

import com.google.protobuf.ServiceException;
import com.solvd.laba.dao.impl.ExperimentResultDAOImpl;
import com.solvd.laba.dao.interfaces.ExperimentResultDAO;
import com.solvd.laba.database.DatabaseConfig;
import com.solvd.laba.domain.ExperimentResult;
import com.solvd.laba.service.interfaces.ExperimentResultService;

import java.util.List;

public class ExperimentResultServiceImpl implements ExperimentResultService {

    private final ExperimentResultDAO experimentResultDAO;

    public ExperimentResultServiceImpl() {
        this.experimentResultDAO = new ExperimentResultDAOImpl(DatabaseConfig.getConnectionPool());
    }

    @Override
    public void addExperimentResult(ExperimentResult dto) throws ServiceException {
        try {

            ExperimentResult experimentResult = new ExperimentResult.Builder()
                    .resultId(dto.getResultId())
                    .experiment(dto.getExperiment())
                    .analysis(dto.getAnalysis())
                    .resultDetails(dto.getResultDetails())
                    .build();

            experimentResultDAO.create(experimentResult);
        } catch (Exception e) {
            throw new ServiceException("Error adding experiment result", e);
        }
    }
    @Override
    public ExperimentResult getExperimentResultById(int id) throws ServiceException {
        try {
            return experimentResultDAO.read(id);
        } catch (Exception e) {
            throw new ServiceException("Error retrieving experiment result", e);
        }
    }

    @Override
    public List<ExperimentResult> getAllExperimentResults() throws ServiceException {
        try {
            return experimentResultDAO.findAll();
        } catch (Exception e) {
            throw new ServiceException("Error retrieving all experiment results", e);
        }
    }

    @Override
    public void updateExperimentResult(ExperimentResult experimentResult) throws ServiceException {
        try {
            experimentResultDAO.update(experimentResult);
        } catch (Exception e) {
            throw new ServiceException("Error updating experiment result", e);
        }
    }

    @Override
    public void deleteExperimentResult(int id) throws ServiceException {
        try {
            experimentResultDAO.delete(id);
        } catch (Exception e) {
            throw new ServiceException("Error deleting experiment result", e);
        }
    }
}

