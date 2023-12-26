package com.solvd.laba.dao.impl;

import com.solvd.laba.database.ConnectionPool;
import com.solvd.laba.dao.interfaces.ScientistDAO;
import com.solvd.laba.database.DatabaseConnection;
import com.solvd.laba.domain.Scientist;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScientistDAOImpl implements ScientistDAO {
    private ConnectionPool connectionPool;

    public ScientistDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public void addScientist(Scientist scientist) throws SQLException {
        String sql = "INSERT INTO scientists (name, email, department_id, area_id) VALUES (?, ?, ?, ?)";
        DatabaseConnection dbConnection = null;
        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();

            try (PreparedStatement statement = sqlConnection.prepareStatement(sql)) {
                statement.setString(1, scientist.getName());
                statement.setString(2, scientist.getEmail());
                statement.setInt(3, scientist.getDepartmentId());
                statement.setInt(4, scientist.getAreaId());
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
    public Scientist getScientistById(int id) throws SQLException {
        String sql = "SELECT * FROM scientists WHERE scientist_id = ?";
        DatabaseConnection dbConnection = null;
        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();

            try (PreparedStatement statement = sqlConnection.prepareStatement(sql)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return new Scientist(
                                resultSet.getInt("scientist_id"),
                                resultSet.getString("name"),
                                resultSet.getString("email"),
                                resultSet.getInt("department_id"),
                                resultSet.getInt("area_id")
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
        return null;
    }

    @Override
    public List<Scientist> getAllScientists() throws SQLException {
        List<Scientist> scientists = new ArrayList<>();
        String sql = "SELECT * FROM scientists";
        DatabaseConnection dbConnection = null;
        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();

            try (PreparedStatement statement = sqlConnection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    scientists.add(new Scientist(
                            resultSet.getInt("scientist_id"),
                            resultSet.getString("name"),
                            resultSet.getString("email"),
                            resultSet.getInt("department_id"),
                            resultSet.getInt("area_id")
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
        return scientists;
    }

    @Override
    public void updateScientist(Scientist scientist) throws SQLException {
        String sql = "UPDATE scientists SET name = ?, email = ?, department_id = ?, area_id = ? WHERE scientist_id = ?";
        DatabaseConnection dbConnection = null;
        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();

            try (PreparedStatement statement = sqlConnection.prepareStatement(sql)) {
                statement.setString(1, scientist.getName());
                statement.setString(2, scientist.getEmail());
                statement.setInt(3, scientist.getDepartmentId());
                statement.setInt(4, scientist.getAreaId());
                statement.setInt(5, scientist.getScientistId());
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
    public void deleteScientist(int id) throws SQLException {
        String sql = "DELETE FROM scientists WHERE scientist_id = ?";
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

