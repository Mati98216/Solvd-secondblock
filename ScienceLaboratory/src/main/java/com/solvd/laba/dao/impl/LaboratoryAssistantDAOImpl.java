package com.solvd.laba.dao.impl;

import com.solvd.laba.dao.interfaces.LaboratoryAssistantDAO;
import com.solvd.laba.database.ConnectionPool;
import com.solvd.laba.domain.Department;
import com.solvd.laba.domain.LaboratoryAssistant;
import com.solvd.laba.domain.ResearchArea;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LaboratoryAssistantDAOImpl extends AbstractDAO<LaboratoryAssistant, Integer> implements LaboratoryAssistantDAO {

    public LaboratoryAssistantDAOImpl(ConnectionPool connectionPool) {
        super(connectionPool);
    }

    @Override
    protected LaboratoryAssistant createEntity(ResultSet resultSet) throws SQLException {
        LaboratoryAssistant assistant = new LaboratoryAssistant();
        assistant.setAssistantId(resultSet.getInt("assistant_id"));
        assistant.setName(resultSet.getString("name"));
        assistant.setEmail(resultSet.getString("email"));
        assistant.setDepartment(createDepartment(resultSet));
        assistant.setArea(createResearchArea(resultSet));
        return assistant;
    }

    private Department createDepartment(ResultSet resultSet) throws SQLException {
        Department department = new Department();
        department.setDepartmentId(resultSet.getInt("department_id"));
        department.setDepartmentName(resultSet.getString("department_name"));
        return department;
    }

    private ResearchArea createResearchArea(ResultSet resultSet) throws SQLException {
        ResearchArea area = new ResearchArea();
        area.setAreaId(resultSet.getInt("area_id"));
        area.setAreaName(resultSet.getString("area_name"));
        return area;
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO laboratory_assistants (name, email, department_id, area_id) VALUES (?, ?, ?, ?)";
    }

    @Override
    protected void setCreateStatement(PreparedStatement statement, LaboratoryAssistant assistant) throws SQLException {
        statement.setString(1, assistant.getName());
        statement.setString(2, assistant.getEmail());
        statement.setInt(3, assistant.getDepartment().getDepartmentId());
        statement.setInt(4, assistant.getArea().getAreaId());
    }

    @Override
    protected String getReadQuery() {
        return "SELECT la.*, d.department_name, ra.area_name " +
                "FROM laboratory_assistants la " +
                "JOIN departments d ON la.department_id = d.department_id " +
                "JOIN research_areas ra ON la.area_id = ra.area_id " +
                "WHERE assistant_id = ?";
    }

    @Override
    protected void setReadStatement(PreparedStatement statement, Integer id) throws SQLException {
        statement.setInt(1, id);
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE laboratory_assistants SET name = ?, email = ?, department_id = ?, area_id = ? WHERE assistant_id = ?";
    }

    @Override
    protected void setUpdateStatement(PreparedStatement statement, LaboratoryAssistant assistant) throws SQLException {
        statement.setString(1, assistant.getName());
        statement.setString(2, assistant.getEmail());
        statement.setInt(3, assistant.getDepartment().getDepartmentId());
        statement.setInt(4, assistant.getArea().getAreaId());
        statement.setInt(5, assistant.getAssistantId());
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM laboratory_assistants WHERE assistant_id = ?";
    }

    @Override
    protected void setDeleteStatement(PreparedStatement statement, Integer id) throws SQLException {
        statement.setInt(1, id);
    }

    @Override
    protected String getFindAllQuery() {
        return "SELECT la.*, d.department_name, ra.area_name " +
                "FROM laboratory_assistants la " +
                "JOIN departments d ON la.department_id = d.department_id " +
                "JOIN research_areas ra ON la.area_id = ra.area_id";
    }
}
