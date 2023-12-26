package com.solvd.laba.dao.interfaces;

import com.solvd.laba.domain.ExperimentEquipment;

import java.sql.SQLException;
import java.util.List;

public interface ExperimentEquipmentDAO {
    void addExperimentEquipment(ExperimentEquipment experimentEquipment) throws SQLException, InterruptedException;
    ExperimentEquipment getExperimentEquipmentById(int id) throws SQLException;
    List<ExperimentEquipment> getAllExperimentEquipments() throws SQLException;
    void updateExperimentEquipment(ExperimentEquipment experimentEquipment) throws SQLException;
    void deleteExperimentEquipment(int id) throws SQLException;
}
