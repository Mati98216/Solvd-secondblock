package com.solvd.laba.service.interfaces;

import com.solvd.laba.domain.ExperimentPublications;

import java.util.List;

public interface ExperimentPublicationsService {
    void addExperimentPublications(ExperimentPublications experimentPublications);
    ExperimentPublications getExperimentPublicationsById(int id);
    List<ExperimentPublications> getAllExperimentPublications();
    void updateExperimentPublications(ExperimentPublications experimentPublications);
    void deleteExperimentPublications(int id);
}