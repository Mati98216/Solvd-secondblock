package com.solvd.laba.dao.interfaces;

import com.solvd.laba.domain.ExperimentResult;

import java.sql.SQLException;
import java.util.List;

public interface ExperimentResultDAO {
    void addExperimentResult(ExperimentResult experimentResult) throws SQLException, InterruptedException;
    ExperimentResult getExperimentResultById(int id) throws SQLException;
    List<ExperimentResult> getAllExperimentResults() throws SQLException;
    void updateExperimentResult(ExperimentResult experimentResult) throws SQLException;
    void deleteExperimentResult(int id) throws SQLException;
}

