package com.solvd.laba.dao.interfaces;

import com.solvd.laba.domain.LaboratoryAssistant;

import java.sql.SQLException;
import java.util.List;

public interface LaboratoryAssistantDAO {
    void addLaboratoryAssistant(LaboratoryAssistant laboratoryAssistant) throws SQLException;
    LaboratoryAssistant getLaboratoryAssistantById(int id) throws SQLException;
    List<LaboratoryAssistant> getAllLaboratoryAssistants() throws SQLException;
    void updateLaboratoryAssistant(LaboratoryAssistant laboratoryAssistant) throws SQLException;
    void deleteLaboratoryAssistant(int id) throws SQLException;
}
