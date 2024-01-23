package com.solvd.laba.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solvd.laba.dao.interfaces.IdentifiableEntity;
import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class ResearchArea implements IdentifiableEntity<Integer> {
    @XmlElement(name = "areaId")
    @JsonProperty("areaId")
    private int areaId;

    @XmlElement(name = "areaName")
    @JsonProperty("areaName")
    private String areaName;

    @Override
    public String toString() {
        return "ResearchArea{" +
                "areaId=" + areaId +
                ", areaName='" + areaName + '\'' +
                '}';
    }
    @Override
    public void setId(Number id) {
        this.areaId = id.intValue();
    }
}
