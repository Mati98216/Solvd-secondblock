package com.solvd.laba.dao.impl;

import com.solvd.laba.database.ConnectionPool;
import com.solvd.laba.dao.interfaces.ExperimentResultDAO;
import com.solvd.laba.database.DatabaseConnection;
import com.solvd.laba.domain.ExperimentResult;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ExperimentResultDAOImpl implements ExperimentResultDAO {
    private ConnectionPool connectionPool;

    public ExperimentResultDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public void addExperimentResult(ExperimentResult experimentResult) throws SQLException {
        String sql = "INSERT INTO experiment_results (experiment_id, analysis_id, result_details) VALUES (?, ?, ?)";
        DatabaseConnection dbConnection = null;
        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();

            try (PreparedStatement statement = sqlConnection.prepareStatement(sql)) {
                statement.setInt(1, experimentResult.getExperimentId());
                statement.setInt(2, experimentResult.getAnalysisId());
                statement.setString(3, experimentResult.getResultDetails());
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
    public ExperimentResult getExperimentResultById(int id) throws SQLException {
        String sql = "SELECT * FROM experiment_results WHERE result_id = ?";
        ExperimentResult experimentResult = null;
        DatabaseConnection dbConnection = null;

        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();

            try (PreparedStatement statement = sqlConnection.prepareStatement(sql)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        experimentResult = new ExperimentResult(
                                resultSet.getInt("result_id"),
                                resultSet.getInt("experiment_id"),
                                resultSet.getInt("analysis_id"),
                                resultSet.getString("result_details")
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
        return experimentResult;
    }

    @Override
    public List<ExperimentResult> getAllExperimentResults() throws SQLException {
        List<ExperimentResult> experimentResults = new ArrayList<>();
        String sql = "SELECT * FROM experiment_results";
        DatabaseConnection dbConnection = null;

        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();

            try (PreparedStatement statement = sqlConnection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    experimentResults.add(new ExperimentResult(
                            resultSet.getInt("result_id"),
                            resultSet.getInt("experiment_id"),
                            resultSet.getInt("analysis_id"),
                            resultSet.getString("result_details")
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
        return experimentResults;
    }

    @Override
    public void updateExperimentResult(ExperimentResult experimentResult) throws SQLException {
        String sql = "UPDATE experiment_results SET experiment_id = ?, analysis_id = ?, result_details = ? WHERE result_id = ?";
        DatabaseConnection dbConnection = null;

        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();

            try (PreparedStatement statement = sqlConnection.prepareStatement(sql)) {
                statement.setInt(1, experimentResult.getExperimentId());
                statement.setInt(2, experimentResult.getAnalysisId());
                statement.setString(3, experimentResult.getResultDetails());
                statement.setInt(4, experimentResult.getResultId());
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
    public void deleteExperimentResult(int id) throws SQLException {
        String sql = "DELETE FROM experiment_results WHERE result_id = ?";
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
