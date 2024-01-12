package com.solvd.laba.domain;

import com.solvd.laba.dao.interfaces.IdentifiableEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Equipment implements IdentifiableEntity<Integer> {
    private int equipmentId;
    private String equipmentName;
    private Department department;

    public Equipment(int equipmentId, String equipmentName) {
    }

    @Override
    public void setId(Number id) {
        this.equipmentId = id.intValue();
    }
}
