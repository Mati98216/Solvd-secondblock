package com.solvd.laba.dao.impl;

import com.solvd.laba.dao.interfaces.ExperimentTimelineDAO;
import com.solvd.laba.database.ConnectionPool;
import com.solvd.laba.domain.Experiment;
import com.solvd.laba.domain.ExperimentTimeline;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExperimentTimelineDAOImpl extends AbstractDAO<ExperimentTimeline, Integer> implements ExperimentTimelineDAO {

    public ExperimentTimelineDAOImpl(ConnectionPool connectionPool) {
        super(connectionPool);
    }

    @Override
    protected ExperimentTimeline createEntity(ResultSet resultSet) throws SQLException {
        ExperimentTimeline timeline = new ExperimentTimeline();
        timeline.setTimelineId(resultSet.getInt("timeline_id"));

        Experiment experiment = new Experiment(); // Assuming you have a method to create an Experiment object
        experiment.setExperimentId(resultSet.getInt("experiment_id"));

        timeline.setExperiment(experiment);

        timeline.setStartDate(resultSet.getDate("start_date"));
        timeline.setEndDate(resultSet.getDate("end_date"));

        return timeline;
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO experiment_timelines (experiment_id, start_date, end_date) VALUES (?, ?, ?)";
    }

    @Override
    protected void setCreateStatement(PreparedStatement statement, ExperimentTimeline timeline) throws SQLException {
        statement.setInt(1, timeline.getExperiment().getExperimentId());
        statement.setDate(2, new java.sql.Date(timeline.getStartDate().getTime()));
        statement.setDate(3, new java.sql.Date(timeline.getEndDate().getTime()));
    }

    @Override
    protected String getReadQuery() {
        return "SELECT et.*, e.experiment_name " +
                "FROM experiment_timelines et " +
                "LEFT JOIN experiments e ON et.experiment_id = e.experiment_id " +
                "WHERE et.timeline_id = ?";
    }

    @Override
    protected void setReadStatement(PreparedStatement statement, Integer id) throws SQLException {
        statement.setInt(1, id);
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE experiment_timelines SET experiment_id = ?, start_date = ?, end_date = ? WHERE timeline_id = ?";
    }

    @Override
    protected void setUpdateStatement(PreparedStatement statement, ExperimentTimeline timeline) throws SQLException {
        statement.setInt(1, timeline.getExperiment().getExperimentId());
        statement.setDate(2, new java.sql.Date(timeline.getStartDate().getTime()));
        statement.setDate(3, new java.sql.Date(timeline.getEndDate().getTime()));
        statement.setInt(4, timeline.getTimelineId());
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM experiment_timelines WHERE timeline_id = ?";
    }

    @Override
    protected void setDeleteStatement(PreparedStatement statement, Integer id) throws SQLException {
        statement.setInt(1, id);
    }

    @Override
    public List<ExperimentTimeline> findAll() throws SQLException {
        List<ExperimentTimeline> timelines = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection().getSqlConnection();
             PreparedStatement statement = connection.prepareStatement(getFindAllQuery())) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                timelines.add(createEntity(resultSet));
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("Operation interrupted", e);
        }
        return timelines;
    }

    protected String getFindAllQuery() {
        return "SELECT et.*, e.experiment_name " +
                "FROM experiment_timelines et " +
                "LEFT JOIN experiments e ON et.experiment_id = e.experiment_id";
    }
}

