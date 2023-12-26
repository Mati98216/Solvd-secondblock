package com.solvd.laba.dao.interfaces;

import com.solvd.laba.domain.Department;

import java.sql.SQLException;
import java.util.List;

public interface DepartmentDAO {
    void addDepartment(Department department) throws SQLException;
    Department getDepartmentById(int id) throws SQLException, InterruptedException;
    List<Department> getAllDepartments() throws SQLException;
    void updateDepartment(Department department) throws SQLException;
    void deleteDepartment(int id) throws SQLException;
}
