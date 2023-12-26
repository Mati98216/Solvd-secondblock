package com.solvd.laba.service.impl;

import com.google.protobuf.ServiceException;
import com.solvd.laba.dao.interfaces.ExperimentTimelineDAO;
import com.solvd.laba.domain.ExperimentTimeline;
import com.solvd.laba.service.interfaces.ExperimentTimelineService;

import java.sql.SQLException;
import java.util.List;

public class ExperimentTimelineServiceImpl implements ExperimentTimelineService {
    private ExperimentTimelineDAO timelineDAO;

    public ExperimentTimelineServiceImpl(ExperimentTimelineDAO timelineDAO) {
        this.timelineDAO = timelineDAO;
    }

    @Override
    public void addExperimentTimeline(ExperimentTimeline timeline) throws ServiceException {
        try {
            timelineDAO.addExperimentTimeline(timeline);
        } catch (SQLException | InterruptedException e) {
            throw new ServiceException("Error adding experiment timeline", e);
        }
    }

    @Override
    public ExperimentTimeline getExperimentTimelineById(int id) throws ServiceException {
        try {
            return timelineDAO.getExperimentTimelineById(id);
        } catch (SQLException e) {
            throw new ServiceException("Error retrieving experiment timeline by ID", e);
        }
    }

    @Override
    public List<ExperimentTimeline> getAllExperimentTimelines() throws ServiceException {
        try {
            return timelineDAO.getAllExperimentTimelines();
        } catch (SQLException e) {
            throw new ServiceException("Error retrieving all experiment timelines", e);
        }
    }

    @Override
    public void updateExperimentTimeline(ExperimentTimeline timeline) throws ServiceException {
        try {
            timelineDAO.updateExperimentTimeline(timeline);
        } catch (SQLException e) {
            throw new ServiceException("Error updating experiment timeline", e);
        }
    }

    @Override
    public void deleteExperimentTimeline(int id) throws ServiceException {
        try {
            timelineDAO.deleteExperimentTimeline(id);
        } catch (SQLException e) {
            throw new ServiceException("Error deleting experiment timeline", e);
        }
    }

}
