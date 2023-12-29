package com.solvd.laba.mybatis;

import com.solvd.laba.domain.LaboratoryAssistant;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface LaboratoryAssistantMapper {

    @Insert("INSERT INTO laboratory_assistants (name, email, department_id, area_id) " +
            "VALUES (#{name}, #{email}, #{department.departmentId}, #{area.areaId})")
    @Options(useGeneratedKeys = true, keyProperty = "assistantId")
    void insert(LaboratoryAssistant assistant);

    @Select("SELECT la.*, d.department_name, ra.area_name " +
            "FROM laboratory_assistants la " +
            "LEFT JOIN departments d ON la.department_id = d.department_id " +
            "LEFT JOIN research_areas ra ON la.area_id = ra.area_id " +
            "WHERE assistant_id = #{id}")
    @Results(value = {
            @Result(property = "assistantId", column = "assistant_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "email", column = "email"),
            @Result(property = "department.departmentId", column = "department_id"),
            @Result(property = "department.departmentName", column = "department_name"),
            @Result(property = "area.areaId", column = "area_id"),
            @Result(property = "area.areaName", column = "area_name")
    })
    LaboratoryAssistant getById(int id);

    @Update("UPDATE laboratory_assistants SET name = #{name}, email = #{email}, " +
            "department_id = #{department.departmentId}, area_id = #{area.areaId} " +
            "WHERE assistant_id = #{assistantId}")
    void update(LaboratoryAssistant assistant);

    @Delete("DELETE FROM laboratory_assistants WHERE assistant_id = #{id}")
    void delete(int id);

    @Select("SELECT la.*, d.department_name, ra.area_name " +
            "FROM laboratory_assistants la " +
            "LEFT JOIN departments d ON la.department_id = d.department_id " +
            "LEFT JOIN research_areas ra ON la.area_id = ra.area_id")
    @Results(value = {
            @Result(property = "assistantId", column = "assistant_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "email", column = "email"),
            @Result(property = "department.departmentId", column = "department_id"),
            @Result(property = "department.departmentName", column = "department_name"),
            @Result(property = "area.areaId", column = "area_id"),
            @Result(property = "area.areaName", column = "area_name")
    })
    List<LaboratoryAssistant> findAll();
}
