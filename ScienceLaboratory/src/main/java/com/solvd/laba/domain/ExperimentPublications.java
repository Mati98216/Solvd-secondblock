package com.solvd.laba.domain;

public class ExperimentPublications {
    private int experimentPublicationsId;
    private int experimentId;
    private int publicationId;

    public ExperimentPublications() {
    }

    public ExperimentPublications(int experimentPublicationsId, int experimentId, int publicationId) {
        this.experimentPublicationsId = experimentPublicationsId;
        this.experimentId = experimentId;
        this.publicationId = publicationId;
    }

    public int getExperimentPublicationsId() {
        return experimentPublicationsId;
    }

    public void setExperimentPublicationsId(int experimentPublicationsId) {
        this.experimentPublicationsId = experimentPublicationsId;
    }

    public int getExperimentId() {
        return experimentId;
    }

    public void setExperimentId(int experimentId) {
        this.experimentId = experimentId;
    }

    public int getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(int publicationId) {
        this.publicationId = publicationId;
    }
}