package com.solvd.laba.service.mybatis;

import com.solvd.laba.domain.Analysis;
import com.solvd.laba.mybatis.AnalysisMapper;
import com.solvd.laba.service.interfaces.AnalysisService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class AnalysisServiceImpl implements AnalysisService {
    private SqlSessionFactory sqlSessionFactory ;

    public AnalysisServiceImpl() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("mybatis-config.xml")) {
            this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("Error initializing SqlSessionFactory", e);
        }
    }

    @Override
    public void addAnalysis(Analysis analysis) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            AnalysisMapper mapper = session.getMapper(AnalysisMapper.class);
            mapper.insert(analysis);
            session.commit();
        }
    }

    @Override
    public Analysis getAnalysisById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            AnalysisMapper mapper = session.getMapper(AnalysisMapper.class);
            return mapper.getById(id);
        }
    }

    @Override
    public List<Analysis> getAllAnalysis() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            AnalysisMapper mapper = session.getMapper(AnalysisMapper.class);
            return mapper.findAll();
        }
    }

    @Override
    public void updateAnalysis(Analysis analysis) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            AnalysisMapper mapper = session.getMapper(AnalysisMapper.class);
            mapper.update(analysis);
            session.commit();
        }
    }

    @Override
    public void deleteAnalysis(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            AnalysisMapper mapper = session.getMapper(AnalysisMapper.class);
            mapper.delete(id);
            session.commit();
        }
    }
}

