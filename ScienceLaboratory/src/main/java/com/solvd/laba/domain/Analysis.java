package com.solvd.laba.domain;

import com.solvd.laba.dao.interfaces.IdentifiableEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Analysis implements IdentifiableEntity<Integer>{
    private int analysisId;
    private String analysisName;
    private Scientist scientist;
    private LaboratoryAssistant assistant;
    @Override
    public void setId(Number id) {
        this.analysisId = id.intValue();
    }
}
