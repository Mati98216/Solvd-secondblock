package com.solvd.laba.service.interfaces;

import com.google.protobuf.ServiceException;
import com.solvd.laba.domain.ExperimentPublications;

import java.util.List;

public interface ExperimentPublicationsService {
    void addExperimentPublications(ExperimentPublications experimentPublications) throws ServiceException;
    ExperimentPublications getExperimentPublicationsById(int id) throws ServiceException;
    List<ExperimentPublications> getAllExperimentPublications() throws ServiceException;
    void updateExperimentPublications(ExperimentPublications experimentPublications) throws ServiceException;
    void deleteExperimentPublications(int id) throws ServiceException;
}
