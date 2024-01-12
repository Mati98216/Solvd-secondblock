package com.solvd.laba.dao.impl;

import com.solvd.laba.dao.interfaces.ResearchAreaDAO;
import com.solvd.laba.database.ConnectionPool;
import com.solvd.laba.domain.ResearchArea;

import java.sql.*;

public class ResearchAreaDAOImpl extends AbstractDAO<ResearchArea, Integer> implements ResearchAreaDAO {

    public ResearchAreaDAOImpl(ConnectionPool connectionPool) {
        super(connectionPool);
    }

    @Override
    protected ResearchArea createEntity(ResultSet resultSet) throws SQLException {
        ResearchArea researchArea = new ResearchArea();
        researchArea.setAreaId(resultSet.getInt("area_id"));
        researchArea.setAreaName(resultSet.getString("area_name"));
        return researchArea;
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO research_areas (area_name) VALUES (?)";
    }

    @Override
    protected void setCreateStatement(PreparedStatement statement, ResearchArea researchArea) throws SQLException {
        statement.setString(1, researchArea.getAreaName());
    }

    @Override
    protected String getReadQuery() {
        return "SELECT * FROM research_areas WHERE area_id = ?";
    }

    @Override
    protected void setReadStatement(PreparedStatement statement, Integer id) throws SQLException {
        statement.setInt(1, id);
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE research_areas SET area_name = ? WHERE area_id = ?";
    }

    @Override
    protected void setUpdateStatement(PreparedStatement statement, ResearchArea researchArea) throws SQLException {
        statement.setString(1, researchArea.getAreaName());
        statement.setInt(2, researchArea.getAreaId());
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM research_areas WHERE area_id = ?";
    }

    @Override
    protected void setDeleteStatement(PreparedStatement statement, Integer id) throws SQLException {
        statement.setInt(1, id);
    }

    @Override
    protected String getFindAllQuery() {
        return "SELECT * FROM research_areas";
    }
}


