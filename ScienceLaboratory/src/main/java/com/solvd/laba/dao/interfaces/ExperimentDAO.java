package com.solvd.laba.dao.interfaces;

import com.solvd.laba.domain.Experiment;

import java.sql.SQLException;
import java.util.List;

public interface ExperimentDAO {
    void addExperiment(Experiment experiment) throws SQLException, InterruptedException;
    Experiment getExperimentById(int id) throws SQLException;
    List<Experiment> getAllExperiments() throws SQLException;
    void updateExperiment(Experiment experiment) throws SQLException;
    void deleteExperiment(int id) throws SQLException;
}