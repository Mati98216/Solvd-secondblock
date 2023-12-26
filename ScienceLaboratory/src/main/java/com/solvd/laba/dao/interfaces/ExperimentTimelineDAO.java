package com.solvd.laba.dao.interfaces;

import com.solvd.laba.domain.ExperimentTimeline;

import java.sql.SQLException;
import java.util.List;

public interface ExperimentTimelineDAO {
    void addExperimentTimeline(ExperimentTimeline experimentTimeline) throws SQLException, InterruptedException;
    ExperimentTimeline getExperimentTimelineById(int id) throws SQLException;
    List<ExperimentTimeline> getAllExperimentTimelines() throws SQLException;
    void updateExperimentTimeline(ExperimentTimeline experimentTimeline) throws SQLException;
    void deleteExperimentTimeline(int id) throws SQLException;
}
