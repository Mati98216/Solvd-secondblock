package com.solvd.laba.service.interfaces;

import com.google.protobuf.ServiceException;
import com.solvd.laba.domain.Equipment;
import com.solvd.laba.domain.Experiment;

import java.util.List;

public interface ExperimentService {
    void addExperiment(Experiment experiment) throws ServiceException;
    Experiment getExperimentById(int id) throws ServiceException;
    List<Experiment> getAllExperiments() throws ServiceException;
    void updateExperiment(Experiment experiment) throws ServiceException;
    void deleteExperiment(int id) throws ServiceException;
    void addEquipmentToExperiment(int experimentId, int equipmentId) throws ServiceException;
    List<Equipment> getEquipmentForExperiment(int experimentId) throws ServiceException;
    void updateEquipmentForExperiment(int experimentId, int oldEquipmentId, int newEquipmentId) throws ServiceException;
    void removeEquipmentFromExperiment(int experimentId, int equipmentId) throws ServiceException;

}
