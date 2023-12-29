package com.solvd.laba.service.jdbc;

import com.google.protobuf.ServiceException;
import com.solvd.laba.dao.impl.ScientistDAOImpl;
import com.solvd.laba.dao.interfaces.ScientistDAO;
import com.solvd.laba.database.DatabaseConfig;
import com.solvd.laba.domain.Scientist;
import com.solvd.laba.service.interfaces.ScientistService;

import java.util.List;

public class ScientistServiceImpl implements ScientistService {

    private ScientistDAO scientistDAO;



    public ScientistServiceImpl() {
        this.scientistDAO = new ScientistDAOImpl(DatabaseConfig.getConnectionPool());
    }

    @Override
    public void addScientist(Scientist scientist) throws ServiceException {
        try {
            scientistDAO.create(scientist);
        } catch (Exception e) {
            throw new ServiceException("Error adding scientist", e);
        }
    }

    @Override
    public Scientist getScientistById(int id) throws ServiceException {
        try {
            return scientistDAO.read(id);
        } catch (Exception e) {
            throw new ServiceException("Error retrieving scientist", e);
        }
    }

    @Override
    public List<Scientist> getAllScientists() throws ServiceException {
        try {
            return scientistDAO.findAll();
        } catch (Exception e) {
            throw new ServiceException("Error retrieving all scientists", e);
        }
    }

    @Override
    public void updateScientist(Scientist scientist) throws ServiceException {
        try {
            scientistDAO.update(scientist);
        } catch (Exception e) {
            throw new ServiceException("Error updating scientist", e);
        }
    }

    @Override
    public void deleteScientist(int id) throws ServiceException {
        try {
            scientistDAO.delete(id);
        } catch (Exception e) {
            throw new ServiceException("Error deleting scientist", e);
        }
    }
}
