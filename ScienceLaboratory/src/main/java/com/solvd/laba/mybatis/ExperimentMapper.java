package com.solvd.laba.mybatis;
import com.solvd.laba.domain.Equipment;
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
    @Select("SELECT eq.* FROM equipment eq " +
            "JOIN experiment_equipment ee ON eq.equipment_id = ee.equipment_id " +
            "WHERE ee.experiment_id = #{experimentId}")
    List<Equipment> getEquipmentForExperiment(int experimentId);
    @Insert("INSERT INTO experiment_equipment (experiment_id, equipment_id) VALUES (#{experimentId}, #{equipmentId})")
    void addEquipmentToExperiment(@Param("experimentId") int experimentId, @Param("equipmentId") int equipmentId);


    @Delete("DELETE FROM experiment_equipment WHERE experiment_id = #{experimentId} AND equipment_id = #{equipmentId}")
    void removeEquipmentFromExperiment(@Param("experimentId") int experimentId, @Param("equipmentId") int equipmentId);


    @Update("UPDATE experiment_equipment SET equipment_id = #{newEquipmentId} WHERE experiment_id = #{experimentId} AND equipment_id = #{oldEquipmentId}")
    void updateEquipmentForExperiment(@Param("experimentId") int experimentId, @Param("oldEquipmentId") int oldEquipmentId, @Param("newEquipmentId") int newEquipmentId);


}
