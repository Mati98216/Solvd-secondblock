package com.solvd.laba.dao.impl;

import com.solvd.laba.database.ConnectionPool;
import com.solvd.laba.dao.interfaces.ExperimentDAO;
import com.solvd.laba.database.DatabaseConnection;
import com.solvd.laba.domain.Experiment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExperimentDAOImpl implements ExperimentDAO {
    private ConnectionPool connectionPool;

    public ExperimentDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public void addExperiment(Experiment experiment) throws SQLException {
        String sql = "INSERT INTO experiments (experiment_name, scientist_id) VALUES (?, ?)";
        DatabaseConnection dbConnection = null;
        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();

            try (PreparedStatement statement = sqlConnection.prepareStatement(sql)) {
                statement.setString(1, experiment.getExperimentName());
                statement.setInt(2, experiment.getScientistId());
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
    public Experiment getExperimentById(int id) throws SQLException {
        String sql = "SELECT * FROM experiments WHERE experiment_id = ?";
        Experiment experiment = null;
        DatabaseConnection dbConnection = null;

        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();

            try (PreparedStatement statement = sqlConnection.prepareStatement(sql)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        experiment = new Experiment(
                                resultSet.getInt("experiment_id"),
                                resultSet.getString("experiment_name"),
                                resultSet.getInt("scientist_id")
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
        return experiment;
    }

    @Override
    public List<Experiment> getAllExperiments() throws SQLException {
        List<Experiment> experiments = new ArrayList<>();
        String sql = "SELECT * FROM experiments";
        DatabaseConnection dbConnection = null;

        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();

            try (PreparedStatement statement = sqlConnection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    experiments.add(new Experiment(
                            resultSet.getInt("experiment_id"),
                            resultSet.getString("experiment_name"),
                            resultSet.getInt("scientist_id")
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
        return experiments;
    }

    @Override
    public void updateExperiment(Experiment experiment) throws SQLException {
        String sql = "UPDATE experiments SET experiment_name = ?, scientist_id = ? WHERE experiment_id = ?";
        DatabaseConnection dbConnection = null;

        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();

            try (PreparedStatement statement = sqlConnection.prepareStatement(sql)) {
                statement.setString(1, experiment.getExperimentName());
                statement.setInt(2, experiment.getScientistId());
                statement.setInt(3, experiment.getExperimentId());
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
    public void deleteExperiment(int id) throws SQLException {
        String sql = "DELETE FROM experiments WHERE experiment_id = ?";
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
