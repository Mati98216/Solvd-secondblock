package com.solvd.laba.service.mybatis;

import com.solvd.laba.domain.Equipment;
import com.solvd.laba.mybatis.EquipmentMapper;
import com.solvd.laba.service.interfaces.EquipmentService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class EquipmentServiceImpl implements EquipmentService {
    private final SqlSessionFactory sqlSessionFactory;

    public EquipmentServiceImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void addEquipment(Equipment equipment) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EquipmentMapper mapper = session.getMapper(EquipmentMapper.class);
            mapper.insert(equipment);
            session.commit();
        }
    }

    @Override
    public Equipment getEquipmentById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EquipmentMapper mapper = session.getMapper(EquipmentMapper.class);
            return mapper.getById(id);
        }
    }

    @Override
    public List<Equipment> getAllEquipment() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EquipmentMapper mapper = session.getMapper(EquipmentMapper.class);
            return mapper.findAll();
        }
    }

    @Override
    public void updateEquipment(Equipment equipment) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EquipmentMapper mapper = session.getMapper(EquipmentMapper.class);
            mapper.update(equipment);
            session.commit();
        }
    }

    @Override
    public void deleteEquipment(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EquipmentMapper mapper = session.getMapper(EquipmentMapper.class);
            mapper.delete(id);
            session.commit();
        }
    }
}
