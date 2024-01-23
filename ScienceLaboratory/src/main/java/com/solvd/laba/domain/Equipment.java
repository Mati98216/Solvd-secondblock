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
public class Equipment implements IdentifiableEntity<Integer> {
    @XmlElement(name = "equipmentId")
    @JsonProperty("equipmentId")
    private int equipmentId;

    @XmlElement(name = "equipmentName")
    @JsonProperty("equipmentName")
    private String equipmentName;

    @XmlElement(name = "department")
    @JsonProperty("department")
    private Department department;

    public Equipment(int equipmentId, String equipmentName) {
    }

    @Override
    public void setId(Number id) {
        this.equipmentId = id.intValue();
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "equipmentId=" + equipmentId +
                ", equipmentName='" + equipmentName + '\'' +
                ", department=" + department +
                '}';
    }
}
