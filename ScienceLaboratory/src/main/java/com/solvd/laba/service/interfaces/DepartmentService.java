package com.solvd.laba.service.interfaces;

import com.solvd.laba.domain.Department;

import java.util.List;

public interface DepartmentService {
    void addDepartment(Department department);
    Department getDepartmentById(int id);
    List<Department> getAllDepartments();
    void updateDepartment(Department department);
    void deleteDepartment(int id);
}
