package com.solvd.laba.dao.impl;

import com.solvd.laba.dao.interfaces.PublicationDAO;
import com.solvd.laba.database.ConnectionPool;
import com.solvd.laba.domain.Experiment;
import com.solvd.laba.domain.Publication;
import com.solvd.laba.domain.Scientist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PublicationDAOImpl extends AbstractDAO<Publication, Integer> implements PublicationDAO {

    public PublicationDAOImpl(ConnectionPool connectionPool) {
        super(connectionPool);
    }

    @Override
    protected Publication createEntity(ResultSet resultSet) throws SQLException {
        Publication publication = new Publication();
        publication.setPublicationId(resultSet.getInt("publication_id"));
        publication.setPublicationTitle(resultSet.getString("publication_title"));
        publication.setPublicationDate(resultSet.getDate("publication_date"));

        Scientist scientist = new Scientist();
        scientist.setName(resultSet.getString("name"));
        publication.setScientist(scientist);

        return publication;
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO publications (publication_title, publication_date, scientist_id) VALUES (?, ?, ?)";
    }

    @Override
    protected void setCreateStatement(PreparedStatement statement, Publication publication) throws SQLException {
        statement.setString(1, publication.getPublicationTitle());
        statement.setDate(2, new java.sql.Date(publication.getPublicationDate().getTime()));
        statement.setInt(3, publication.getScientist().getScientistId());
    }

    @Override
    protected String getReadQuery() {
        return "SELECT p.*, s.*, d.department_id, d.department_name " +
                "FROM publications p " +
                "JOIN scientists s ON p.scientist_id = s.scientist_id " +
                "LEFT JOIN departments d ON s.department_id = d.department_id " +
                "WHERE publication_id = ?";
    }

    @Override
    protected void setReadStatement(PreparedStatement statement, Integer id) throws SQLException {
        statement.setInt(1, id);
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE publications SET publication_title = ?, publication_date = ?, scientist_id = ? WHERE publication_id = ?";
    }

    @Override
    protected void setUpdateStatement(PreparedStatement statement, Publication publication) throws SQLException {
        statement.setString(1, publication.getPublicationTitle());
        statement.setDate(2, new java.sql.Date(publication.getPublicationDate().getTime()));
        statement.setInt(3, publication.getScientist().getScientistId());
        statement.setInt(4, publication.getPublicationId());
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM publications WHERE publication_id = ?";
    }

    @Override
    protected void setDeleteStatement(PreparedStatement statement, Integer id) throws SQLException {
        statement.setInt(1, id);
    }

    @Override
    protected String getFindAllQuery() {
        return "SELECT p.*, s.*, d.department_id, d.department_name " +
                "FROM publications p " +
                "JOIN scientists s ON p.scientist_id = s.scientist_id " +
                "LEFT JOIN departments d ON s.department_id = d.department_id";
    }
    @Override
    public void addExperimentToPublication(int publicationId, int experimentId) throws SQLException, InterruptedException {
        try (Connection connection = connectionPool.getConnection().getSqlConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO experiment_publications (publication_id, experiment_id) VALUES (?, ?)")) {
            statement.setInt(1, publicationId);
            statement.setInt(2, experimentId);
            statement.executeUpdate();
        }
    }

    @Override
    public List<Experiment> getExperimentsForPublication(int publicationId) throws SQLException {
        List<Experiment> experiments = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection().getSqlConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT e.* FROM experiments e " +
                             "JOIN experiment_publications ep ON e.experiment_id = ep.experiment_id " +
                             "WHERE ep.publication_id = ?")) {
            statement.setInt(1, publicationId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Experiment experiment = new Experiment();
                    experiment.setExperimentId(resultSet.getInt("experiment_id"));
                    experiment.setExperimentName(resultSet.getString("experiment_name"));
                    experiments.add(experiment);
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return experiments;
    }

    @Override
    public void updateExperimentForPublication(int publicationId, int oldExperimentId, int newExperimentId) throws SQLException {
        try (Connection connection = connectionPool.getConnection().getSqlConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE experiment_publications SET experiment_id = ? WHERE publication_id = ? AND experiment_id = ?")) {
            statement.setInt(1, newExperimentId);
            statement.setInt(2, publicationId);
            statement.setInt(3, oldExperimentId);
            statement.executeUpdate();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeExperimentFromPublication(int publicationId, int experimentId) throws SQLException, InterruptedException {
        try (Connection connection = connectionPool.getConnection().getSqlConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM experiment_publications WHERE publication_id = ? AND experiment_id = ?")) {
            statement.setInt(1, publicationId);
            statement.setInt(2, experimentId);
            statement.executeUpdate();
        }
    }
}

