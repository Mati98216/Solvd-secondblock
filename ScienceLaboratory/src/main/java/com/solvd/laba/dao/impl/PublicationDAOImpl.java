package com.solvd.laba.dao.impl;

import com.solvd.laba.database.ConnectionPool;
import com.solvd.laba.dao.interfaces.PublicationDAO;
import com.solvd.laba.database.DatabaseConnection;
import com.solvd.laba.domain.Publication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class PublicationDAOImpl implements PublicationDAO {
    private ConnectionPool connectionPool;

    public PublicationDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public void addPublication(Publication publication) throws SQLException {
        String sql = "INSERT INTO publications (publication_title, publication_date, scientist_id) VALUES (?, ?, ?)";
        DatabaseConnection dbConnection = null;
        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();

            try (PreparedStatement statement = sqlConnection.prepareStatement(sql)) {
                statement.setString(1, publication.getPublicationTitle());
                statement.setDate(2, publication.getPublicationDate());
                statement.setInt(3, publication.getScientistId());
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
    public Publication getPublicationById(int id) throws SQLException {
        String sql = "SELECT * FROM publications WHERE publication_id = ?";
        Publication publication = null;
        DatabaseConnection dbConnection = null;

        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();

            try (PreparedStatement statement = sqlConnection.prepareStatement(sql)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        publication = new Publication(
                                resultSet.getInt("publication_id"),
                                resultSet.getString("publication_title"),
                                resultSet.getDate("publication_date"),
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
        return publication;
    }

    @Override
    public List<Publication> getAllPublications() throws SQLException {
        List<Publication> publications = new ArrayList<>();
        String sql = "SELECT * FROM publications";
        DatabaseConnection dbConnection = null;

        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();

            try (PreparedStatement statement = sqlConnection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    publications.add(new Publication(
                            resultSet.getInt("publication_id"),
                            resultSet.getString("publication_title"),
                            resultSet.getDate("publication_date"),
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
        return publications;
    }

    @Override
    public void updatePublication(Publication publication) throws SQLException {
        String sql = "UPDATE publications SET publication_title = ?, publication_date = ?, scientist_id = ? WHERE publication_id = ?";
        DatabaseConnection dbConnection = null;

        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();

            try (PreparedStatement statement = sqlConnection.prepareStatement(sql)) {
                statement.setString(1, publication.getPublicationTitle());
                statement.setDate(2, publication.getPublicationDate());
                statement.setInt(3, publication.getScientistId());
                statement.setInt(4, publication.getPublicationId());
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
    public void deletePublication(int id) throws SQLException {
        String sql = "DELETE FROM publications WHERE publication_id = ?";
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

