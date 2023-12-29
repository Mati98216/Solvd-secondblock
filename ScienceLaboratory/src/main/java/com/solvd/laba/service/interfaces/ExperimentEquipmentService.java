package com.solvd.laba.service.interfaces;

import com.solvd.laba.domain.ExperimentEquipment;

import java.util.List;

public interface ExperimentEquipmentService {
    void addExperimentEquipment(ExperimentEquipment experimentEquipment);
    ExperimentEquipment getExperimentEquipmentById(int id);
    List<ExperimentEquipment> getAllExperimentEquipment();
    void updateExperimentEquipment(ExperimentEquipment experimentEquipment);
    void deleteExperimentEquipment(int id);
}
