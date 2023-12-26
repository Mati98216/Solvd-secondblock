package com.solvd.laba.service.impl;

import com.google.protobuf.ServiceException;
import com.solvd.laba.dao.interfaces.ResearchAreaDAO;
import com.solvd.laba.domain.ResearchArea;
import com.solvd.laba.service.interfaces.ResearchAreaService;

import java.sql.SQLException;
import java.util.List;

public class ResearchAreaServiceImpl implements ResearchAreaService {
    private ResearchAreaDAO researchAreaDAO;

    public ResearchAreaServiceImpl(ResearchAreaDAO researchAreaDAO) {
        this.researchAreaDAO = researchAreaDAO;
    }

    @Override
    public void addResearchArea(ResearchArea researchArea) throws ServiceException {
        try {

            researchAreaDAO.addResearchArea(researchArea);
        } catch (SQLException | InterruptedException e) {
            throw new ServiceException("Error adding research area", e);
        }
    }

    @Override
    public ResearchArea getResearchAreaById(int id) throws ServiceException {
        try {

            return researchAreaDAO.getResearchAreaById(id);
        } catch (SQLException e) {
            throw new ServiceException("Error retrieving research area by ID", e);
        }
    }

    @Override
    public List<ResearchArea> getAllResearchAreas() throws ServiceException {
        try {

            return researchAreaDAO.getAllResearchAreas();
        } catch (SQLException e) {
            throw new ServiceException("Error retrieving all research areas", e);
        }
    }

    @Override
    public void updateResearchArea(ResearchArea researchArea) throws ServiceException {
        try {

            researchAreaDAO.updateResearchArea(researchArea);
        } catch (SQLException e) {
            throw new ServiceException("Error updating research area", e);
        }
    }

    @Override
    public void deleteResearchArea(int id) throws ServiceException {
        try {

            researchAreaDAO.deleteResearchArea(id);
        } catch (SQLException e) {
            throw new ServiceException("Error deleting research area", e);
        }
    }
}
