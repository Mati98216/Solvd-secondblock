package com.solvd.laba.service.mybatis;

import com.solvd.laba.domain.ExperimentTimeline;
import com.solvd.laba.mybatis.ExperimentTimelineMapper;
import com.solvd.laba.service.interfaces.ExperimentTimelineService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class ExperimentTimelineServiceImpl implements ExperimentTimelineService {
    private final SqlSessionFactory sqlSessionFactory;

    public ExperimentTimelineServiceImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void addExperimentTimeline(ExperimentTimeline experimentTimeline) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ExperimentTimelineMapper mapper = session.getMapper(ExperimentTimelineMapper.class);
            mapper.insert(experimentTimeline);
            session.commit();
        }
    }

    @Override
    public ExperimentTimeline getExperimentTimelineById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ExperimentTimelineMapper mapper = session.getMapper(ExperimentTimelineMapper.class);
            return mapper.getById(id);
        }
    }

    @Override
    public List<ExperimentTimeline> getAllExperimentTimelines() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ExperimentTimelineMapper mapper = session.getMapper(ExperimentTimelineMapper.class);
            return mapper.findAll();
        }
    }

    @Override
    public void updateExperimentTimeline(ExperimentTimeline experimentTimeline) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ExperimentTimelineMapper mapper = session.getMapper(ExperimentTimelineMapper.class);
            mapper.update(experimentTimeline);
            session.commit();
        }
    }

    @Override
    public void deleteExperimentTimeline(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ExperimentTimelineMapper mapper = session.getMapper(ExperimentTimelineMapper.class);
            mapper.delete(id);
            session.commit();
        }
    }
}
