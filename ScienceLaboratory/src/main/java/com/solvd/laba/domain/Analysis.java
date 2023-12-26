package com.solvd.laba.domain;

public class Analysis {
    private int analysisId;
    private String analysisName;
    private int scientistId;
    private int assistantId;


    public Analysis() {
    }

    public Analysis(int analysisId, String analysisName, int scientistId, int assistantId) {
        this.analysisId = analysisId;
        this.analysisName = analysisName;
        this.scientistId = scientistId;
        this.assistantId = assistantId;
    }

    public int getAnalysisId() {
        return analysisId;
    }

    public void setAnalysisId(int analysisId) {
        this.analysisId = analysisId;
    }

    public String getAnalysisName() {
        return analysisName;
    }

    public void setAnalysisName(String analysisName) {
        this.analysisName = analysisName;
    }

    public int getScientistId() {
        return scientistId;
    }

    public void setScientistId(int scientistId) {
        this.scientistId = scientistId;
    }

    public int getAssistantId() {
        return assistantId;
    }

    public void setAssistantId(int assistantId) {
        this.assistantId = assistantId;
    }
}