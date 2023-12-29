package com.solvd.laba.service.impl;

import com.solvd.laba.domain.Department;
import com.solvd.laba.mybatis.DepartmentMapper;
import com.solvd.laba.service.interfaces.DepartmentService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {
    private final SqlSessionFactory sqlSessionFactory;

    public DepartmentServiceImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void addDepartment(Department department) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            DepartmentMapper mapper = session.getMapper(DepartmentMapper.class);
            mapper.insert(department);
            session.commit();
        }
    }

    @Override
    public Department getDepartmentById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            DepartmentMapper mapper = session.getMapper(DepartmentMapper.class);
            return mapper.getById(id);
        }
    }

    @Override
    public List<Department> getAllDepartments() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            DepartmentMapper mapper = session.getMapper(DepartmentMapper.class);
            return mapper.findAll();
        }
    }

    @Override
    public void updateDepartment(Department department) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            DepartmentMapper mapper = session.getMapper(DepartmentMapper.class);
            mapper.update(department);
            session.commit();
        }
    }

    @Override
    public void deleteDepartment(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            DepartmentMapper mapper = session.getMapper(DepartmentMapper.class);
            mapper.delete(id);
            session.commit();
        }
    }
}
