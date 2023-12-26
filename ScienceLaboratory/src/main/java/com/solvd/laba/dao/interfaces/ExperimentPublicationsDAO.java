package com.solvd.laba.dao.interfaces;

import com.solvd.laba.domain.ExperimentPublications;

import java.sql.SQLException;
import java.util.List;

public interface ExperimentPublicationsDAO {
    void addExperimentPublications(ExperimentPublications experimentPublications) throws SQLException;
    ExperimentPublications getExperimentPublicationsById(int id) throws SQLException;
    List<ExperimentPublications> getAllExperimentPublications() throws SQLException;
    void updateExperimentPublications(ExperimentPublications experimentPublications) throws SQLException;
    void deleteExperimentPublications(int id) throws SQLException;
}
