package com.solvd.laba.service.interfaces;

import com.solvd.laba.domain.Analysis;

import java.util.List;

public interface AnalysisService {
    void addAnalysis(Analysis analysis);
    Analysis getAnalysisById(int id);
    List<Analysis> getAllAnalysis();
    void updateAnalysis(Analysis analysis);
    void deleteAnalysis(int id);
}

