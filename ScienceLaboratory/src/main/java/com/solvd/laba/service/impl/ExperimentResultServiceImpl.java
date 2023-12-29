package com.solvd.laba.service.impl;

import com.solvd.laba.domain.ExperimentResult;
import com.solvd.laba.mybatis.ExperimentResultMapper;
import com.solvd.laba.service.interfaces.ExperimentResultService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class ExperimentResultServiceImpl implements ExperimentResultService {
    private final SqlSessionFactory sqlSessionFactory;

    public ExperimentResultServiceImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void addExperimentResult(ExperimentResult experimentResult) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ExperimentResultMapper mapper = session.getMapper(ExperimentResultMapper.class);
            mapper.insert(experimentResult);
            session.commit();
        }
    }

    @Override
    public ExperimentResult getExperimentResultById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ExperimentResultMapper mapper = session.getMapper(ExperimentResultMapper.class);
            return mapper.getById(id);
        }
    }

    @Override
    public List<ExperimentResult> getAllExperimentResults() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ExperimentResultMapper mapper = session.getMapper(ExperimentResultMapper.class);
            return mapper.findAll();
        }
    }

    @Override
    public void updateExperimentResult(ExperimentResult experimentResult) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ExperimentResultMapper mapper = session.getMapper(ExperimentResultMapper.class);
            mapper.update(experimentResult);
            session.commit();
        }
    }

    @Override
    public void deleteExperimentResult(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ExperimentResultMapper mapper = session.getMapper(ExperimentResultMapper.class);
            mapper.delete(id);
            session.commit();
        }
    }
}
