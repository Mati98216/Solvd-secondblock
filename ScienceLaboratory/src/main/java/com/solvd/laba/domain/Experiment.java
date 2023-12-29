package com.solvd.laba.domain;

import com.solvd.laba.dao.interfaces.IdentifiableEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Experiment  {
    private int experimentId;
    private String experimentName;
    private Scientist scientist;

}

