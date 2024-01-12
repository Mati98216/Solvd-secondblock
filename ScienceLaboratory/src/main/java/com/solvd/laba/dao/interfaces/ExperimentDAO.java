package com.solvd.laba.dao.interfaces;

import com.solvd.laba.domain.Equipment;
import com.solvd.laba.domain.Experiment;

import java.sql.SQLException;
import java.util.List;

public interface ExperimentDAO extends GenericDAO<Experiment, Integer> {


    void addEquipmentToExperiment(int experimentId, int equipmentId) throws SQLException;


    void updateEquipmentForExperiment(int experimentId, int oldEquipmentId, int newEquipmentId) throws SQLException;


    void removeEquipmentFromExperiment(int experimentId, int equipmentId) throws SQLException, InterruptedException;
    List<Equipment> getEquipmentForExperiment(int experimentId) throws SQLException, InterruptedException;

}
