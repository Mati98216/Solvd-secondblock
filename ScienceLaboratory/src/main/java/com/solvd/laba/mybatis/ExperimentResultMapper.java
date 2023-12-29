package com.solvd.laba.mybatis;

import com.solvd.laba.domain.ExperimentResult;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ExperimentResultMapper {

    @Insert("INSERT INTO experiment_results (experiment_id, analysis_id, result_details) " +
            "VALUES (#{experiment.experimentId}, #{analysis.analysisId}, #{resultDetails})")
    @Options(useGeneratedKeys = true, keyProperty = "resultId")
    void insert(ExperimentResult experimentResult);

    @Select("SELECT er.*, e.experiment_name, a.analysis_name " +
            "FROM experiment_results er " +
            "JOIN experiments e ON er.experiment_id = e.experiment_id " +
            "JOIN analyses a ON er.analysis_id = a.analysis_id " +
            "WHERE result_id = #{id}")
    @Results(value = {
            @Result(property = "resultId", column = "result_id"),
            @Result(property = "experiment.experimentId", column = "experiment_id"),
            @Result(property = "experiment.name", column = "experiment_name"),
            @Result(property = "analysis.analysisId", column = "analysis_id"),
            @Result(property = "analysis.name", column = "analysis_name"),
            @Result(property = "resultDetails", column = "result_details")
    })
    ExperimentResult getById(int id);

    @Update("UPDATE experiment_results SET experiment_id = #{experiment.experimentId}, " +
            "analysis_id = #{analysis.analysisId}, result_details = #{resultDetails} " +
            "WHERE result_id = #{resultId}")
    void update(ExperimentResult experimentResult);

    @Delete("DELETE FROM experiment_results WHERE result_id = #{id}")
    void delete(int id);

    @Select("SELECT er.*, e.experiment_name, a.analysis_name " +
            "FROM experiment_results er " +
            "JOIN experiments e ON er.experiment_id = e.experiment_id " +
            "JOIN analyses a ON er.analysis_id = a.analysis_id")
    @Results(value = {
            @Result(property = "resultId", column = "result_id"),
            @Result(property = "experiment.experimentId", column = "experiment_id"),
            @Result(property = "experiment.name", column = "experiment_name"),
            @Result(property = "analysis.analysisId", column = "analysis_id"),
            @Result(property = "analysis.name", column = "analysis_name"),
            @Result(property = "resultDetails", column = "result_details")
    })
    List<ExperimentResult> findAll();
}

