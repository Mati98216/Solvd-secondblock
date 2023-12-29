package com.solvd.laba.service.interfaces;

import com.solvd.laba.domain.Equipment;

import java.util.List;

public interface EquipmentService {
    void addEquipment(Equipment equipment);
    Equipment getEquipmentById(int id);
    List<Equipment> getAllEquipment();
    void updateEquipment(Equipment equipment);
    void deleteEquipment(int id);
}
