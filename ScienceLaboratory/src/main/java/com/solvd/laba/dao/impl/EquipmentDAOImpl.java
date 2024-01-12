package com.solvd.laba.dao.impl;

import com.solvd.laba.dao.interfaces.EquipmentDAO;
import com.solvd.laba.database.ConnectionPool;
import com.solvd.laba.domain.Department;
import com.solvd.laba.domain.Equipment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EquipmentDAOImpl extends AbstractDAO<Equipment, Integer> implements EquipmentDAO {

    public EquipmentDAOImpl(ConnectionPool connectionPool) {
        super(connectionPool);
    }

    @Override
    protected Equipment createEntity(ResultSet resultSet) throws SQLException {
        Equipment equipment = new Equipment();
        equipment.setEquipmentId(resultSet.getInt("equipment_id"));
        equipment.setEquipmentName(resultSet.getString("equipment_name"));
        equipment.setDepartment(createDepartment(resultSet));
        return equipment;
    }

    private Department createDepartment(ResultSet resultSet) throws SQLException {
        Department department = new Department();
        department.setDepartmentId(resultSet.getInt("department_id"));
        department.setDepartmentName(resultSet.getString("department_name"));
        return department;
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO equipment (equipment_name, department_id) VALUES (?, ?)";
    }

    @Override
    protected void setCreateStatement(PreparedStatement statement, Equipment equipment) throws SQLException {
        statement.setString(1, equipment.getEquipmentName());
        statement.setInt(2, equipment.getDepartment().getDepartmentId());
    }

    @Override
    protected String getReadQuery() {
        return "SELECT e.*, d.department_name " +
                "FROM equipment e " +
                "JOIN departments d ON e.department_id = d.department_id " +
                "WHERE equipment_id = ?";
    }

    @Override
    protected void setReadStatement(PreparedStatement statement, Integer id) throws SQLException {
        statement.setInt(1, id);
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE equipment SET equipment_name = ?, department_id = ? WHERE equipment_id = ?";
    }

    @Override
    protected void setUpdateStatement(PreparedStatement statement, Equipment equipment) throws SQLException {
        statement.setString(1, equipment.getEquipmentName());
        statement.setInt(2, equipment.getDepartment().getDepartmentId());
        statement.setInt(3, equipment.getEquipmentId());
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM equipment WHERE equipment_id = ?";
    }

    @Override
    protected void setDeleteStatement(PreparedStatement statement, Integer id) throws SQLException {
        statement.setInt(1, id);
    }

    @Override
    protected String getFindAllQuery() {
        return "SELECT e.*, d.department_name " +
                "FROM equipment e " +
                "JOIN departments d ON e.department_id = d.department_id";
    }
}
