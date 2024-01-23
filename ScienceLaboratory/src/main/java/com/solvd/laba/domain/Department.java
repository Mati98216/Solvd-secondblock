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
public class Department implements IdentifiableEntity<Integer>{
    @XmlElement(name = "departmentId")
    @JsonProperty("departmentId")
    private int departmentId;

    @XmlElement(name = "departmentName")
    @JsonProperty("departmentName")
    private String departmentName;

    @Override
    public String toString() {
        return "Department{" +
                "departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
    @Override
    public void setId(Number id) {
        this.departmentId = id.intValue();
    }
}
