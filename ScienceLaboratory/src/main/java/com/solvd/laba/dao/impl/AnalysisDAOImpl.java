package com.solvd.laba.dao.impl;

import com.solvd.laba.database.ConnectionPool;
import com.solvd.laba.dao.interfaces.AnalysisDAO;
import com.solvd.laba.database.DatabaseConnection;
import com.solvd.laba.domain.Analysis;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class AnalysisDAOImpl implements AnalysisDAO {
    private ConnectionPool connectionPool;

    public AnalysisDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public void addAnalysis(Analysis analysis) throws SQLException {
        String sql = "INSERT INTO analysis (analysis_name, scientist_id, assistant_id) VALUES (?, ?, ?)";
        DatabaseConnection dbConnection = null;
        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();

            try (PreparedStatement statement = sqlConnection.prepareStatement(sql)) {
                statement.setString(1, analysis.getAnalysisName());
                statement.setInt(2, analysis.getScientistId());
                statement.setInt(3, analysis.getAssistantId());
                statement.executeUpdate();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("Failed to get a connection from the pool", e);
        } finally {
            if (dbConnection != null) {
                connectionPool.releaseConnection(dbConnection);
            }
        }
    }

    @Override
    public Analysis getAnalysisById(int id) throws SQLException {
        String sql = "SELECT * FROM analysis WHERE analysis_id = ?";
        Analysis analysis = null;
        DatabaseConnection dbConnection = null;

        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();

            try (PreparedStatement statement = sqlConnection.prepareStatement(sql)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        analysis = new Analysis(
                                resultSet.getInt("analysis_id"),
                                resultSet.getString("analysis_name"),
                                resultSet.getInt("scientist_id"),
                                resultSet.getInt("assistant_id")
                        );
                    }
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("Failed to get a connection from the pool", e);
        } finally {
            if (dbConnection != null) {
                connectionPool.releaseConnection(dbConnection);
            }
        }
        return analysis;
    }

    @Override
    public List<Analysis> getAllAnalyses() throws SQLException {
        List<Analysis> analyses = new ArrayList<>();
        String sql = "SELECT * FROM analysis";
        DatabaseConnection dbConnection = null;

        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();

            try (PreparedStatement statement = sqlConnection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    analyses.add(new Analysis(
                            resultSet.getInt("analysis_id"),
                            resultSet.getString("analysis_name"),
                            resultSet.getInt("scientist_id"),
                            resultSet.getInt("assistant_id")
                    ));
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("Failed to get a connection from the pool", e);
        } finally {
            if (dbConnection != null) {
                connectionPool.releaseConnection(dbConnection);
            }
        }
        return analyses;
    }

    @Override
    public void updateAnalysis(Analysis analysis) throws SQLException {
        String sql = "UPDATE analysis SET analysis_name = ?, scientist_id = ?, assistant_id = ? WHERE analysis_id = ?";
        DatabaseConnection dbConnection = null;

        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();

            try (PreparedStatement statement = sqlConnection.prepareStatement(sql)) {
                statement.setString(1, analysis.getAnalysisName());
                statement.setInt(2, analysis.getScientistId());
                statement.setInt(3, analysis.getAssistantId());
                statement.setInt(4, analysis.getAnalysisId());
                statement.executeUpdate();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("Failed to get a connection from the pool", e);
        } finally {
            if (dbConnection != null) {
                connectionPool.releaseConnection(dbConnection);
            }
        }
    }

    @Override
    public void deleteAnalysis(int id) throws SQLException {
        String sql = "DELETE FROM analysis WHERE analysis_id = ?";
        DatabaseConnection dbConnection = null;

        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();

            try (PreparedStatement statement = sqlConnection.prepareStatement(sql)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("Failed to get a connection from the pool", e);
        } finally {
            if (dbConnection != null) {
                connectionPool.releaseConnection(dbConnection);
            }
        }
    }
}
