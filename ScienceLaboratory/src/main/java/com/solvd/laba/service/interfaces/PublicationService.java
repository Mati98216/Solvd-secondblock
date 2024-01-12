package com.solvd.laba.service.interfaces;

import com.google.protobuf.ServiceException;
import com.solvd.laba.domain.Experiment;
import com.solvd.laba.domain.Publication;

import java.util.List;

public interface PublicationService {
    void addPublication(Publication publication) throws ServiceException;
    Publication getPublicationById(int id) throws ServiceException;
    List<Publication> getAllPublications() throws ServiceException;
    void updatePublication(Publication publication) throws ServiceException;
    void deletePublication(int id) throws ServiceException;

    void addExperimentToPublication(int experimentId, int publicationId) throws ServiceException;

    List<Experiment> getExperimentsForPublication(int publicationId) throws ServiceException;

    void updateExperimentForPublication(int publicationId, int oldExperimentId, int newExperimentId) throws ServiceException;

    void removeExperimentFromPublication(int experimentId, int publicationId) throws ServiceException;
}
