package com.solvd.laba.service.jdbc;

import com.google.protobuf.ServiceException;
import com.solvd.laba.dao.impl.ResearchAreaDAOImpl;
import com.solvd.laba.dao.interfaces.ResearchAreaDAO;
import com.solvd.laba.database.DatabaseConfig;
import com.solvd.laba.domain.ResearchArea;
import com.solvd.laba.service.interfaces.ResearchAreaService;

import java.util.List;

public class ResearchAreaServiceImpl implements ResearchAreaService {

    private final ResearchAreaDAO researchAreaDAO;

    public ResearchAreaServiceImpl() {
        this.researchAreaDAO = new ResearchAreaDAOImpl(DatabaseConfig.getConnectionPool());
    }

    @Override
    public void addResearchArea(ResearchArea researchArea) throws ServiceException {
        try {
            researchAreaDAO.create(researchArea);
        } catch (Exception e) {
            throw new ServiceException("Error adding research area", e);
        }
    }

    @Override
    public ResearchArea getResearchAreaById(int id) throws ServiceException {
        try {
            return researchAreaDAO.read(id);
        } catch (Exception e) {
            throw new ServiceException("Error retrieving research area", e);
        }
    }

    @Override
    public List<ResearchArea> getAllResearchAreas() throws ServiceException {
        try {
            return researchAreaDAO.findAll();
        } catch (Exception e) {
            throw new ServiceException("Error retrieving all research areas", e);
        }
    }

    @Override
    public void updateResearchArea(ResearchArea researchArea) throws ServiceException {
        try {
            researchAreaDAO.update(researchArea);
        } catch (Exception e) {
            throw new ServiceException("Error updating research area", e);
        }
    }

    @Override
    public void deleteResearchArea(int id) throws ServiceException {
        try {
            researchAreaDAO.delete(id);
        } catch (Exception e) {
            throw new ServiceException("Error deleting research area", e);
        }
    }
}
