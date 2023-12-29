package com.solvd.laba.service.interfaces;

import com.solvd.laba.domain.LaboratoryAssistant;

import java.util.List;

public interface LaboratoryAssistantService {
    void addLaboratoryAssistant(LaboratoryAssistant laboratoryAssistant);
    LaboratoryAssistant getLaboratoryAssistantById(int id);
    List<LaboratoryAssistant> getAllLaboratoryAssistants();
    void updateLaboratoryAssistant(LaboratoryAssistant laboratoryAssistant);
    void deleteLaboratoryAssistant(int id);
}
