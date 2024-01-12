package com.solvd.laba.domain;

import com.solvd.laba.dao.interfaces.IdentifiableEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Experiment  implements IdentifiableEntity<Integer>{
    private int experimentId;
    private String experimentName;
    private Scientist scientist;
    private List<Equipment> equipmentList;

    public Experiment(int experimentId, String experimentName, Scientist scientist) {
        this.experimentId = experimentId;
        this.experimentName = experimentName;
        this.scientist = scientist;
    }

    @Override
    public void setId(Number id) {
        this.experimentId = id.intValue();
    }
}

