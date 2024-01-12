package com.solvd.laba.service.mybatis;

import com.solvd.laba.domain.Equipment;
import com.solvd.laba.domain.Experiment;
import com.solvd.laba.mybatis.ExperimentMapper;
import com.solvd.laba.service.interfaces.ExperimentService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class ExperimentServiceImpl implements ExperimentService {
    private SqlSessionFactory sqlSessionFactory;

    public ExperimentServiceImpl() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("mybatis-config.xml")) {
            this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("Error initializing SqlSessionFactory", e);
        }
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
    public void addEquipmentToExperiment(int experimentId, int equipmentId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ExperimentMapper mapper = session.getMapper(ExperimentMapper.class);
            mapper.addEquipmentToExperiment(experimentId, equipmentId);
            session.commit();
        }
    }

    public void removeEquipmentFromExperiment(int experimentId, int equipmentId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ExperimentMapper mapper = session.getMapper(ExperimentMapper.class);
            mapper.removeEquipmentFromExperiment(experimentId, equipmentId);
            session.commit();
        }
    }

    public void updateEquipmentForExperiment(int experimentId, int oldEquipmentId, int newEquipmentId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ExperimentMapper mapper = session.getMapper(ExperimentMapper.class);
            mapper.updateEquipmentForExperiment(experimentId, oldEquipmentId, newEquipmentId);
            session.commit();
        }
    }
    public List<Equipment> getEquipmentForExperiment(int experimentId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ExperimentMapper mapper = session.getMapper(ExperimentMapper.class);
            return mapper.getEquipmentForExperiment(experimentId);
        }
    }
}

