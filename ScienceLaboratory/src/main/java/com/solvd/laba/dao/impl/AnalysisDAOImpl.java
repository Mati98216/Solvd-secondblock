package com.solvd.laba.dao.impl;

import com.solvd.laba.dao.interfaces.AnalysisDAO;
import com.solvd.laba.database.ConnectionPool;
import com.solvd.laba.domain.Analysis;
import com.solvd.laba.domain.LaboratoryAssistant;
import com.solvd.laba.domain.Scientist;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnalysisDAOImpl extends AbstractDAO<Analysis, Integer> implements AnalysisDAO {

    public AnalysisDAOImpl(ConnectionPool connectionPool) {
        super(connectionPool);
    }

    @Override
    protected Analysis createEntity(ResultSet resultSet) throws SQLException {
        Analysis analysis = new Analysis();
        analysis.setAnalysisId(resultSet.getInt("analysis_id"));
        analysis.setAnalysisName(resultSet.getString("analysis_description"));
        analysis.setScientist(createScientist(resultSet));
        analysis.setAssistant(createLaboratoryAssistant(resultSet));
        return analysis;
    }

    private Scientist createScientist(ResultSet resultSet) throws SQLException {
        Scientist scientist = new Scientist();
        scientist.setScientistId(resultSet.getInt("scientist_id"));
        scientist.setName(resultSet.getString("scientist_name"));
        scientist.setEmail(resultSet.getString("scientist_email"));
        return scientist;
    }

    private LaboratoryAssistant createLaboratoryAssistant(ResultSet resultSet) throws SQLException {
        LaboratoryAssistant assistant = new LaboratoryAssistant();
        assistant.setAssistantId(resultSet.getInt("assistant_id"));
        assistant.setName(resultSet.getString("assistant_name"));
        assistant.setEmail(resultSet.getString("assistant_email"));
        return assistant;
    }



    @Override
    protected String getCreateQuery() {
        return "INSERT INTO analysis (analysis_description, scientist_id, assistant_id) VALUES (?, ?, ?)";
    }

    @Override
    protected void setCreateStatement(PreparedStatement statement, Analysis analysis) throws SQLException {
        statement.setString(1, analysis.getAnalysisName());
        statement.setInt(2, analysis.getScientist().getScientistId());
        statement.setInt(3, analysis.getAssistant().getAssistantId());
    }

    @Override
    protected String getReadQuery() {
        return "SELECT a.*, s.scientist_id, s.name as scientist_name, s.email as scientist_email, " +
                "la.assistant_id, la.name as assistant_name, la.email as assistant_email " +
                "FROM analysis a " +
                "JOIN scientists s ON a.scientist_id = s.scientist_id " +
                "JOIN laboratory_assistants la ON a.assistant_id = la.assistant_id " +
                "WHERE analysis_id = ?";
    }

    @Override
    protected void setReadStatement(PreparedStatement statement, Integer id) throws SQLException {
        statement.setInt(1, id);
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE analysis SET analysis_description = ?, scientist_id = ?, assistant_id = ? WHERE analysis_id = ?";
    }

    @Override
    protected void setUpdateStatement(PreparedStatement statement, Analysis analysis) throws SQLException {
        statement.setString(1, analysis.getAnalysisName());
        statement.setInt(2, analysis.getScientist().getScientistId());
        statement.setInt(3, analysis.getAssistant().getAssistantId());
        statement.setInt(4, analysis.getAnalysisId());
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM analysis WHERE analysis_id = ?";
    }

    @Override
    protected void setDeleteStatement(PreparedStatement statement, Integer id) throws SQLException {
        statement.setInt(1, id);
    }

    @Override
    protected String getFindAllQuery() {
        return "SELECT a.*, s.scientist_id, s.name as scientist_name, s.email as scientist_email, " +
                "la.assistant_id, la.name as assistant_name, la.email as assistant_email, " +
                "FROM analysis a " +
                "JOIN scientists s ON a.scientist_id = s.scientist_id " +
                "JOIN laboratory_assistants la ON a.assistant_id = la.assistant_id ";
    }
}
