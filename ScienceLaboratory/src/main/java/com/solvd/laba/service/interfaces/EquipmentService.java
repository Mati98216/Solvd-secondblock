package com.solvd.laba.service.interfaces;

import com.google.protobuf.ServiceException;
import com.solvd.laba.domain.Equipment;

import java.util.List;

public interface EquipmentService {
    void addEquipment(Equipment equipment) throws ServiceException;
    Equipment getEquipmentById(int id) throws ServiceException;
    List<Equipment> getAllEquipment() throws ServiceException;
    void updateEquipment(Equipment equipment) throws ServiceException;
    void deleteEquipment(int id) throws ServiceException;
}
