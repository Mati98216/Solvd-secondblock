package com.solvd.laba.service.jdbc;

import com.google.protobuf.ServiceException;
import com.solvd.laba.dao.impl.PublicationDAOImpl;
import com.solvd.laba.dao.interfaces.PublicationDAO;
import com.solvd.laba.database.ConnectionPool;
import com.solvd.laba.database.DatabaseConfig;
import com.solvd.laba.domain.Experiment;
import com.solvd.laba.domain.Publication;
import com.solvd.laba.service.interfaces.PublicationService;

import java.sql.SQLException;
import java.util.List;

public class PublicationServiceImpl implements PublicationService {

    private final PublicationDAO publicationDAO;

    public PublicationServiceImpl() {

        this.publicationDAO = new PublicationDAOImpl(DatabaseConfig.getConnectionPool());
    }

    @Override
    public void addPublication(Publication publication) throws ServiceException {
        try {

            publicationDAO.create(publication);
        } catch (Exception e) {
            throw new ServiceException("Error adding publication", e);
        }
    }

    @Override
    public Publication getPublicationById(int id) throws ServiceException {
        try {
            return publicationDAO.read(id);
        } catch (Exception e) {
            throw new ServiceException("Error retrieving publication", e);
        }
    }

    @Override
    public List<Publication> getAllPublications() throws ServiceException {
        try {
            return publicationDAO.findAll();
        } catch (Exception e) {
            throw new ServiceException("Error retrieving all publications", e);
        }
    }

    @Override
    public void updatePublication(Publication publication) throws ServiceException {
        try {
            publicationDAO.update(publication);
        } catch (Exception e) {
            throw new ServiceException("Error updating publication", e);
        }
    }

    @Override
    public void deletePublication(int id) throws ServiceException {
        try {
            publicationDAO.delete(id);
        } catch (Exception e) {
            throw new ServiceException("Error deleting publication", e);
        }
    }

    @Override
    public void addExperimentToPublication(int experimentId, int publicationId) throws ServiceException {
        try {
            publicationDAO.addExperimentToPublication(publicationId, experimentId);
        } catch (Exception e) {
            throw new ServiceException("Error adding experiment to publication", e);
        }
    }

    @Override
    public List<Experiment> getExperimentsForPublication(int publicationId) throws ServiceException {
        try {
            return publicationDAO.getExperimentsForPublication(publicationId);
        } catch (Exception e) {
            throw new ServiceException("Error retrieving experiments for publication", e);
        }
    }

    @Override
    public void updateExperimentForPublication(int publicationId, int oldExperimentId, int newExperimentId) throws ServiceException {
        try {
            publicationDAO.updateExperimentForPublication(publicationId, oldExperimentId, newExperimentId);
        } catch (Exception e) {
            throw new ServiceException("Error updating experiment for publication", e);
        }
    }

    @Override
    public void removeExperimentFromPublication(int publicationId, int experimentId) throws ServiceException {
        try {
            publicationDAO.removeExperimentFromPublication(publicationId, experimentId);
        } catch (Exception e) {
            throw new ServiceException("Error removing experiment from publication", e);
        }
    }
}
