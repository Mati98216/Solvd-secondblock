package com.solvd.laba.mybatis;
import com.solvd.laba.domain.ExperimentTimeline;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ExperimentTimelineMapper {

    @Insert("INSERT INTO experiment_timelines (experiment_id, start_date, end_date) " +
            "VALUES (#{experiment.experimentId}, #{startDate}, #{endDate})")
    @Options(useGeneratedKeys = true, keyProperty = "timelineId")
    void insert(ExperimentTimeline timeline);

    @Select("SELECT et.*, e.experiment_name " +
            "FROM experiment_timelines et " +
            "JOIN experiments e ON et.experiment_id = e.experiment_id " +
            "WHERE timeline_id = #{id}")
    @Results(value = {
            @Result(property = "timelineId", column = "timeline_id"),
            @Result(property = "experiment.experimentId", column = "experiment_id"),
            @Result(property = "experiment.name", column = "experiment_name"),
            @Result(property = "startDate", column = "start_date"),
            @Result(property = "endDate", column = "end_date")
    })
    ExperimentTimeline getById(int id);

    @Update("UPDATE experiment_timelines SET experiment_id = #{experiment.experimentId}, " +
            "start_date = #{startDate}, end_date = #{endDate} WHERE timeline_id = #{timelineId}")
    void update(ExperimentTimeline timeline);

    @Delete("DELETE FROM experiment_timelines WHERE timeline_id = #{id}")
    void delete(int id);

    @Select("SELECT et.*, e.experiment_name " +
            "FROM experiment_timelines et " +
            "JOIN experiments e ON et.experiment_id = e.experiment_id")
    @Results(value = {
            @Result(property = "timelineId", column = "timeline_id"),
            @Result(property = "experiment.experimentId", column = "experiment_id"),
            @Result(property = "experiment.name", column = "experiment_name"),
            @Result(property = "startDate", column = "start_date"),
            @Result(property = "endDate", column = "end_date")
    })
    List<ExperimentTimeline> findAll();
}
