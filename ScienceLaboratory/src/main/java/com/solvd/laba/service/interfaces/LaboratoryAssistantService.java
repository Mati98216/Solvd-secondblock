package com.solvd.laba.service.interfaces;

import com.google.protobuf.ServiceException;
import com.solvd.laba.domain.LaboratoryAssistant;

import java.util.List;

public interface LaboratoryAssistantService {
    void addLaboratoryAssistant(LaboratoryAssistant laboratoryAssistant) throws ServiceException;
    LaboratoryAssistant getLaboratoryAssistantById(int id) throws ServiceException;
    List<LaboratoryAssistant> getAllLaboratoryAssistants() throws ServiceException;
    void updateLaboratoryAssistant(LaboratoryAssistant laboratoryAssistant) throws ServiceException;
    void deleteLaboratoryAssistant(int id) throws ServiceException;
}
