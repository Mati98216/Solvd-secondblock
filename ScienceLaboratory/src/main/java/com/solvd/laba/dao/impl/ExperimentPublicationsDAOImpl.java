package com.solvd.laba.dao.impl;

import com.solvd.laba.database.ConnectionPool;
import com.solvd.laba.dao.interfaces.ExperimentPublicationsDAO;
import com.solvd.laba.database.DatabaseConnection;
import com.solvd.laba.domain.ExperimentPublications;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExperimentPublicationsDAOImpl implements ExperimentPublicationsDAO {
    private ConnectionPool connectionPool;

    public ExperimentPublicationsDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public void addExperimentPublications(ExperimentPublications experimentPublications) throws SQLException {
        String sql = "INSERT INTO experiment_publications (experiment_id, publication_id) VALUES (?, ?)";
        DatabaseConnection dbConnection = null;
        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();

            try (PreparedStatement statement = sqlConnection.prepareStatement(sql)) {
                statement.setInt(1, experimentPublications.getExperimentId());
                statement.setInt(2, experimentPublications.getPublicationId());
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
    public ExperimentPublications getExperimentPublicationsById(int id) throws SQLException {
        String sql = "SELECT * FROM experiment_publications WHERE experiment_publications_id = ?";
        ExperimentPublications experimentPublications = null;
        DatabaseConnection dbConnection = null;

        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();

            try (PreparedStatement statement = sqlConnection.prepareStatement(sql)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        experimentPublications = new ExperimentPublications(
                                resultSet.getInt("experiment_publications_id"),
                                resultSet.getInt("experiment_id"),
                                resultSet.getInt("publication_id")
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
        return experimentPublications;
    }

    @Override
    public List<ExperimentPublications> getAllExperimentPublications() throws SQLException {
        List<ExperimentPublications> experimentPublicationsList = new ArrayList<>();
        String sql = "SELECT * FROM experiment_publications";
        DatabaseConnection dbConnection = null;

        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();

            try (PreparedStatement statement = sqlConnection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    experimentPublicationsList.add(new ExperimentPublications(
                            resultSet.getInt("experiment_publications_id"),
                            resultSet.getInt("experiment_id"),
                            resultSet.getInt("publication_id")
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
        return experimentPublicationsList;
    }

    @Override
    public void updateExperimentPublications(ExperimentPublications experimentPublications) throws SQLException {
        String sql = "UPDATE experiment_publications SET experiment_id = ?, publication_id = ? WHERE experiment_publications_id = ?";
        DatabaseConnection dbConnection = null;

        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();

            try (PreparedStatement statement = sqlConnection.prepareStatement(sql)) {
                statement.setInt(1, experimentPublications.getExperimentId());
                statement.setInt(2, experimentPublications.getPublicationId());
                statement.setInt(3, experimentPublications.getExperimentPublicationsId());
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
    public void deleteExperimentPublications(int id) throws SQLException {
        String sql = "DELETE FROM experiment_publications WHERE experiment_publications_id = ?";
        DatabaseConnection dbConnection = null;

        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();

            try (PreparedStatement statement = sqlConnection.prepareStatement(sql)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (InterruptedException e) {
            throw new  RuntimeException("Failed to get a connection from the pool", e);
        } finally {
            if (dbConnection != null) {
                connectionPool.releaseConnection(dbConnection);
            }
        }
    }
}

