package com.solvd.laba.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.solvd.laba.dao.interfaces.IdentifiableEntity;
import lombok.*;

import javax.xml.bind.annotation.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "experiment")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Experiment  implements IdentifiableEntity<Integer>{
    @XmlElement(name = "experimentId")
    @JsonProperty("experimentId")
    private int experimentId;

    @XmlElement(name = "experimentName")
    @JsonProperty("experimentName")
    private String experimentName;

    @XmlElement(name = "scientist")
    @JsonProperty("scientist")
    private Scientist scientist;

    @XmlElementWrapper(name = "equipmentList")
    @XmlElement(name = "equipment")
    @JsonProperty("equipmentList")
    private List<Equipment> equipmentList;

    public Experiment(int experimentId, String experimentName, Scientist scientist) {
        this.experimentId = experimentId;
        this.experimentName = experimentName;
        this.scientist = scientist;
    }

    @Override
    public void setId(Number id) {
        this.experimentId = id.intValue();
    }
    @Override
    public String toString() {
        return "Experiment{" +
                "experimentId=" + experimentId +
                ", experimentName='" + experimentName + '\'' +
                ", scientist=" + (scientist != null ? scientist.toString() : "null") +
                ", equipmentList=" + (equipmentList != null ? equipmentList.toString() : "null") +
                '}';
    }
}

