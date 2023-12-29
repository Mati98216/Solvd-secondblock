package com.solvd.laba.mybatis;
import com.solvd.laba.domain.ExperimentEquipment;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ExperimentEquipmentMapper {

    @Insert("INSERT INTO experiment_equipment (experiment_id, equipment_id) " +
            "VALUES (#{experiment.experimentId}, #{equipment.equipmentId})")
    @Options(useGeneratedKeys = true, keyProperty = "experimentEquipmentId")
    void insert(ExperimentEquipment experimentEquipment);

    @Select("SELECT ee.*, e.experiment_name, eq.equipment_name " +
            "FROM experiment_equipment ee " +
            "JOIN experiments e ON ee.experiment_id = e.experiment_id " +
            "JOIN equipment eq ON ee.equipment_id = eq.equipment_id " +
            "WHERE experiment_equipment_id = #{id}")
    @Results(value = {
            @Result(property = "experimentEquipmentId", column = "experiment_equipment_id"),
            @Result(property = "experiment.experimentId", column = "experiment_id"),
            @Result(property = "experiment.name", column = "experiment_name"),
            @Result(property = "equipment.equipmentId", column = "equipment_id"),
            @Result(property = "equipment.name", column = "equipment_name")
    })
    ExperimentEquipment getById(int id);

    @Update("UPDATE experiment_equipment SET experiment_id = #{experiment.experimentId}, " +
            "equipment_id = #{equipment.equipmentId} WHERE experiment_equipment_id = #{experimentEquipmentId}")
    void update(ExperimentEquipment experimentEquipment);

    @Delete("DELETE FROM experiment_equipment WHERE experiment_equipment_id = #{id}")
    void delete(int id);

    @Select("SELECT ee.*, e.experiment_name, eq.equipment_name " +
            "FROM experiment_equipment ee " +
            "JOIN experiments e ON ee.experiment_id = e.experiment_id " +
            "JOIN equipment eq ON ee.equipment_id = eq.equipment_id")
    @Results(value = {
            @Result(property = "experimentEquipmentId", column = "experiment_equipment_id"),
            @Result(property = "experiment.experimentId", column = "experiment_id"),
            @Result(property = "experiment.name", column = "experiment_name"),
            @Result(property = "equipment.equipmentId", column = "equipment_id"),
            @Result(property = "equipment.name", column = "equipment_name")
    })
    List<ExperimentEquipment> findAll();
}
