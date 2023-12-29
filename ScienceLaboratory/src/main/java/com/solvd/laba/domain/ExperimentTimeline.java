package com.solvd.laba.domain;

import java.sql.Date;

import com.solvd.laba.dao.interfaces.IdentifiableEntity;
import lombok.*;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExperimentTimeline  {
    private int timelineId;
    private Experiment experiment;
    private Date startDate;
    private Date endDate;

}
