package com.solvd.laba.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class Scientist implements IdentifiableEntity<Integer> {
    @XmlElement(name = "id")
    @JsonProperty("id")
    private int scientistId;

    @XmlElement(name = "name")
    @JsonProperty("name")
    private String name;

    @XmlElement(name = "email")
    @JsonProperty("email")
    private String email;

    @XmlElement(name = "department")
    @JsonProperty("department")
    private Department department;

    @XmlElement(name = "researchArea")
    @JsonProperty("researchArea")
    private ResearchArea area;
    @Override
    public void setId(Number id) {
        this.scientistId = id.intValue();
    }
    @Override
    public String toString() {
        return "Scientist{" +
                "scientistId=" + scientistId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", department=" + (department != null ? department.getDepartmentName() : "null") +
                ", area=" + (area != null ? area.getAreaName() : "null") +
                '}';
    }
}
