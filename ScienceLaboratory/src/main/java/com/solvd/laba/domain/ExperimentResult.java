package com.solvd.laba.domain;

public class ExperimentResult {
    private int resultId;
    private int experimentId;
    private int analysisId;
    private String resultDetails;


    public ExperimentResult() {
    }

    public ExperimentResult(int resultId, int experimentId, int analysisId, String resultDetails) {
        this.resultId = resultId;
        this.experimentId = experimentId;
        this.analysisId = analysisId;
        this.resultDetails = resultDetails;
    }

    public int getResultId() {
        return resultId;
    }

    public void setResultId(int resultId) {
        this.resultId = resultId;
    }

    public int getExperimentId() {
        return experimentId;
    }

    public void setExperimentId(int experimentId) {
        this.experimentId = experimentId;
    }

    public int getAnalysisId() {
        return analysisId;
    }

    public void setAnalysisId(int analysisId) {
        this.analysisId = analysisId;
    }

    public String getResultDetails() {
        return resultDetails;
    }

    public void setResultDetails(String resultDetails) {
        this.resultDetails = resultDetails;
    }
}
