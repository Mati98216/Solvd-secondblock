package com.solvd.laba.service.mybatis;

import com.solvd.laba.domain.LaboratoryAssistant;
import com.solvd.laba.mybatis.LaboratoryAssistantMapper;
import com.solvd.laba.service.interfaces.LaboratoryAssistantService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class LaboratoryAssistantServiceImpl implements LaboratoryAssistantService {
    private final SqlSessionFactory sqlSessionFactory;

    public LaboratoryAssistantServiceImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void addLaboratoryAssistant(LaboratoryAssistant laboratoryAssistant) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            LaboratoryAssistantMapper mapper = session.getMapper(LaboratoryAssistantMapper.class);
            mapper.insert(laboratoryAssistant);
            session.commit();
        }
    }

    @Override
    public LaboratoryAssistant getLaboratoryAssistantById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            LaboratoryAssistantMapper mapper = session.getMapper(LaboratoryAssistantMapper.class);
            return mapper.getById(id);
        }
    }

    @Override
    public List<LaboratoryAssistant> getAllLaboratoryAssistants() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            LaboratoryAssistantMapper mapper = session.getMapper(LaboratoryAssistantMapper.class);
            return mapper.findAll();
        }
    }

    @Override
    public void updateLaboratoryAssistant(LaboratoryAssistant laboratoryAssistant) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            LaboratoryAssistantMapper mapper = session.getMapper(LaboratoryAssistantMapper.class);
            mapper.update(laboratoryAssistant);
            session.commit();
        }
    }

    @Override
    public void deleteLaboratoryAssistant(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            LaboratoryAssistantMapper mapper = session.getMapper(LaboratoryAssistantMapper.class);
            mapper.delete(id);
            session.commit();
        }
    }
}
