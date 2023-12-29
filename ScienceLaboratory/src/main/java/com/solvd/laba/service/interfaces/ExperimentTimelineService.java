package com.solvd.laba.service.interfaces;

import com.solvd.laba.domain.ExperimentTimeline;

import java.util.List;

public interface ExperimentTimelineService {
    void addExperimentTimeline(ExperimentTimeline experimentTimeline);
    ExperimentTimeline getExperimentTimelineById(int id);
    List<ExperimentTimeline> getAllExperimentTimelines();
    void updateExperimentTimeline(ExperimentTimeline experimentTimeline);
    void deleteExperimentTimeline(int id);
}
