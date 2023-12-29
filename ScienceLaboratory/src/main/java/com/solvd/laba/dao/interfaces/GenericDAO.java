package com.solvd.laba.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T, ID> {
    T create(T t) throws SQLException, InterruptedException;
    T read(ID id) throws SQLException, InterruptedException;
    T update(T t) throws SQLException, InterruptedException;
    List<T> findAll() throws SQLException, InterruptedException;
    void delete(ID id) throws SQLException, InterruptedException;
}
