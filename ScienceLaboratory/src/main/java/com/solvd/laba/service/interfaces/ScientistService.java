package com.solvd.laba.service.interfaces;

import com.google.protobuf.ServiceException;
import com.solvd.laba.domain.Scientist;

import java.util.List;

public interface ScientistService {
    void addScientist(Scientist scientist) throws ServiceException;
    Scientist getScientistById(int id) throws ServiceException;
    List<Scientist> getAllScientists() throws ServiceException;
    void updateScientist(Scientist scientist) throws ServiceException;
    void deleteScientist(int id) throws ServiceException;
}
