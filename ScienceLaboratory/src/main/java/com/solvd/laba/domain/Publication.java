package com.solvd.laba.domain;

import java.sql.Date;

public class Publication {
    private int publicationId;
    private String publicationTitle;
    private Date publicationDate;
    private int scientistId;


    public Publication() {
    }

    public Publication(int publicationId, String publicationTitle, Date publicationDate, int scientistId) {
        this.publicationId = publicationId;
        this.publicationTitle = publicationTitle;
        this.publicationDate = publicationDate;
        this.scientistId = scientistId;
    }

    public int getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(int publicationId) {
        this.publicationId = publicationId;
    }

    public String getPublicationTitle() {
        return publicationTitle;
    }

    public void setPublicationTitle(String publicationTitle) {
        this.publicationTitle = publicationTitle;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public int getScientistId() {
        return scientistId;
    }

    public void setScientistId(int scientistId) {
        this.scientistId = scientistId;
    }
}
