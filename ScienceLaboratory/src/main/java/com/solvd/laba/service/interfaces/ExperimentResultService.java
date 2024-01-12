package com.solvd.laba.service.interfaces;

import com.google.protobuf.ServiceException;
import com.solvd.laba.domain.ExperimentResult;

import java.util.List;

public interface ExperimentResultService {
    void addExperimentResult(ExperimentResult experimentResult) throws ServiceException;
    ExperimentResult getExperimentResultById(int id) throws ServiceException;
    List<ExperimentResult> getAllExperimentResults() throws ServiceException;
    void updateExperimentResult(ExperimentResult experimentResult) throws ServiceException;
    void deleteExperimentResult(int id) throws ServiceException;
}