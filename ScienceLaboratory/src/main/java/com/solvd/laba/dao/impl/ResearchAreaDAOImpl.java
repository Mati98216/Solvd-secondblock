package com.solvd.laba.dao.impl;

import com.solvd.laba.database.ConnectionPool;
import com.solvd.laba.dao.interfaces.ResearchAreaDAO;
import com.solvd.laba.database.DatabaseConnection;
import com.solvd.laba.domain.ResearchArea;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResearchAreaDAOImpl implements ResearchAreaDAO {
    private ConnectionPool connectionPool;

    public ResearchAreaDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public void addResearchArea(ResearchArea researchArea) throws SQLException {
        String sql = "INSERT INTO research_areas (area_id, area_name) VALUES (?, ?)";
        DatabaseConnection dbConnection = null;
        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();
            try (PreparedStatement statement = sqlConnection.prepareStatement(sql)) {
                statement.setInt(1, researchArea.getAreaId());
                statement.setString(2, researchArea.getAreaName());
                statement.executeUpdate();
            }
        } catch (InterruptedException e) {
            // Handle InterruptedException
        } finally {
            if (dbConnection != null) {
                connectionPool.releaseConnection(dbConnection);
            }
        }
    }

    @Override
    public ResearchArea getResearchAreaById(int id) throws SQLException {
        String sql = "SELECT * FROM research_areas WHERE area_id = ?";
        ResearchArea researchArea = null;
        DatabaseConnection dbConnection = null;

        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();

            try (PreparedStatement statement = sqlConnection.prepareStatement(sql)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        researchArea = new ResearchArea(
                                resultSet.getInt("area_id"),
                                resultSet.getString("area_name")
                        );
                    }
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (dbConnection != null) {
                connectionPool.releaseConnection(dbConnection);
            }
        }
        return researchArea;
    }


    @Override
    public List<ResearchArea> getAllResearchAreas() throws SQLException {
        List<ResearchArea> researchAreas = new ArrayList<>();
        String sql = "SELECT * FROM research_areas";
        DatabaseConnection dbConnection = null;

        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();

            try (PreparedStatement statement = sqlConnection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    researchAreas.add(new ResearchArea(
                            resultSet.getInt("area_id"),
                            resultSet.getString("area_name")
                    ));
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (dbConnection != null) {
                connectionPool.releaseConnection(dbConnection);
            }
        }
        return researchAreas;
    }


    @Override
    public void updateResearchArea(ResearchArea researchArea) throws SQLException {
        String sql = "UPDATE research_areas SET area_name = ? WHERE area_id = ?";
        DatabaseConnection dbConnection = null;

        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();

            try (PreparedStatement statement = sqlConnection.prepareStatement(sql)) {
                statement.setString(1, researchArea.getAreaName());
                statement.setInt(2, researchArea.getAreaId());
                statement.executeUpdate();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (dbConnection != null) {
                connectionPool.releaseConnection(dbConnection);
            }
        }
    }

    @Override
    public void deleteResearchArea(int id) throws SQLException {
        String sql = "DELETE FROM research_areas WHERE area_id = ?";
        DatabaseConnection dbConnection = null;

        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();

            try (PreparedStatement statement = sqlConnection.prepareStatement(sql)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (dbConnection != null) {
                connectionPool.releaseConnection(dbConnection);
            }
        }
    }

}
