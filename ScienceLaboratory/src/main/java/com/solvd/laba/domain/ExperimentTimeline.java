package com.solvd.laba.domain;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.solvd.laba.dao.interfaces.IdentifiableEntity;
import com.solvd.laba.jaxb.SqlDateAdapter;
import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class ExperimentTimeline implements IdentifiableEntity<Integer> {
    @XmlElement(name = "timelineId")
    @JsonProperty("timelineId")
    private int timelineId;

    @Override
    public String toString() {
        return "ExperimentTimeline{" +
                "timelineId=" + timelineId +
                ", experiment=" + experiment +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

    @XmlElement(name = "experiment")
    @JsonProperty("experiment")
    private Experiment experiment;

    @XmlElement(name = "startDate")
    @XmlSchemaType(name = "date")
    @JsonProperty("startDate")
    @XmlJavaTypeAdapter(SqlDateAdapter.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date startDate;

    @XmlElement(name = "endDate")
    @XmlSchemaType(name = "date")
    @JsonProperty("endDate")
    @XmlJavaTypeAdapter(SqlDateAdapter.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date endDate;
    @Override
    public void setId(Number id) {
        this.timelineId = id.intValue();
    }
}
