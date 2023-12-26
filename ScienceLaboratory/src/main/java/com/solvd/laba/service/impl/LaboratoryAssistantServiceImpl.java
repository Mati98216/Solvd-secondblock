package com.solvd.laba.service.impl;

import com.google.protobuf.ServiceException;
import com.solvd.laba.dao.interfaces.LaboratoryAssistantDAO;
import com.solvd.laba.domain.LaboratoryAssistant;
import com.solvd.laba.service.interfaces.LaboratoryAssistantService;

import java.sql.SQLException;
import java.util.List;

public class LaboratoryAssistantServiceImpl implements LaboratoryAssistantService {
    private LaboratoryAssistantDAO assistantDAO;

    public LaboratoryAssistantServiceImpl(LaboratoryAssistantDAO assistantDAO) {
        this.assistantDAO = assistantDAO;
    }

    @Override
    public void addLaboratoryAssistant(LaboratoryAssistant assistant) throws ServiceException {
        try {
            assistantDAO.addLaboratoryAssistant(assistant);
        } catch (SQLException e) {
            throw new ServiceException("Error adding laboratory assistant", e);
        }
    }

    @Override
    public LaboratoryAssistant getLaboratoryAssistantById(int id) throws ServiceException {
        try {
            return assistantDAO.getLaboratoryAssistantById(id);
        } catch (SQLException e) {
            throw new ServiceException("Error retrieving laboratory assistant by ID", e);
        }
    }

    @Override
    public List<LaboratoryAssistant> getAllLaboratoryAssistants() throws ServiceException {
        try {
            return assistantDAO.getAllLaboratoryAssistants();
        } catch (SQLException e) {
            throw new ServiceException("Error retrieving all laboratory assistants", e);
        }
    }

    @Override
    public void updateLaboratoryAssistant(LaboratoryAssistant assistant) throws ServiceException {

        try {
            assistantDAO.updateLaboratoryAssistant(assistant);
        } catch (SQLException e) {
            throw new ServiceException("Error updating laboratory assistant", e);
        }
    }

    @Override
    public void deleteLaboratoryAssistant(int id) throws ServiceException {
        try {
            assistantDAO.deleteLaboratoryAssistant(id);
        } catch (SQLException e) {
            throw new ServiceException("Error deleting laboratory assistant", e);
        }
    }


}
