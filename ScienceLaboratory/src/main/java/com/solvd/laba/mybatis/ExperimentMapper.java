package com.solvd.laba.mybatis;
import com.solvd.laba.domain.Experiment;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ExperimentMapper {

    @Insert("INSERT INTO experiments (experiment_name, scientist_id) " +
            "VALUES (#{experimentName}, #{scientist.scientistId})")
    @Options(useGeneratedKeys = true, keyProperty = "experimentId")
    void insert(Experiment experiment);

    @Select("SELECT e.*, s.name as scientist_name " +
            "FROM experiments e " +
            "LEFT JOIN scientists s ON e.scientist_id = s.scientist_id " +
            "WHERE experiment_id = #{id}")
    @Results(value = {
            @Result(property = "experimentId", column = "experiment_id"),
            @Result(property = "experimentName", column = "experiment_name"),
            @Result(property = "scientist.scientistId", column = "scientist_id"),
            @Result(property = "scientist.name", column = "scientist_name")
    })
    Experiment getById(int id);

    @Update("UPDATE experiments SET experiment_name = #{experimentName}, " +
            "scientist_id = #{scientist.scientistId} WHERE experiment_id = #{experimentId}")
    void update(Experiment experiment);

    @Delete("DELETE FROM experiments WHERE experiment_id = #{id}")
    void delete(int id);

    @Select("SELECT e.*, s.name as scientist_name " +
            "FROM experiments e " +
            "LEFT JOIN scientists s ON e.scientist_id = s.scientist_id")
    @Results(value = {
            @Result(property = "experimentId", column = "experiment_id"),
            @Result(property = "experimentName", column = "experiment_name"),
            @Result(property = "scientist.scientistId", column = "scientist_id"),
            @Result(property = "scientist.name", column = "scientist_name")
    })
    List<Experiment> findAll();
}
