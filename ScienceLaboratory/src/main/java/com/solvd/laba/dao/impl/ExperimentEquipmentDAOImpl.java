package com.solvd.laba.dao.impl;

import com.solvd.laba.database.ConnectionPool;
import com.solvd.laba.dao.interfaces.ExperimentEquipmentDAO;
import com.solvd.laba.database.DatabaseConnection;
import com.solvd.laba.domain.ExperimentEquipment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExperimentEquipmentDAOImpl implements ExperimentEquipmentDAO {
    private ConnectionPool connectionPool;

    public ExperimentEquipmentDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public void addExperimentEquipment(ExperimentEquipment experimentEquipment) throws SQLException {
        String sql = "INSERT INTO experiment_equipment (experiment_id, equipment_id) VALUES (?, ?)";
        DatabaseConnection dbConnection = null;
        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();

            try (PreparedStatement statement = sqlConnection.prepareStatement(sql)) {
                statement.setInt(1, experimentEquipment.getExperimentId());
                statement.setInt(2, experimentEquipment.getEquipmentId());
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
    public ExperimentEquipment getExperimentEquipmentById(int id) throws SQLException {
        String sql = "SELECT * FROM experiment_equipment WHERE experiment_equipment_id = ?";
        ExperimentEquipment experimentEquipment = null;
        DatabaseConnection dbConnection = null;

        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();

            try (PreparedStatement statement = sqlConnection.prepareStatement(sql)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        experimentEquipment = new ExperimentEquipment(
                                resultSet.getInt("experiment_equipment_id"),
                                resultSet.getInt("experiment_id"),
                                resultSet.getInt("equipment_id")
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
        return experimentEquipment;
    }

    @Override
    public List<ExperimentEquipment> getAllExperimentEquipments() throws SQLException {
        List<ExperimentEquipment> experimentEquipments = new ArrayList<>();
        String sql = "SELECT * FROM experiment_equipment";
        DatabaseConnection dbConnection = null;

        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();

            try (PreparedStatement statement = sqlConnection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    experimentEquipments.add(new ExperimentEquipment(
                            resultSet.getInt("experiment_equipment_id"),
                            resultSet.getInt("experiment_id"),
                            resultSet.getInt("equipment_id")
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
        return experimentEquipments;
    }

    @Override
    public void updateExperimentEquipment(ExperimentEquipment experimentEquipment) throws SQLException {
        String sql = "UPDATE experiment_equipment SET experiment_id = ?, equipment_id = ? WHERE experiment_equipment_id = ?";
        DatabaseConnection dbConnection = null;

        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();

            try (PreparedStatement statement = sqlConnection.prepareStatement(sql)) {
                statement.setInt(1, experimentEquipment.getExperimentId());
                statement.setInt(2, experimentEquipment.getEquipmentId());
                statement.setInt(3, experimentEquipment.getExperimentEquipmentId());
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
    public void deleteExperimentEquipment(int id) throws SQLException {
        String sql = "DELETE FROM experiment_equipment WHERE experiment_equipment_id = ?";
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
