package com.solvd.laba.service.impl;

import com.solvd.laba.domain.Scientist;
import com.solvd.laba.mybatis.ScientistMapper;
import com.solvd.laba.service.interfaces.ScientistService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;


public class ScientistServiceImpl implements ScientistService {
    private SqlSessionFactory sqlSessionFactory;

    public ScientistServiceImpl() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("mybatis-config.xml")) {
            this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("Error initializing SqlSessionFactory", e);
        }
    }

    @Override
    public void addScientist(Scientist scientist) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ScientistMapper mapper = session.getMapper(ScientistMapper.class);
            mapper.insert(scientist);
            session.commit();
        }
    }

    @Override
    public Scientist getScientistById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ScientistMapper mapper = session.getMapper(ScientistMapper.class);
            return mapper.getById(id);
        }
    }

    @Override
    public List<Scientist> getAllScientists() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ScientistMapper mapper = session.getMapper(ScientistMapper.class);
            return mapper.findAll();
        }
    }

    @Override
    public void updateScientist(Scientist scientist) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ScientistMapper mapper = session.getMapper(ScientistMapper.class);
            mapper.update(scientist);
            session.commit();
        }
    }

    @Override
    public void deleteScientist(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ScientistMapper mapper = session.getMapper(ScientistMapper.class);
            mapper.delete(id);
            session.commit();
        }
    }
}