package com.solvd.laba.dao.impl;

import com.solvd.laba.dao.interfaces.PublicationDAO;
import com.solvd.laba.database.ConnectionPool;
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
        scientist.setScientistId(resultSet.getInt("scientist_id"));
        scientist.setName(resultSet.getString("name"));
        scientist.setEmail(resultSet.getString("email"));
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
        return "SELECT * FROM publications"+
                "LEFT JOIN scientists s ON p.scientist_id = s.scientist_id"
                +" WHERE publication_id = ?";
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
    public List<Publication> findAll() throws SQLException {
        List<Publication> publications = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection().getSqlConnection();
             PreparedStatement statement = connection.prepareStatement(getFindAllQuery())) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                publications.add(createEntity(resultSet));
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("Operation interrupted", e);
        }
        return publications;
    }

    @Override
    protected String getFindAllQuery() {
        return "SELECT p.*, s.name, s.email, s.department_id, s.area_id " +
                "FROM publications p " +
                "LEFT JOIN scientists s ON p.scientist_id = s.scientist_id";
    }

}

