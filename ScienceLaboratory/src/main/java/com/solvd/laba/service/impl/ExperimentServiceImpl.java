package com.solvd.laba.service.impl;

import com.solvd.laba.domain.Experiment;
import com.solvd.laba.mybatis.ExperimentMapper;
import com.solvd.laba.service.interfaces.ExperimentService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class ExperimentServiceImpl implements ExperimentService {
    private final SqlSessionFactory sqlSessionFactory;

    public ExperimentServiceImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void addExperiment(Experiment experiment) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ExperimentMapper mapper = session.getMapper(ExperimentMapper.class);
            mapper.insert(experiment);
            session.commit();
        }
    }

    @Override
    public Experiment getExperimentById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ExperimentMapper mapper = session.getMapper(ExperimentMapper.class);
            return mapper.getById(id);
        }
    }

    @Override
    public List<Experiment> getAllExperiments() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ExperimentMapper mapper = session.getMapper(ExperimentMapper.class);
            return mapper.findAll();
        }
    }

    @Override
    public void updateExperiment(Experiment experiment) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ExperimentMapper mapper = session.getMapper(ExperimentMapper.class);
            mapper.update(experiment);
            session.commit();
        }
    }

    @Override
    public void deleteExperiment(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ExperimentMapper mapper = session.getMapper(ExperimentMapper.class);
            mapper.delete(id);
            session.commit();
        }
    }
}

