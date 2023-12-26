package com.solvd.laba.dao.interfaces;

import com.solvd.laba.domain.Scientist;

import java.sql.SQLException;
import java.util.List;

public interface ScientistDAO {
    void addScientist(Scientist scientist) throws SQLException;
    Scientist getScientistById(int id) throws SQLException, InterruptedException;
    List<Scientist> getAllScientists() throws SQLException, InterruptedException;
    void updateScientist(Scientist scientist) throws SQLException, InterruptedException;
    void deleteScientist(int id) throws SQLException, InterruptedException;
}