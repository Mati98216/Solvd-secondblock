package com.solvd.laba.dao.interfaces;

import com.solvd.laba.domain.Equipment;

import java.sql.SQLException;
import java.util.List;

public interface EquipmentDAO {
    void addEquipment(Equipment equipment) throws SQLException;
    Equipment getEquipmentById(int id) throws SQLException;
    List<Equipment> getAllEquipment() throws SQLException;
    void updateEquipment(Equipment equipment) throws SQLException, InterruptedException;
    void deleteEquipment(int id) throws SQLException;
}