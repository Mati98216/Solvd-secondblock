package com.solvd.laba.domain;

import com.solvd.laba.dao.interfaces.IdentifiableEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExperimentEquipment {
    private int experimentEquipmentId;
    private Experiment experiment;
    private Equipment equipment;

}

