package com.solvd.laba.mybatis;
import com.solvd.laba.domain.Equipment;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface EquipmentMapper {

    @Insert("INSERT INTO equipment (equipment_name, department_id) " +
            "VALUES (#{equipmentName}, #{department.departmentId})")
    @Options(useGeneratedKeys = true, keyProperty = "equipmentId")
    void insert(Equipment equipment);

    @Select("SELECT eq.*, d.department_name " +
            "FROM equipment eq " +
            "LEFT JOIN departments d ON eq.department_id = d.department_id " +
            "WHERE equipment_id = #{id}")
    @Results(value = {
            @Result(property = "equipmentId", column = "equipment_id"),
            @Result(property = "equipmentName", column = "equipment_name"),
            @Result(property = "department.departmentId", column = "department_id"),
            @Result(property = "department.departmentName", column = "department_name")
    })
    Equipment getById(int id);

    @Update("UPDATE equipment SET equipment_name = #{equipmentName}, " +
            "department_id = #{department.departmentId} WHERE equipment_id = #{equipmentId}")
    void update(Equipment equipment);

    @Delete("DELETE FROM equipment WHERE equipment_id = #{id}")
    void delete(int id);

    @Select("SELECT eq.*, d.department_name " +
            "FROM equipment eq " +
            "LEFT JOIN departments d ON eq.department_id = d.department_id")
    @Results(value = {
            @Result(property = "equipmentId", column = "equipment_id"),
            @Result(property = "equipmentName", column = "equipment_name"),
            @Result(property = "department.departmentId", column = "department_id"),
            @Result(property = "department.departmentName", column = "department_name")
    })
    List<Equipment> findAll();
}
