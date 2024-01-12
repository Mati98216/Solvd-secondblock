package com.solvd.laba.service.jdbc;

import com.google.protobuf.ServiceException;
import com.solvd.laba.dao.impl.EquipmentDAOImpl;
import com.solvd.laba.dao.interfaces.EquipmentDAO;
import com.solvd.laba.database.DatabaseConfig;
import com.solvd.laba.domain.Equipment;
import com.solvd.laba.service.interfaces.EquipmentService;

import java.util.List;

public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentDAO equipmentDAO;

    public EquipmentServiceImpl() {
        this.equipmentDAO = new EquipmentDAOImpl(DatabaseConfig.getConnectionPool());
    }

    @Override
    public void addEquipment(Equipment equipment) throws ServiceException {
        try {

            equipmentDAO.create(equipment);
        } catch (Exception e) {
            throw new ServiceException("Error adding equipment", e);
        }
    }

    @Override
    public Equipment getEquipmentById(int id) throws ServiceException {
        try {
            return equipmentDAO.read(id);
        } catch (Exception e) {
            throw new ServiceException("Error retrieving equipment", e);
        }
    }

    @Override
    public List<Equipment> getAllEquipment() throws ServiceException {
        try {
            return equipmentDAO.findAll();
        } catch (Exception e) {
            throw new ServiceException("Error retrieving all equipment", e);
        }
    }

    @Override
    public void updateEquipment(Equipment equipment) throws ServiceException {
        try {

            equipmentDAO.update(equipment);
        } catch (Exception e) {
            throw new ServiceException("Error updating equipment", e);
        }
    }

    @Override
    public void deleteEquipment(int id) throws ServiceException {
        try {
            equipmentDAO.delete(id);
        } catch (Exception e) {
            throw new ServiceException("Error deleting equipment", e);
        }
    }
}
