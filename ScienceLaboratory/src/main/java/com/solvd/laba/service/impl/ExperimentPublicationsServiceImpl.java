package com.solvd.laba.service.impl;

import com.google.protobuf.ServiceException;
import com.solvd.laba.dao.interfaces.ExperimentPublicationsDAO;
import com.solvd.laba.domain.ExperimentPublications;
import com.solvd.laba.service.interfaces.ExperimentPublicationsService;

import java.sql.SQLException;
import java.util.List;

public class ExperimentPublicationsServiceImpl implements ExperimentPublicationsService {
    private ExperimentPublicationsDAO experimentPublicationsDAO;

    public ExperimentPublicationsServiceImpl(ExperimentPublicationsDAO experimentPublicationsDAO) {
        this.experimentPublicationsDAO = experimentPublicationsDAO;
    }

    @Override
    public void addExperimentPublications(ExperimentPublications experimentPublications) throws ServiceException {

        try {
            experimentPublicationsDAO.addExperimentPublications(experimentPublications);
        } catch (SQLException e) {
            throw new ServiceException("Error adding experiment publication", e);
        }
    }

    @Override
    public ExperimentPublications getExperimentPublicationsById(int id) throws ServiceException {
        try {
            return experimentPublicationsDAO.getExperimentPublicationsById(id);
        } catch (SQLException e) {
            throw new ServiceException("Error retrieving experiment publication by ID", e);
        }
    }

    @Override
    public List<ExperimentPublications> getAllExperimentPublications() throws ServiceException {
        try {
            return experimentPublicationsDAO.getAllExperimentPublications();
        } catch (SQLException e) {
            throw new ServiceException("Error retrieving all experiment publications", e);
        }
    }

    @Override
    public void updateExperimentPublications(ExperimentPublications experimentPublications) throws ServiceException {

        try {
            experimentPublicationsDAO.updateExperimentPublications(experimentPublications);
        } catch (SQLException e) {
            throw new ServiceException("Error updating experiment publication", e);
        }
    }

    @Override
    public void deleteExperimentPublications(int id) throws ServiceException {
        try {
            experimentPublicationsDAO.deleteExperimentPublications(id);
        } catch (SQLException e) {
            throw new ServiceException("Error deleting experiment publication", e);
        }
    }

}
