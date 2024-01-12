package com.solvd.laba.service.jdbc;

import com.google.protobuf.ServiceException;
import com.solvd.laba.dao.impl.LaboratoryAssistantDAOImpl;
import com.solvd.laba.dao.interfaces.LaboratoryAssistantDAO;
import com.solvd.laba.database.DatabaseConfig;
import com.solvd.laba.domain.LaboratoryAssistant;
import com.solvd.laba.service.interfaces.LaboratoryAssistantService;
import java.util.List;

public class LaboratoryAssistantServiceImpl implements LaboratoryAssistantService {

    private final LaboratoryAssistantDAO laboratoryAssistantDAO;

    public LaboratoryAssistantServiceImpl() {
        this.laboratoryAssistantDAO = new LaboratoryAssistantDAOImpl(DatabaseConfig.getConnectionPool());
    }

    @Override
    public void addLaboratoryAssistant(LaboratoryAssistant laboratoryAssistant) throws ServiceException {
        try {
            laboratoryAssistantDAO.create(laboratoryAssistant);
        } catch (Exception e) {
            throw new ServiceException("Error adding laboratory assistant", e);
        }
    }

    @Override
    public LaboratoryAssistant getLaboratoryAssistantById(int id) throws ServiceException {
        try {
            return laboratoryAssistantDAO.read(id);
        } catch (Exception e) {
            throw new ServiceException("Error retrieving laboratory assistant", e);
        }
    }

    @Override
    public List<LaboratoryAssistant> getAllLaboratoryAssistants() throws ServiceException {
        try {
            return laboratoryAssistantDAO.findAll();
        } catch (Exception e) {
            throw new ServiceException("Error retrieving all laboratory assistants", e);
        }
    }

    @Override
    public void updateLaboratoryAssistant(LaboratoryAssistant laboratoryAssistant) throws ServiceException {
        try {
            laboratoryAssistantDAO.update(laboratoryAssistant);
        } catch (Exception e) {
            throw new ServiceException("Error updating laboratory assistant", e);
        }
    }

    @Override
    public void deleteLaboratoryAssistant(int id) throws ServiceException {
        try {
            laboratoryAssistantDAO.delete(id);
        } catch (Exception e) {
            throw new ServiceException("Error deleting laboratory assistant", e);
        }
    }
}


