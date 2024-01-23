package com.solvd.laba.dao.impl;

import com.solvd.laba.dao.interfaces.ExperimentResultDAO;
import com.solvd.laba.database.ConnectionPool;
import com.solvd.laba.domain.Analysis;
import com.solvd.laba.domain.Experiment;
import com.solvd.laba.domain.ExperimentResult;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExperimentResultDAOImpl extends AbstractDAO<ExperimentResult, Integer> implements ExperimentResultDAO {

    public ExperimentResultDAOImpl(ConnectionPool connectionPool) {
        super(connectionPool);
    }

    @Override
    protected ExperimentResult createEntity(ResultSet resultSet) throws SQLException {
        return new ExperimentResult.Builder()
                .resultId(resultSet.getInt("result_id"))
                .experiment(createExperiment(resultSet))
                .analysis(createAnalysis(resultSet))
                .resultDetails(resultSet.getString("result_details"))
                .build();
    }

    private Experiment createExperiment(ResultSet resultSet) throws SQLException {
        Experiment experiment = new Experiment();
        experiment.setExperimentId(resultSet.getInt("experiment_id"));
        experiment.setExperimentName(resultSet.getString("experiment_name"));
        return experiment;
    }

    private Analysis createAnalysis(ResultSet resultSet) throws SQLException {
        Analysis analysis = new Analysis();
        analysis.setAnalysisId(resultSet.getInt("analysis_id"));
        analysis.setAnalysisName(resultSet.getString("analysis_name"));
        return analysis;
    }


    @Override
    protected String getCreateQuery() {
        return "INSERT INTO experiment_results (experiment_id, analysis_id, result_details) VALUES (?, ?, ?)";
    }

    @Override
    protected void setCreateStatement(PreparedStatement statement, ExperimentResult result) throws SQLException {
        statement.setInt(1, result.getExperiment().getExperimentId());
        statement.setInt(2, result.getAnalysis().getAnalysisId());
        statement.setString(3, result.getResultDetails());
    }

    @Override
    protected String getReadQuery() {
        return "SELECT er.*, e.experiment_name, a.analysis_name, " +
                "FROM experiment_results er " +
                "JOIN experiments e ON er.experiment_id = e.experiment_id " +
                "JOIN analysis a ON er.analysis_id = a.analysis_id " +
                "WHERE result_id = ?";
    }

    @Override
    protected void setReadStatement(PreparedStatement statement, Integer id) throws SQLException {
        statement.setInt(1, id);
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE experiment_results SET experiment_id = ?, analysis_id = ?, result_details = ? WHERE result_id = ?";
    }

    @Override
    protected void setUpdateStatement(PreparedStatement statement, ExperimentResult result) throws SQLException {
        statement.setInt(1, result.getExperiment().getExperimentId());
        statement.setInt(2, result.getAnalysis().getAnalysisId());
        statement.setString(3, result.getResultDetails());
        statement.setInt(4, result.getResultId());
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM experiment_results WHERE result_id = ?";
    }

    @Override
    protected void setDeleteStatement(PreparedStatement statement, Integer id) throws SQLException {
        statement.setInt(1, id);
    }

    @Override
    protected String getFindAllQuery() {
        return "SELECT er.*, e.experiment_name, a.analysis_name, " +
                "FROM experiment_results er " +
                "JOIN experiments e ON er.experiment_id = e.experiment_id " +
                "JOIN analysis a ON er.analysis_id = a.analysis_id ";
    }
}