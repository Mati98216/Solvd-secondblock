package com.solvd.laba.dao.impl;

import com.solvd.laba.database.ConnectionPool;
import com.solvd.laba.dao.interfaces.LaboratoryAssistantDAO;
import com.solvd.laba.database.DatabaseConnection;
import com.solvd.laba.domain.LaboratoryAssistant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




public class LaboratoryAssistantDAOImpl implements LaboratoryAssistantDAO {
    private ConnectionPool connectionPool;

    public LaboratoryAssistantDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public void addLaboratoryAssistant(LaboratoryAssistant laboratoryAssistant) throws SQLException {
        String sql = "INSERT INTO laboratory_assistants (name, email, department_id, area_id) VALUES (?, ?, ?, ?)";
        DatabaseConnection dbConnection = null;
        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();

            try (PreparedStatement statement = sqlConnection.prepareStatement(sql)) {
                statement.setString(1, laboratoryAssistant.getName());
                statement.setString(2, laboratoryAssistant.getEmail());
                statement.setInt(3, laboratoryAssistant.getDepartmentId());
                statement.setInt(4, laboratoryAssistant.getAreaId());
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
    public LaboratoryAssistant getLaboratoryAssistantById(int id) throws SQLException {
        String sql = "SELECT * FROM laboratory_assistants WHERE assistant_id = ?";
        LaboratoryAssistant laboratoryAssistant = null;
        DatabaseConnection dbConnection = null;

        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();

            try (PreparedStatement statement = sqlConnection.prepareStatement(sql)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        laboratoryAssistant = new LaboratoryAssistant(
                                resultSet.getInt("assistant_id"),
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
        return laboratoryAssistant;
    }

    @Override
    public List<LaboratoryAssistant> getAllLaboratoryAssistants() throws SQLException {
        List<LaboratoryAssistant> assistants = new ArrayList<>();
        String sql = "SELECT * FROM laboratory_assistants";
        DatabaseConnection dbConnection = null;

        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();

            try (PreparedStatement statement = sqlConnection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    assistants.add(new LaboratoryAssistant(
                            resultSet.getInt("assistant_id"),
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
        return assistants;
    }

    @Override
    public void updateLaboratoryAssistant(LaboratoryAssistant laboratoryAssistant) throws SQLException {
        String sql = "UPDATE laboratory_assistants SET name = ?, email = ?, department_id = ?, area_id = ? WHERE assistant_id = ?";
        DatabaseConnection dbConnection = null;

        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();

            try (PreparedStatement statement = sqlConnection.prepareStatement(sql)) {
                statement.setString(1, laboratoryAssistant.getName());
                statement.setString(2, laboratoryAssistant.getEmail());
                statement.setInt(3, laboratoryAssistant.getDepartmentId());
                statement.setInt(4, laboratoryAssistant.getAreaId());
                statement.setInt(5, laboratoryAssistant.getAssistantId());
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
    public void deleteLaboratoryAssistant(int id) throws SQLException {
        String sql = "DELETE FROM laboratory_assistants WHERE assistant_id = ?";
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
