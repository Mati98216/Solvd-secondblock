package com.solvd.laba.domain;

import java.sql.Date;

import com.solvd.laba.dao.interfaces.IdentifiableEntity;
import lombok.*;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Publication implements IdentifiableEntity<Integer> {
    private int publicationId;
    private String publicationTitle;
    private Date publicationDate;
    private Scientist scientist;
    private List<Experiment> experimentList;

    public Publication(int publicationId, String publicationTitle, Date publicationDate, Scientist scientist) {
        this.publicationId = publicationId;
        this.publicationTitle = publicationTitle;
        this.publicationDate = publicationDate;
        this.scientist = scientist;
    }

    @Override
    public void setId(Number id) {
        this.publicationId = id.intValue();
    }

    @Override
    public String toString() {
        return "Publication{" +
                "publicationId=" + publicationId +
                ", publicationTitle='" + publicationTitle + '\'' +
                ", publicationDate=" + publicationDate +
                ", scientist=" + scientist +
                ", experimentList=" + experimentList +
                '}';
    }
}

