package com.solvd.laba.domain;

import com.solvd.laba.dao.interfaces.IdentifiableEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Scientist implements IdentifiableEntity<Integer> {
    private int scientistId;
    private String name;
    private String email;
    private Department department;
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
