package com.solvd.laba.mybatis;
import com.solvd.laba.domain.Analysis;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface AnalysisMapper {

    @Insert("INSERT INTO analyses (analysis_name, scientist_id, assistant_id) VALUES (#{analysisName}, #{scientist.scientistId}, #{assistant.assistantId})")
    @Options(useGeneratedKeys = true, keyProperty = "analysisId")
    void insert(Analysis analysis);

    @Select("SELECT a.analysis_id, a.analysis_name, s.scientist_id, s.name AS scientist_name, la.assistant_id, la.name AS assistant_name " +
            "FROM analyses a " +
            "LEFT JOIN scientists s ON a.scientist_id = s.scientist_id " +
            "LEFT JOIN laboratory_assistants la ON a.assistant_id = la.assistant_id " +
            "WHERE a.analysis_id = #{id}")
    Analysis getById(int id);

    @Update("UPDATE analyses SET analysis_name = #{analysisName}, scientist_id = #{scientist.scientistId}, assistant_id = #{assistant.assistantId} WHERE analysis_id = #{analysisId}")
    void update(Analysis analysis);

    @Delete("DELETE FROM analyses WHERE analysis_id = #{id}")
    void delete(int id);

    @Select("SELECT a.analysis_id, a.analysis_name, s.scientist_id, s.name AS scientist_name, la.assistant_id, la.name AS assistant_name " +
            "FROM analyses a " +
            "LEFT JOIN scientists s ON a.scientist_id = s.scientist_id " +
            "LEFT JOIN laboratory_assistants la ON a.assistant_id = la.assistant_id")
    List<Analysis> findAll();
}
