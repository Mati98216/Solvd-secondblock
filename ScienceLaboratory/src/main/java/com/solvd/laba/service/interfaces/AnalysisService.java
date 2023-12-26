package com.solvd.laba.service.interfaces;

import com.google.protobuf.ServiceException;
import com.solvd.laba.domain.Analysis;

import java.util.List;

public interface AnalysisService {
    void addAnalysis(Analysis analysis) throws ServiceException;
    Analysis getAnalysisById(int id) throws ServiceException;
    List<Analysis> getAllAnalyses() throws ServiceException;
    void updateAnalysis(Analysis analysis) throws ServiceException;
    void deleteAnalysis(int id) throws ServiceException;
}
