package com.solvd.laba.service.impl;

import com.google.protobuf.ServiceException;
import com.solvd.laba.dao.interfaces.ExperimentEquipmentDAO;
import com.solvd.laba.domain.ExperimentEquipment;
import com.solvd.laba.service.interfaces.ExperimentEquipmentService;

import java.sql.SQLException;
import java.util.List;

public class ExperimentEquipmentServiceImpl implements ExperimentEquipmentService {
    private ExperimentEquipmentDAO experimentEquipmentDAO;

    public ExperimentEquipmentServiceImpl(ExperimentEquipmentDAO experimentEquipmentDAO) {
        this.experimentEquipmentDAO = experimentEquipmentDAO;
    }

    @Override
    public void addExperimentEquipment(ExperimentEquipment equipment) throws ServiceException {

        try {
            experimentEquipmentDAO.addExperimentEquipment(equipment);
        } catch (SQLException e) {
            throw new ServiceException("Error adding experiment equipment", e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ExperimentEquipment getExperimentEquipmentById(int id) throws ServiceException {
        try {
            return experimentEquipmentDAO.getExperimentEquipmentById(id);
        } catch (SQLException e) {
            throw new ServiceException("Error retrieving experiment equipment by ID", e);
        }
    }

    @Override
    public List<ExperimentEquipment> getAllExperimentEquipments() throws ServiceException {
        try {
            return experimentEquipmentDAO.getAllExperimentEquipments();
        } catch (SQLException e) {
            throw new ServiceException("Error retrieving all experiment equipments", e);
        }
    }

    @Override
    public void updateExperimentEquipment(ExperimentEquipment equipment) throws ServiceException {

        try {
            experimentEquipmentDAO.updateExperimentEquipment(equipment);
        } catch (SQLException e) {
            throw new ServiceException("Error updating experiment equipment", e);
        }
    }

    @Override
    public void deleteExperimentEquipment(int id) throws ServiceException {
        try {
            experimentEquipmentDAO.deleteExperimentEquipment(id);
        } catch (SQLException e) {
            throw new ServiceException("Error deleting experiment equipment", e);
        }
    }


}
