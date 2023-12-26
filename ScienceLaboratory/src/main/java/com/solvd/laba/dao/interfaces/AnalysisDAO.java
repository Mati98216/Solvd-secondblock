package com.solvd.laba.dao.interfaces;

import com.solvd.laba.domain.Analysis;

import java.sql.SQLException;
import java.util.List;

public interface AnalysisDAO {
    void addAnalysis(Analysis analysis) throws SQLException, InterruptedException;
    Analysis getAnalysisById(int id) throws SQLException;
    List<Analysis> getAllAnalyses() throws SQLException;
    void updateAnalysis(Analysis analysis) throws SQLException;
    void deleteAnalysis(int id) throws SQLException;
}
