package com.solvd.laba.service.impl;

import com.solvd.laba.domain.ExperimentPublications;
import com.solvd.laba.mybatis.ExperimentPublicationsMapper;
import com.solvd.laba.service.interfaces.ExperimentPublicationsService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class ExperimentPublicationsServiceImpl implements ExperimentPublicationsService {
    private final SqlSessionFactory sqlSessionFactory;

    public ExperimentPublicationsServiceImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void addExperimentPublications(ExperimentPublications experimentPublications) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ExperimentPublicationsMapper mapper = session.getMapper(ExperimentPublicationsMapper.class);
            mapper.insert(experimentPublications);
            session.commit();
        }
    }

    @Override
    public ExperimentPublications getExperimentPublicationsById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ExperimentPublicationsMapper mapper = session.getMapper(ExperimentPublicationsMapper.class);
            return mapper.getById(id);
        }
    }

    @Override
    public List<ExperimentPublications> getAllExperimentPublications() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ExperimentPublicationsMapper mapper = session.getMapper(ExperimentPublicationsMapper.class);
            return mapper.findAll();
        }
    }

    @Override
    public void updateExperimentPublications(ExperimentPublications experimentPublications) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ExperimentPublicationsMapper mapper = session.getMapper(ExperimentPublicationsMapper.class);
            mapper.update(experimentPublications);
            session.commit();
        }
    }

    @Override
    public void deleteExperimentPublications(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ExperimentPublicationsMapper mapper = session.getMapper(ExperimentPublicationsMapper.class);
            mapper.delete(id);
            session.commit();
        }
    }
}
