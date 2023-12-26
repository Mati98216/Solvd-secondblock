package com.solvd.laba.domain;

import java.sql.Date;

public class ExperimentTimeline {
    private int timelineId;
    private int experimentId;
    private Date startDate;
    private Date endDate;

    // Constructors
    public ExperimentTimeline() {
    }

    public ExperimentTimeline(int timelineId, int experimentId, Date startDate, Date endDate) {
        this.timelineId = timelineId;
        this.experimentId = experimentId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getTimelineId() {
        return timelineId;
    }

    public void setTimelineId(int timelineId) {
        this.timelineId = timelineId;
    }

    public int getExperimentId() {
        return experimentId;
    }

    public void setExperimentId(int experimentId) {
        this.experimentId = experimentId;
    }

    public java.sql.Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public java.sql.Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
