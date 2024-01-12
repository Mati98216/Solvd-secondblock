package com.solvd.laba.service.interfaces;

import com.google.protobuf.ServiceException;
import com.solvd.laba.domain.ExperimentTimeline;

import java.util.List;

public interface ExperimentTimelineService {
    void addExperimentTimeline(ExperimentTimeline experimentTimeline) throws ServiceException;
    ExperimentTimeline getExperimentTimelineById(int id) throws ServiceException;
    List<ExperimentTimeline> getAllExperimentTimelines() throws ServiceException;
    void updateExperimentTimeline(ExperimentTimeline experimentTimeline) throws ServiceException;
    void deleteExperimentTimeline(int id) throws ServiceException;
}
