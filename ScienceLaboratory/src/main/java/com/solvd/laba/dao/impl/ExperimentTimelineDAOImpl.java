package com.solvd.laba.dao.impl;
import com.solvd.laba.database.ConnectionPool;
import com.solvd.laba.dao.interfaces.ExperimentTimelineDAO;
import com.solvd.laba.database.DatabaseConnection;
import com.solvd.laba.domain.ExperimentTimeline;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ExperimentTimelineDAOImpl implements ExperimentTimelineDAO {
    private ConnectionPool connectionPool;

    public ExperimentTimelineDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public void addExperimentTimeline(ExperimentTimeline experimentTimeline) throws SQLException {
        String sql = "INSERT INTO experiment_timeline (experiment_id, start_date, end_date) VALUES (?, ?, ?)";
        DatabaseConnection dbConnection = null;
        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();

            try (PreparedStatement statement = sqlConnection.prepareStatement(sql)) {
                statement.setInt(1, experimentTimeline.getExperimentId());
                statement.setDate(2, experimentTimeline.getStartDate());
                statement.setDate(3, experimentTimeline.getEndDate());
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
    public ExperimentTimeline getExperimentTimelineById(int id) throws SQLException {
        String sql = "SELECT * FROM experiment_timeline WHERE timeline_id = ?";
        ExperimentTimeline experimentTimeline = null;
        DatabaseConnection dbConnection = null;

        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();

            try (PreparedStatement statement = sqlConnection.prepareStatement(sql)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        experimentTimeline = new ExperimentTimeline(
                                resultSet.getInt("timeline_id"),
                                resultSet.getInt("experiment_id"),
                                resultSet.getDate("start_date"),
                                resultSet.getDate("end_date")
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
        return experimentTimeline;
    }

    @Override
    public List<ExperimentTimeline> getAllExperimentTimelines() throws SQLException {
        List<ExperimentTimeline> experimentTimelines = new ArrayList<>();
        String sql = "SELECT * FROM experiment_timeline";
        DatabaseConnection dbConnection = null;

        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();

            try (PreparedStatement statement = sqlConnection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    experimentTimelines.add(new ExperimentTimeline(
                            resultSet.getInt("timeline_id"),
                            resultSet.getInt("experiment_id"),
                            resultSet.getDate("start_date"),
                            resultSet.getDate("end_date")
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
        return experimentTimelines;
    }

    @Override
    public void updateExperimentTimeline(ExperimentTimeline experimentTimeline) throws SQLException {
        String sql = "UPDATE experiment_timeline SET experiment_id = ?, start_date = ?, end_date = ? WHERE timeline_id = ?";
        DatabaseConnection dbConnection = null;

        try {
            dbConnection = connectionPool.getConnection();
            Connection sqlConnection = dbConnection.getSqlConnection();

            try (PreparedStatement statement = sqlConnection.prepareStatement(sql)) {
                statement.setInt(1, experimentTimeline.getExperimentId());
                statement.setDate(2, experimentTimeline.getStartDate());
                statement.setDate(3, experimentTimeline.getEndDate());
                statement.setInt(4, experimentTimeline.getTimelineId());
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
    public void deleteExperimentTimeline(int id) throws SQLException {
        String sql = "DELETE FROM experiment_timeline WHERE timeline_id = ?";
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
