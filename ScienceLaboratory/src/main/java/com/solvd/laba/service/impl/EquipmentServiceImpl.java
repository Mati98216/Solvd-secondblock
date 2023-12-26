package com.solvd.laba.service.impl;

import com.google.protobuf.ServiceException;
import com.solvd.laba.dao.interfaces.EquipmentDAO;
import com.solvd.laba.domain.Equipment;
import com.solvd.laba.service.interfaces.EquipmentService;

import java.sql.SQLException;
import java.util.List;

public class EquipmentServiceImpl implements EquipmentService {
    private EquipmentDAO equipmentDAO;

    public EquipmentServiceImpl(EquipmentDAO equipmentDAO) {
        this.equipmentDAO = equipmentDAO;
    }

    @Override
    public void addEquipment(Equipment equipment) throws ServiceException {
        try {
            equipmentDAO.addEquipment(equipment);
        } catch (SQLException e) {
            throw new ServiceException("Error adding equipment", e);
        }
    }

    @Override
    public Equipment getEquipmentById(int id) throws ServiceException {
        try {
            return equipmentDAO.getEquipmentById(id);
        } catch (SQLException e) {
            throw new ServiceException("Error retrieving equipment by ID", e);
        }
    }

    @Override
    public List<Equipment> getAllEquipment() throws ServiceException {
        try {
            return equipmentDAO.getAllEquipment();
        } catch (SQLException e) {
            throw new ServiceException("Error retrieving all equipment", e);
        }
    }

    @Override
    public void updateEquipment(Equipment equipment) throws ServiceException {
        try {
            equipmentDAO.updateEquipment(equipment);
        } catch (SQLException | InterruptedException e) {
            throw new ServiceException("Error updating equipment", e);
        }
    }

    @Override
    public void deleteEquipment(int id) throws ServiceException {
        try {
            equipmentDAO.deleteEquipment(id);
        } catch (SQLException e) {
            throw new ServiceException("Error deleting equipment", e);
        }
    }

}