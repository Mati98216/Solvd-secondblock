package com.solvd.laba.service.impl;

import com.solvd.laba.domain.ResearchArea;
import com.solvd.laba.mybatis.ResearchAreaMapper;
import com.solvd.laba.service.interfaces.ResearchAreaService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class ResearchAreaServiceImpl implements ResearchAreaService {
    private final SqlSessionFactory sqlSessionFactory;

    public ResearchAreaServiceImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void addResearchArea(ResearchArea researchArea) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ResearchAreaMapper mapper = session.getMapper(ResearchAreaMapper.class);
            mapper.insert(researchArea);
            session.commit();
        }
    }

    @Override
    public ResearchArea getResearchAreaById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ResearchAreaMapper mapper = session.getMapper(ResearchAreaMapper.class);
            return mapper.getById(id);
        }
    }

    @Override
    public List<ResearchArea> getAllResearchAreas() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ResearchAreaMapper mapper = session.getMapper(ResearchAreaMapper.class);
            return mapper.findAll();
        }
    }

    @Override
    public void updateResearchArea(ResearchArea researchArea) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ResearchAreaMapper mapper = session.getMapper(ResearchAreaMapper.class);
            mapper.update(researchArea);
            session.commit();
        }
    }

    @Override
    public void deleteResearchArea(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ResearchAreaMapper mapper = session.getMapper(ResearchAreaMapper.class);
            mapper.delete(id);
            session.commit();
        }
    }
}
