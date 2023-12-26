package com.solvd.laba.service.interfaces;

import com.google.protobuf.ServiceException;
import com.solvd.laba.domain.ExperimentEquipment;

import java.util.List;

public interface ExperimentEquipmentService {
    void addExperimentEquipment(ExperimentEquipment equipment) throws ServiceException;
    ExperimentEquipment getExperimentEquipmentById(int id) throws ServiceException;
    List<ExperimentEquipment> getAllExperimentEquipments() throws ServiceException;
    void updateExperimentEquipment(ExperimentEquipment equipment) throws ServiceException;
    void deleteExperimentEquipment(int id) throws ServiceException;
}
