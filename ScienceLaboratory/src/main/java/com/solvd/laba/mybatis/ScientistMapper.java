package com.solvd.laba.mybatis;

import com.solvd.laba.domain.Scientist;
import org.apache.ibatis.annotations.*;
import java.util.List;

public interface ScientistMapper {
    // Insert a new scientist record
    @Insert("INSERT INTO scientists (name, email, department_id, area_id) VALUES (#{name}, #{email}, #{department.departmentId}, #{area.areaId})")
    @Options(useGeneratedKeys = true, keyProperty = "scientistId")
    void insert(Scientist scientist);

    // Select a scientist by ID
    @Select("SELECT s.*, d.department_name, a.area_name " +
            "FROM scientists s " +
            "LEFT JOIN departments d ON s.department_id = d.department_id " +
            "LEFT JOIN research_areas a ON s.area_id = a.area_id " +
            "WHERE scientist_id = #{id}")
    @Results(value = {
            @Result(property = "scientistId", column = "scientist_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "email", column = "email"),
            @Result(property = "department.departmentId", column = "department_id"),
            @Result(property = "department.departmentName", column = "department_name"),
            @Result(property = "area.areaId", column = "area_id"),
            @Result(property = "area.areaName", column = "area_name")
    })
    Scientist getById(int id);

    // Update a scientist record
    @Update("UPDATE scientists SET name = #{name}, email = #{email}, department_id = #{department.departmentId}, area_id = #{area.areaId} WHERE scientist_id = #{scientistId}")
    void update(Scientist scientist);

    // Delete a scientist record
    @Delete("DELETE FROM scientists WHERE scientist_id = #{id}")
    void delete(int id);

    // Select all scientists
    @Select("SELECT s.*, d.department_name, a.area_name " +
            "FROM scientists s " +
            "LEFT JOIN departments d ON s.department_id = d.department_id " +
            "LEFT JOIN research_areas a ON s.area_id = a.area_id")
    @Results(value = {
            @Result(property = "scientistId", column = "scientist_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "email", column = "email"),
            @Result(property = "department.departmentId", column = "department_id"),
            @Result(property = "department.departmentName", column = "department_name"),
            @Result(property = "area.areaId", column = "area_id"),
            @Result(property = "area.areaName", column = "area_name")
    })
    List<Scientist> findAll();
}

