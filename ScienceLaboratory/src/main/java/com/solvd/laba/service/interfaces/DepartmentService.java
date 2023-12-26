package com.solvd.laba.service.interfaces;

import com.google.protobuf.ServiceException;
import com.solvd.laba.domain.Department;

import java.util.List;

public interface DepartmentService {
    void addDepartment(Department department) throws ServiceException;
    Department getDepartmentById(int id) throws ServiceException;
    List<Department> getAllDepartments() throws ServiceException;
    void updateDepartment(Department department) throws ServiceException;
    void deleteDepartment(int id) throws ServiceException;
}
