package com.solvd.laba.domain;

import com.solvd.laba.dao.interfaces.IdentifiableEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExperimentPublications {
    private int experimentPublicationsId;
    private Experiment experiment;
    private Publication publication;

}
