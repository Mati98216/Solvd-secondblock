package com.solvd.laba.service.jdbc;

import com.google.protobuf.ServiceException;
import com.solvd.laba.dao.impl.DepartmentDAOImpl;
import com.solvd.laba.dao.interfaces.DepartmentDAO;
import com.solvd.laba.database.DatabaseConfig;
import com.solvd.laba.domain.Department;
import com.solvd.laba.service.interfaces.DepartmentService;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentDAO departmentDAO;

    public DepartmentServiceImpl() {
        this.departmentDAO = new DepartmentDAOImpl(DatabaseConfig.getConnectionPool());
    }

    @Override
    public void addDepartment(Department department) throws ServiceException {
        try {

            departmentDAO.create(department);
        } catch (Exception e) {
            throw new ServiceException("Error adding department", e);
        }
    }

    @Override
    public Department getDepartmentById(int id) throws ServiceException {
        try {
            return departmentDAO.read(id);
        } catch (Exception e) {
            throw new ServiceException("Error retrieving department", e);
        }
    }

    @Override
    public List<Department> getAllDepartments() throws ServiceException {
        try {
            return departmentDAO.findAll();
        } catch (Exception e) {
            throw new ServiceException("Error retrieving all departments", e);
        }
    }

    @Override
    public void updateDepartment(Department department) throws ServiceException {
        try {

            departmentDAO.update(department);
        } catch (Exception e) {
            throw new ServiceException("Error updating department", e);
        }
    }

    @Override
    public void deleteDepartment(int id) throws ServiceException {
        try {
            departmentDAO.delete(id);
        } catch (Exception e) {
            throw new ServiceException("Error deleting department", e);
        }
    }
}

