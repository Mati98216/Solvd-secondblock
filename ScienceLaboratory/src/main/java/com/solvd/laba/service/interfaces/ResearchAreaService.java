package com.solvd.laba.service.interfaces;

import com.google.protobuf.ServiceException;
import com.solvd.laba.domain.ResearchArea;
import java.util.List;

public interface ResearchAreaService {
    void addResearchArea(ResearchArea researchArea) throws ServiceException;
    ResearchArea getResearchAreaById(int id) throws ServiceException;
    List<ResearchArea> getAllResearchAreas() throws ServiceException;
    void updateResearchArea(ResearchArea researchArea) throws ServiceException;
    void deleteResearchArea(int id) throws ServiceException;
}

