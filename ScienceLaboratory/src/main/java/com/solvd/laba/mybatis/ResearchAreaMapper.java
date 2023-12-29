package com.solvd.laba.mybatis;

import com.solvd.laba.domain.ResearchArea;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ResearchAreaMapper {

    @Insert("INSERT INTO research_areas (area_name) VALUES (#{areaName})")
    @Options(useGeneratedKeys = true, keyProperty = "areaId")
    void insert(ResearchArea researchArea);

    @Select("SELECT * FROM research_areas WHERE area_id = #{id}")
    ResearchArea getById(int id);

    @Update("UPDATE research_areas SET area_name = #{areaName} WHERE area_id = #{areaId}")
    void update(ResearchArea researchArea);

    @Delete("DELETE FROM research_areas WHERE area_id = #{id}")
    void delete(int id);

    @Select("SELECT * FROM research_areas")
    List<ResearchArea> findAll();
}
