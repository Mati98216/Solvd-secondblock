package com.solvd.laba.service.interfaces;

import com.google.protobuf.ServiceException;
import com.solvd.laba.domain.LaboratoryAssistant;

import java.util.List;

public interface LaboratoryAssistantService {
    void addLaboratoryAssistant(LaboratoryAssistant assistant) throws ServiceException;
    LaboratoryAssistant getLaboratoryAssistantById(int id) throws ServiceException;
    List<LaboratoryAssistant> getAllLaboratoryAssistants() throws ServiceException;
    void updateLaboratoryAssistant(LaboratoryAssistant assistant) throws ServiceException;
    void deleteLaboratoryAssistant(int id) throws ServiceException;
}
