package com.solvd.laba.domain;

import com.solvd.laba.dao.interfaces.IdentifiableEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExperimentResult {
    private int resultId;
    private Experiment experiment;
    private Analysis analysis;
    private String resultDetails;

}
