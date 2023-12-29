package com.solvd.laba.service.impl;

import com.solvd.laba.domain.Publication;
import com.solvd.laba.mybatis.PublicationMapper;
import com.solvd.laba.service.interfaces.PublicationService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class PublicationServiceImpl implements PublicationService {
    private final SqlSessionFactory sqlSessionFactory;

    public PublicationServiceImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void addPublication(Publication publication) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PublicationMapper mapper = session.getMapper(PublicationMapper.class);
            mapper.insert(publication);
            session.commit();
        }
    }

    @Override
    public Publication getPublicationById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PublicationMapper mapper = session.getMapper(PublicationMapper.class);
            return mapper.getById(id);
        }
    }

    @Override
    public List<Publication> getAllPublications() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PublicationMapper mapper = session.getMapper(PublicationMapper.class);
            return mapper.findAll();
        }
    }

    @Override
    public void updatePublication(Publication publication) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PublicationMapper mapper = session.getMapper(PublicationMapper.class);
            mapper.update(publication);
            session.commit();
        }
    }

    @Override
    public void deletePublication(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PublicationMapper mapper = session.getMapper(PublicationMapper.class);
            mapper.delete(id);
            session.commit();
        }
    }
}

