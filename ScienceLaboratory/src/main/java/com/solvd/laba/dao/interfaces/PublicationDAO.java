package com.solvd.laba.dao.interfaces;

import com.solvd.laba.domain.Experiment;
import com.solvd.laba.domain.Publication;

import java.sql.SQLException;
import java.util.List;

public interface PublicationDAO extends GenericDAO<Publication, Integer> {


    void addExperimentToPublication(int publicationId, int experimentId) throws SQLException, InterruptedException;


    List<Experiment> getExperimentsForPublication(int publicationId) throws SQLException;


    void updateExperimentForPublication(int publicationId, int oldExperimentId, int newExperimentId) throws SQLException;


    void removeExperimentFromPublication(int publicationId, int experimentId) throws SQLException, InterruptedException;


}

