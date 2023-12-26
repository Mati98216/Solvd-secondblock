package com.solvd.laba.service.impl;

import com.google.protobuf.ServiceException;
import com.solvd.laba.dao.interfaces.DepartmentDAO;
import com.solvd.laba.domain.Department;
import com.solvd.laba.service.interfaces.DepartmentService;

import java.sql.SQLException;
import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentDAO departmentDAO;

    public DepartmentServiceImpl(DepartmentDAO departmentDAO) {
        this.departmentDAO = departmentDAO;
    }

    @Override
    public void addDepartment(Department department) throws ServiceException {
        try {

            departmentDAO.addDepartment(department);
        } catch (SQLException e) {
            throw new ServiceException("Error adding department", e);
        }
    }

    @Override
    public Department getDepartmentById(int id) throws ServiceException {
        try {

            return departmentDAO.getDepartmentById(id);
        } catch (SQLException e) {
            throw new ServiceException("Error retrieving department by ID", e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Department> getAllDepartments() throws ServiceException {
        try {

            return departmentDAO.getAllDepartments();
        } catch (SQLException e) {
            throw new ServiceException("Error retrieving all departments", e);
        }
    }

    @Override
    public void updateDepartment(Department department) throws ServiceException {
        try {

            departmentDAO.updateDepartment(department);
        } catch (SQLException e) {
            throw new ServiceException("Error updating department", e);
        }
    }

    @Override
    public void deleteDepartment(int id) throws ServiceException {
        try {

            departmentDAO.deleteDepartment(id);
        } catch (SQLException e) {
            throw new ServiceException("Error deleting department", e);
        }
    }
}

