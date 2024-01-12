package com.solvd.laba.dao.impl;

import com.solvd.laba.dao.interfaces.DepartmentDAO;
import com.solvd.laba.database.ConnectionPool;
import com.solvd.laba.domain.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAOImpl extends AbstractDAO<Department, Integer> implements DepartmentDAO {

    public DepartmentDAOImpl(ConnectionPool connectionPool) {
        super(connectionPool);
    }

    @Override
    protected Department createEntity(ResultSet resultSet) throws SQLException {
        Department department = new Department();
        department.setDepartmentId(resultSet.getInt("department_id"));
        department.setDepartmentName(resultSet.getString("department_name"));
        return department;
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO departments (department_name) VALUES (?)";
    }

    @Override
    protected void setCreateStatement(PreparedStatement statement, Department department) throws SQLException {
        statement.setString(1, department.getDepartmentName());
    }

    @Override
    protected String getReadQuery() {
        return "SELECT * FROM departments WHERE department_id = ?";
    }

    @Override
    protected void setReadStatement(PreparedStatement statement, Integer id) throws SQLException {
        statement.setInt(1, id);
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE departments SET department_name = ? WHERE department_id = ?";
    }

    @Override
    protected void setUpdateStatement(PreparedStatement statement, Department department) throws SQLException {
        statement.setString(1, department.getDepartmentName());
        statement.setInt(2, department.getDepartmentId());
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM departments WHERE department_id = ?";
    }

    @Override
    protected void setDeleteStatement(PreparedStatement statement, Integer id) throws SQLException {
        statement.setInt(1, id);
    }

    @Override
    protected String getFindAllQuery() {
        return "SELECT * FROM departments";
    }
}
