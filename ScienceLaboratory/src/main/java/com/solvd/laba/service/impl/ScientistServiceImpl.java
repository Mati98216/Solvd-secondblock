package com.solvd.laba.service.impl;

import com.google.protobuf.ServiceException;
import com.solvd.laba.dao.interfaces.ScientistDAO;
import com.solvd.laba.domain.Scientist;
import com.solvd.laba.service.interfaces.ScientistService;

import java.sql.SQLException;
import java.util.List;

public class ScientistServiceImpl implements ScientistService {
    private ScientistDAO scientistDAO;

    public ScientistServiceImpl(ScientistDAO scientistDAO) {
        this.scientistDAO = scientistDAO;
    }

    @Override
    public void addScientist(Scientist scientist) throws ServiceException {
        try {

            scientistDAO.addScientist(scientist);
        } catch (SQLException e) {
            throw new ServiceException("Error adding scientist", e);
        }
    }

    @Override
    public Scientist getScientistById(int id) throws ServiceException {
        try {

            return scientistDAO.getScientistById(id);
        } catch (SQLException | InterruptedException e) {
            throw new ServiceException("Error retrieving scientist by ID", e);
        }
    }

    @Override
    public List<Scientist> getAllScientists() throws ServiceException {
        try {

            return scientistDAO.getAllScientists();
        } catch (SQLException | InterruptedException e) {
            throw new ServiceException("Error retrieving all scientists", e);
        }
    }

    @Override
    public void updateScientist(Scientist scientist) throws ServiceException {
        try {

            scientistDAO.updateScientist(scientist);
        } catch (SQLException e) {
            throw new ServiceException("Error updating scientist", e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteScientist(int id) throws ServiceException {
        try {

            scientistDAO.deleteScientist(id);
        } catch (SQLException | InterruptedException e) {
            throw new ServiceException("Error deleting scientist", e);
        }
    }
}
