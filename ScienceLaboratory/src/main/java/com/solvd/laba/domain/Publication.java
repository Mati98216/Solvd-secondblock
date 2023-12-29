package com.solvd.laba.domain;

import java.sql.Date;

import com.solvd.laba.dao.interfaces.IdentifiableEntity;
import lombok.*;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Publication  {
    private int publicationId;
    private String publicationTitle;
    private Date publicationDate;
    private Scientist scientist;

}

