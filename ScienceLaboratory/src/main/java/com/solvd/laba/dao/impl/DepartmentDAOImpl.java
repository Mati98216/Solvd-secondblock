package com.solvd.laba.dao.impl;

import com.solvd.laba.database.ConnectionPool;
import com.solvd.laba.dao.interfaces.DepartmentDAO;
import com.solvd.laba.database.DatabaseConnection;
import com.solvd.laba.domain.Department;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class DepartmentDAOImpl implements DepartmentDAO {
    private ConnectionPool connectionPool;

    public DepartmentDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public void addDepartment(Department department) throws SQLException {
        String sql = "INSERT INTO departments (department_name) VALUES (?)";
        DatabaseConnection dbConnection = null;
        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();

            try (PreparedStatement statement = sqlConnection.prepareStatement(sql)) {
                statement.setString(1, department.getDepartmentName());
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
    public Department getDepartmentById(int id) throws SQLException {
        String sql = "SELECT * FROM departments WHERE department_id = ?";
        Department department = null;
        DatabaseConnection dbConnection = null;

        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();

            try (PreparedStatement statement = sqlConnection.prepareStatement(sql)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        department = new Department(
                                resultSet.getInt("department_id"),
                                resultSet.getString("department_name")
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
        return department;
    }

    @Override
    public List<Department> getAllDepartments() throws SQLException {
        List<Department> departments = new ArrayList<>();
        String sql = "SELECT * FROM departments";
        DatabaseConnection dbConnection = null;

        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();

            try (PreparedStatement statement = sqlConnection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    departments.add(new Department(
                            resultSet.getInt("department_id"),
                            resultSet.getString("department_name")
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
        return departments;
    }

    @Override
    public void updateDepartment(Department department) throws SQLException {
        String sql = "UPDATE departments SET department_name = ? WHERE department_id = ?";
        DatabaseConnection dbConnection = null;

        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();

            try (PreparedStatement statement = sqlConnection.prepareStatement(sql)) {
                statement.setString(1, department.getDepartmentName());
                statement.setInt(2, department.getDepartmentId());
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
    public void deleteDepartment(int id) throws SQLException {
        String sql = "DELETE FROM departments WHERE department_id = ?";
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
