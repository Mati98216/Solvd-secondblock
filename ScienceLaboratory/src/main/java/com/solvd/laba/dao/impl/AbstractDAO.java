package com.solvd.laba.dao.impl;

import com.solvd.laba.dao.interfaces.GenericDAO;
import com.solvd.laba.dao.interfaces.IdentifiableEntity;
import com.solvd.laba.database.ConnectionPool;
import com.solvd.laba.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDAO<T, ID> implements GenericDAO<T, ID> {

    protected ConnectionPool connectionPool;

    public AbstractDAO(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    protected abstract T createEntity(ResultSet resultSet) throws SQLException;

    protected abstract String getCreateQuery();
    protected abstract void setCreateStatement(PreparedStatement statement, T entity) throws SQLException;

    protected abstract String getReadQuery();
    protected abstract void setReadStatement(PreparedStatement statement, ID id) throws SQLException;

    protected abstract String getUpdateQuery();
    protected abstract void setUpdateStatement(PreparedStatement statement, T entity) throws SQLException;

    protected abstract String getDeleteQuery();
    protected abstract void setDeleteStatement(PreparedStatement statement, ID id) throws SQLException;
    protected abstract String getFindAllQuery();
    @Override
    public T create(T entity) throws SQLException, InterruptedException {
        DatabaseConnection dbConnection = connectionPool.getConnection();
        try (Connection connection = dbConnection.getSqlConnection();
             PreparedStatement statement = connection.prepareStatement(getCreateQuery(), PreparedStatement.RETURN_GENERATED_KEYS)) {

            setCreateStatement(statement, entity);
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating entity failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    if (entity instanceof IdentifiableEntity) {
                        ((IdentifiableEntity) entity).setId((Number) generatedKeys.getObject(1));
                    }
                    return entity;
                } else {
                    throw new SQLException("Creating entity failed, no ID obtained.");
                }
            }
        } finally {
            connectionPool.releaseConnection(dbConnection);
        }
    }
    @Override
    public List<T> findAll() throws SQLException, InterruptedException {
        List<T> entities = new ArrayList<>();
        DatabaseConnection dbConnection = connectionPool.getConnection();
        try (Connection connection = dbConnection.getSqlConnection();
             PreparedStatement statement = connection.prepareStatement(getFindAllQuery())) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                entities.add(createEntity(resultSet));
            }
        }
        return entities;
    }


    @Override
    public T read(ID id) throws SQLException, InterruptedException {
        DatabaseConnection dbConnection = connectionPool.getConnection();
        try (Connection connection = dbConnection.getSqlConnection(); // Correctly retrieve the SQL connection
             PreparedStatement statement = connection.prepareStatement(getReadQuery())) {

            setReadStatement(statement, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return createEntity(resultSet);
            } else {
                return null;
            }
        } finally {
            connectionPool.releaseConnection(dbConnection); // Make sure to release the connection
        }
    }

    @Override
    public T update(T entity) throws SQLException, InterruptedException {
        DatabaseConnection dbConnection = connectionPool.getConnection();
        try (Connection connection = dbConnection.getSqlConnection();
             PreparedStatement statement = connection.prepareStatement(getUpdateQuery())) {

            setUpdateStatement(statement, entity);
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating entity failed, no rows affected.");
            }

            return entity;
        }
    }

    @Override
    public void delete(ID id) throws SQLException, InterruptedException {
        DatabaseConnection dbConnection = connectionPool.getConnection();
        try (Connection connection = dbConnection.getSqlConnection();
             PreparedStatement statement = connection.prepareStatement(getDeleteQuery())) {

            setDeleteStatement(statement, id);
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Deleting entity failed, no rows affected.");
            }
        }
    }
}
