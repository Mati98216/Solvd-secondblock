package com.solvd.laba.service.impl;

import com.google.protobuf.ServiceException;
import com.solvd.laba.dao.interfaces.ExperimentDAO;
import com.solvd.laba.domain.Experiment;
import com.solvd.laba.service.interfaces.ExperimentService;

import java.sql.SQLException;
import java.util.List;

public class ExperimentServiceImpl implements ExperimentService {
    private ExperimentDAO experimentDAO;

    public ExperimentServiceImpl(ExperimentDAO experimentDAO) {
        this.experimentDAO = experimentDAO;
    }

    @Override
    public void addExperiment(Experiment experiment) throws ServiceException {

        try {
            experimentDAO.addExperiment(experiment);
        } catch (SQLException | InterruptedException e) {
            throw new ServiceException("Error adding experiment", e);
        }
    }

    @Override
    public Experiment getExperimentById(int id) throws ServiceException {
        try {
            return experimentDAO.getExperimentById(id);
        } catch (SQLException e) {
            throw new ServiceException("Error retrieving experiment by ID", e);
        }
    }

    @Override
    public List<Experiment> getAllExperiments() throws ServiceException {
        try {
            return experimentDAO.getAllExperiments();
        } catch (SQLException e) {
            throw new ServiceException("Error retrieving all experiments", e);
        }
    }

    @Override
    public void updateExperiment(Experiment experiment) throws ServiceException {

        try {
            experimentDAO.updateExperiment(experiment);
        } catch (SQLException e) {
            throw new ServiceException("Error updating experiment", e);
        }
    }

    @Override
    public void deleteExperiment(int id) throws ServiceException {
        try {
            experimentDAO.deleteExperiment(id);
        } catch (SQLException e) {
            throw new ServiceException("Error deleting experiment", e);
        }
    }


}
