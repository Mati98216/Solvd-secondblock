package com.solvd.laba.service.jdbc;

import com.google.protobuf.ServiceException;
import com.solvd.laba.dao.impl.ExperimentDAOImpl;
import com.solvd.laba.dao.interfaces.ExperimentDAO;
import com.solvd.laba.database.DatabaseConfig;
import com.solvd.laba.domain.Equipment;
import com.solvd.laba.domain.Experiment;
import com.solvd.laba.service.interfaces.ExperimentService;

import java.util.List;

public class ExperimentServiceImpl implements ExperimentService {

    private final ExperimentDAO experimentDAO;

    public ExperimentServiceImpl() {
        this.experimentDAO = new ExperimentDAOImpl(DatabaseConfig.getConnectionPool());
    }

    @Override
    public void addExperiment(Experiment experiment) throws ServiceException {
        try {
            experimentDAO.create(experiment);
        } catch (Exception e) {
            throw new ServiceException("Error adding experiment", e);
        }
    }

    @Override
    public Experiment getExperimentById(int id) throws ServiceException {
        try {
            return experimentDAO.read(id);
        } catch (Exception e) {
            throw new ServiceException("Error retrieving experiment", e);
        }
    }

    @Override
    public List<Experiment> getAllExperiments() throws ServiceException {
        try {
            return experimentDAO.findAll();
        } catch (Exception e) {
            throw new ServiceException("Error retrieving all experiments", e);
        }
    }

    @Override
    public void updateExperiment(Experiment experiment) throws ServiceException {
        try {
            experimentDAO.update(experiment);
        } catch (Exception e) {
            throw new ServiceException("Error updating experiment", e);
        }
    }

    @Override
    public void deleteExperiment(int id) throws ServiceException {
        try {
            experimentDAO.delete(id);
        } catch (Exception e) {
            throw new ServiceException("Error deleting experiment", e);
        }
    }
    @Override
    public void addEquipmentToExperiment(int experimentId, int equipmentId) throws ServiceException {
        try {

            experimentDAO.addEquipmentToExperiment(experimentId, equipmentId);
        } catch (Exception e) {
            throw new ServiceException("Error adding equipment to experiment", e);
        }
    }
    @Override
    public void updateEquipmentForExperiment(int experimentId, int oldEquipmentId, int newEquipmentId) throws ServiceException {
        try {

            experimentDAO.updateEquipmentForExperiment(experimentId, oldEquipmentId, newEquipmentId);
        } catch (Exception e) {
            throw new ServiceException("Error updating equipment for experiment", e);
        }
    }
    @Override
    public void removeEquipmentFromExperiment(int experimentId, int equipmentId) throws ServiceException {
        try {

            experimentDAO.removeEquipmentFromExperiment(experimentId, equipmentId);
        } catch (Exception e) {
            throw new ServiceException("Error removing equipment from experiment", e);
        }
    }
    @Override
    public List<Equipment> getEquipmentForExperiment(int experimentId) throws ServiceException {
        try {
            return experimentDAO.getEquipmentForExperiment(experimentId);
        } catch (Exception e) {
            throw new ServiceException("Error retrieving equipment for experiment", e);
        }
    }
}

