package com.solvd.laba.domain;

import com.solvd.laba.dao.interfaces.IdentifiableEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResearchArea implements IdentifiableEntity<Integer> {
    private int areaId;
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
