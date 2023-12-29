package com.solvd.laba.service.interfaces;

import com.solvd.laba.domain.Experiment;

import java.util.List;

public interface ExperimentService {
    void addExperiment(Experiment experiment);
    Experiment getExperimentById(int id);
    List<Experiment> getAllExperiments();
    void updateExperiment(Experiment experiment);
    void deleteExperiment(int id);
}
