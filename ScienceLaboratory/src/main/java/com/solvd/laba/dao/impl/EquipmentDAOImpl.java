package com.solvd.laba.dao.impl;

import com.solvd.laba.database.ConnectionPool;
import com.solvd.laba.dao.interfaces.EquipmentDAO;
import com.solvd.laba.database.DatabaseConnection;
import com.solvd.laba.domain.Equipment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipmentDAOImpl implements EquipmentDAO {
    private ConnectionPool connectionPool;

    public EquipmentDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public void addEquipment(Equipment equipment) throws SQLException {
        String sql = "INSERT INTO equipment (equipment_name, department_id) VALUES (?, ?)";
        DatabaseConnection dbConnection = null;
        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();

            try (PreparedStatement statement = sqlConnection.prepareStatement(sql)) {
                statement.setString(1, equipment.getEquipmentName());
                statement.setInt(2, equipment.getDepartmentId());
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
    public Equipment getEquipmentById(int id) throws SQLException {
        String sql = "SELECT * FROM equipment WHERE equipment_id = ?";
        Equipment equipment = null;
        DatabaseConnection dbConnection = null;

        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();

            try (PreparedStatement statement = sqlConnection.prepareStatement(sql)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        equipment = new Equipment(
                                resultSet.getInt("equipment_id"),
                                resultSet.getString("equipment_name"),
                                resultSet.getInt("department_id")
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
        return equipment;
    }

    @Override
    public List<Equipment> getAllEquipment() throws SQLException {
        List<Equipment> equipmentList = new ArrayList<>();
        String sql = "SELECT * FROM equipment";
        DatabaseConnection dbConnection = null;

        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();

            try (PreparedStatement statement = sqlConnection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    equipmentList.add(new Equipment(
                            resultSet.getInt("equipment_id"),
                            resultSet.getString("equipment_name"),
                            resultSet.getInt("department_id")
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
        return equipmentList;
    }

    @Override
    public void updateEquipment(Equipment equipment) throws SQLException {
        String sql = "UPDATE equipment SET equipment_name = ?, department_id = ? WHERE equipment_id = ?";
        DatabaseConnection dbConnection = null;

        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();

            try (PreparedStatement statement = sqlConnection.prepareStatement(sql)) {
                statement.setString(1, equipment.getEquipmentName());
                statement.setInt(2, equipment.getDepartmentId());
                statement.setInt(3, equipment.getEquipmentId());
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
    public void deleteEquipment(int id) throws SQLException {
        String sql = "DELETE FROM equipment WHERE equipment_id = ?";
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
