package com.solvd.laba.service.mybatis;

import com.solvd.laba.domain.Experiment;
import com.solvd.laba.domain.Publication;
import com.solvd.laba.mybatis.PublicationMapper;
import com.solvd.laba.service.interfaces.PublicationService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class PublicationServiceImpl implements PublicationService {
    private SqlSessionFactory sqlSessionFactory;

    public PublicationServiceImpl() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("mybatis-config.xml")) {
            this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("Error initializing SqlSessionFactory", e);
        }
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
    public void addExperimentToPublication(int publicationId, int experimentId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PublicationMapper mapper = session.getMapper(PublicationMapper.class);
            mapper.addExperimentToPublication(publicationId, experimentId);
            session.commit();
        }
    }

    public List<Experiment> getExperimentsForPublication(int publicationId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PublicationMapper mapper = session.getMapper(PublicationMapper.class);
            return mapper.getExperimentsForPublication(publicationId);
        }
    }

    public void updateExperimentForPublication(int publicationId, int oldExperimentId, int newExperimentId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PublicationMapper mapper = session.getMapper(PublicationMapper.class);
            mapper.updateExperimentForPublication(publicationId, oldExperimentId, newExperimentId);
            session.commit();
        }
    }

    public void removeExperimentFromPublication(int publicationId, int experimentId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PublicationMapper mapper = session.getMapper(PublicationMapper.class);
            mapper.removeExperimentFromPublication(publicationId, experimentId);
            session.commit();
        }
    }
}

