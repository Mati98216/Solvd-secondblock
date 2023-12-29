package com.solvd.laba.service.interfaces;

import com.solvd.laba.domain.ExperimentResult;

import java.util.List;

public interface ExperimentResultService {
    void addExperimentResult(ExperimentResult experimentResult);
    ExperimentResult getExperimentResultById(int id);
    List<ExperimentResult> getAllExperimentResults();
    void updateExperimentResult(ExperimentResult experimentResult);
    void deleteExperimentResult(int id);
}