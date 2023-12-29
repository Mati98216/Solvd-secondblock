package com.solvd.laba.domain;

import com.solvd.laba.dao.interfaces.IdentifiableEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Analysis {
    private int analysisId;
    private String analysisName;
    private Scientist scientist;
    private LaboratoryAssistant assistant;

}
