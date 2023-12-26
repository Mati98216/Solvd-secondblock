package com.solvd.laba.dao.interfaces;

import com.solvd.laba.domain.ResearchArea;

import java.sql.SQLException;
import java.util.List;

public interface ResearchAreaDAO {
    void addResearchArea(ResearchArea researchArea) throws SQLException, InterruptedException;
    ResearchArea getResearchAreaById(int id) throws SQLException;
    List<ResearchArea> getAllResearchAreas() throws SQLException;
    void updateResearchArea(ResearchArea researchArea) throws SQLException;
    void deleteResearchArea(int id) throws SQLException;
}
