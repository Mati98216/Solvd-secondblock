package com.solvd.laba.service.impl;

import com.solvd.laba.domain.ExperimentEquipment;
import com.solvd.laba.mybatis.ExperimentEquipmentMapper;
import com.solvd.laba.service.interfaces.ExperimentEquipmentService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class ExperimentEquipmentServiceImpl implements ExperimentEquipmentService {
    private final SqlSessionFactory sqlSessionFactory;

    public ExperimentEquipmentServiceImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void addExperimentEquipment(ExperimentEquipment experimentEquipment) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ExperimentEquipmentMapper mapper = session.getMapper(ExperimentEquipmentMapper.class);
            mapper.insert(experimentEquipment);
            session.commit();
        }
    }

    @Override
    public ExperimentEquipment getExperimentEquipmentById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ExperimentEquipmentMapper mapper = session.getMapper(ExperimentEquipmentMapper.class);
            return mapper.getById(id);
        }
    }

    @Override
    public List<ExperimentEquipment> getAllExperimentEquipment() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ExperimentEquipmentMapper mapper = session.getMapper(ExperimentEquipmentMapper.class);
            return mapper.findAll();
        }
    }

    @Override
    public void updateExperimentEquipment(ExperimentEquipment experimentEquipment) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ExperimentEquipmentMapper mapper = session.getMapper(ExperimentEquipmentMapper.class);
            mapper.update(experimentEquipment);
            session.commit();
        }
    }

    @Override
    public void deleteExperimentEquipment(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ExperimentEquipmentMapper mapper = session.getMapper(ExperimentEquipmentMapper.class);
            mapper.delete(id);
            session.commit();
        }
    }
}

