package com.solvd.laba.domain;

public class Experiment {
    private int experimentId;
    private String experimentName;
    private int scientistId;


    public Experiment() {
    }

    public Experiment(int experimentId, String experimentName, int scientistId) {
        this.experimentId = experimentId;
        this.experimentName = experimentName;
        this.scientistId = scientistId;
    }

    public int getExperimentId() {
        return experimentId;
    }

    public void setExperimentId(int experimentId) {
        this.experimentId = experimentId;
    }

    public String getExperimentName() {
        return experimentName;
    }

    public void setExperimentName(String experimentName) {
        this.experimentName = experimentName;
    }

    public int getScientistId() {
        return scientistId;
    }

    public void setScientistId(int scientistId) {
        this.scientistId = scientistId;
    }
}
