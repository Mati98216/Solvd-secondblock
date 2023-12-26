package com.solvd.laba.service.impl;

import com.google.protobuf.ServiceException;
import com.solvd.laba.dao.interfaces.PublicationDAO;
import com.solvd.laba.domain.Publication;
import com.solvd.laba.service.interfaces.PublicationService;

import java.sql.SQLException;
import java.util.List;

public class PublicationServiceImpl implements PublicationService {
    private PublicationDAO publicationDAO;

    public PublicationServiceImpl(PublicationDAO publicationDAO) {
        this.publicationDAO = publicationDAO;
    }

    @Override
    public void addPublication(Publication publication) throws ServiceException {
        try {

            publicationDAO.addPublication(publication);
        } catch (SQLException e) {
            throw new ServiceException("Error adding publication", e);
        }
    }

    @Override
    public Publication getPublicationById(int id) throws ServiceException {
        try {

            return publicationDAO.getPublicationById(id);
        } catch (SQLException e) {
            throw new ServiceException("Error retrieving publication by ID", e);
        }
    }

    @Override
    public List<Publication> getAllPublications() throws ServiceException {
        try {

            return publicationDAO.getAllPublications();
        } catch (SQLException e) {
            throw new ServiceException("Error retrieving all publications", e);
        }
    }

    @Override
    public void updatePublication(Publication publication) throws ServiceException {
        try {

            publicationDAO.updatePublication(publication);
        } catch (SQLException e) {
            throw new ServiceException("Error updating publication", e);
        }
    }

    @Override
    public void deletePublication(int id) throws ServiceException {
        try {

            publicationDAO.deletePublication(id);
        } catch (SQLException e) {
            throw new ServiceException("Error deleting publication", e);
        }
    }
}
