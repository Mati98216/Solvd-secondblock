package com.solvd.laba.dao.impl;

import com.solvd.laba.database.ConnectionPool;
import com.solvd.laba.dao.interfaces.ScientistDAO;
import com.solvd.laba.database.DatabaseConnection;
import com.solvd.laba.domain.Department;
import com.solvd.laba.domain.ResearchArea;
import com.solvd.laba.domain.Scientist;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScientistDAOImpl extends AbstractDAO<Scientist, Integer> implements ScientistDAO {

    public ScientistDAOImpl(ConnectionPool connectionPool) {
        super(connectionPool);
    }

    @Override
    protected Scientist createEntity(ResultSet resultSet) throws SQLException {
        Scientist scientist = new Scientist();
        scientist.setScientistId(resultSet.getInt("scientist_id"));
        scientist.setName(resultSet.getString("name"));
        scientist.setEmail(resultSet.getString("email"));

        Department department = new Department();
        department.setDepartmentId(resultSet.getInt("department_id"));
        department.setDepartmentName(resultSet.getString("department_name"));
        scientist.setDepartment(department);

        ResearchArea area = new ResearchArea();
        area.setAreaId(resultSet.getInt("area_id"));
        area.setAreaName(resultSet.getString("area_name"));
        scientist.setArea(area);

        return scientist;
    }

    @Override
    protected String getReadQuery() {
        return "SELECT s.*, d.department_name, a.area_name FROM scientists s " +
                "LEFT JOIN departments d ON s.department_id = d.department_id " +
                "LEFT JOIN research_areas a ON s.area_id = a.area_id " +
                "WHERE scientist_id = ?";
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO scientists (name, email, department_id, area_id) VALUES (?, ?, ?, ?)";
    }

    @Override
    protected void setCreateStatement(PreparedStatement statement, Scientist scientist) throws SQLException {
        statement.setString(1, scientist.getName());
        statement.setString(2, scientist.getEmail());
        statement.setInt(3, scientist.getDepartment().getDepartmentId());
        statement.setInt(4, scientist.getArea().getAreaId());
    }




    @Override
    protected void setReadStatement(PreparedStatement statement, Integer id) throws SQLException {
        statement.setInt(1, id);
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE scientists SET name = ?, email = ?, department_id = ?, area_id = ? WHERE scientist_id = ?";
    }

    @Override
    protected void setUpdateStatement(PreparedStatement statement, Scientist scientist) throws SQLException {
        statement.setString(1, scientist.getName());
        statement.setString(2, scientist.getEmail());
        statement.setInt(3, scientist.getDepartment().getDepartmentId());
        statement.setInt(4, scientist.getArea().getAreaId());
        statement.setInt(5, scientist.getScientistId());
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM scientists WHERE scientist_id = ?";
    }

    @Override
    protected void setDeleteStatement(PreparedStatement statement, Integer id) throws SQLException {
        statement.setInt(1, id);
    }
    @Override
    protected String getFindAllQuery() {
        return "SELECT s.*, d.department_name,a.area_name " +
                "FROM scientists s " +
                "LEFT JOIN departments d ON s.department_id = d.department_id "+
                "LEFT JOIN research_areas a ON s.area_id=a.area_id";
    }

}