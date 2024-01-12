package com.solvd.laba.domain;

import com.solvd.laba.dao.interfaces.IdentifiableEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LaboratoryAssistant implements IdentifiableEntity<Integer>{
    private int assistantId;
    private String name;
    private String email;
    private Department department;
    private ResearchArea area;
    @Override
    public void setId(Number id) {
        this.assistantId = id.intValue();
    }
}
