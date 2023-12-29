package com.solvd.laba.domain;

import com.solvd.laba.dao.interfaces.IdentifiableEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LaboratoryAssistant {
    private int assistantId;
    private String name;
    private String email;
    private Department department;
    private ResearchArea area;

}
