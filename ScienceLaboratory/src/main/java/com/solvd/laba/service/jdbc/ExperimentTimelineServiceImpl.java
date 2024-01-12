package com.solvd.laba.service.jdbc;

import com.google.protobuf.ServiceException;
import com.solvd.laba.dao.impl.ExperimentTimelineDAOImpl;
import com.solvd.laba.dao.interfaces.ExperimentTimelineDAO;
import com.solvd.laba.database.DatabaseConfig;
import com.solvd.laba.domain.ExperimentTimeline;
import com.solvd.laba.service.interfaces.ExperimentTimelineService;

import java.util.List;

public class ExperimentTimelineServiceImpl implements ExperimentTimelineService {

    private final ExperimentTimelineDAO experimentTimelineDAO;

    public ExperimentTimelineServiceImpl() {
        this.experimentTimelineDAO = new ExperimentTimelineDAOImpl(DatabaseConfig.getConnectionPool());
    }

    @Override
    public void addExperimentTimeline(ExperimentTimeline experimentTimeline) throws ServiceException {
        try {
            experimentTimelineDAO.create(experimentTimeline);
        } catch (Exception e) {
            throw new ServiceException("Error adding experiment timeline", e);
        }
    }

    @Override
    public ExperimentTimeline getExperimentTimelineById(int id) throws ServiceException {
        try {
            return experimentTimelineDAO.read(id);
        } catch (Exception e) {
            throw new ServiceException("Error retrieving experiment timeline", e);
        }
    }

    @Override
    public List<ExperimentTimeline> getAllExperimentTimelines() throws ServiceException {
        try {
            return experimentTimelineDAO.findAll();
        } catch (Exception e) {
            throw new ServiceException("Error retrieving all experiment timelines", e);
        }
    }

    @Override
    public void updateExperimentTimeline(ExperimentTimeline experimentTimeline) throws ServiceException {
        try {
            experimentTimelineDAO.update(experimentTimeline);
        } catch (Exception e) {
            throw new ServiceException("Error updating experiment timeline", e);
        }
    }

    @Override
    public void deleteExperimentTimeline(int id) throws ServiceException {
        try {
            experimentTimelineDAO.delete(id);
        } catch (Exception e) {
            throw new ServiceException("Error deleting experiment timeline", e);
        }
    }
}

