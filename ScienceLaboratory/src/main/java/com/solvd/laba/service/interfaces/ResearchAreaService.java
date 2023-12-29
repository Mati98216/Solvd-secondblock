package com.solvd.laba.service.interfaces;

import com.solvd.laba.domain.ResearchArea;
import java.util.List;

public interface ResearchAreaService {
    void addResearchArea(ResearchArea researchArea);
    ResearchArea getResearchAreaById(int id);
    List<ResearchArea> getAllResearchAreas();
    void updateResearchArea(ResearchArea researchArea);
    void deleteResearchArea(int id);
}

