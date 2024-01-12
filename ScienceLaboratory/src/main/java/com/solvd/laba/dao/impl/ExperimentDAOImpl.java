package com.solvd.laba.dao.impl;

import com.solvd.laba.dao.interfaces.ExperimentDAO;
import com.solvd.laba.database.ConnectionPool;
import com.solvd.laba.domain.Equipment;
import com.solvd.laba.domain.Experiment;
import com.solvd.laba.domain.Scientist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ExperimentDAOImpl extends AbstractDAO<Experiment, Integer> implements ExperimentDAO {

    public ExperimentDAOImpl(ConnectionPool connectionPool) {
        super(connectionPool);
    }

    @Override
    protected Experiment createEntity(ResultSet resultSet) throws SQLException, InterruptedException {
        Experiment experiment = new Experiment();
        experiment.setExperimentId(resultSet.getInt("experiment_id"));
        experiment.setExperimentName(resultSet.getString("experiment_name"));
        experiment.setScientist(createScientist(resultSet));
        experiment.setEquipmentList(getEquipmentForExperiment(experiment.getExperimentId()));
        return experiment;
    }

    private Scientist createScientist(ResultSet resultSet) throws SQLException {
        Scientist scientist = new Scientist();
        scientist.setScientistId(resultSet.getInt("scientist_id"));
        scientist.setName(resultSet.getString("name"));
        scientist.setEmail(resultSet.getString("email"));
        return scientist;
    }
    @Override
    public void addEquipmentToExperiment(int experimentId, int equipmentId) throws SQLException {
        try (Connection connection = connectionPool.getConnection().getSqlConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO experiment_equipment (experiment_id, equipment_id) VALUES (?, ?)")) {
            statement.setInt(1, experimentId);
            statement.setInt(2, equipmentId);
            statement.executeUpdate();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Equipment> getEquipmentForExperiment(int experimentId) throws SQLException, InterruptedException {
        List<Equipment> equipmentList = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection().getSqlConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT e.* FROM equipment e " +
                             "JOIN experiment_equipment ee ON e.equipment_id = ee.equipment_id " +
                             "WHERE ee.experiment_id = ?")) {
            statement.setInt(1, experimentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Equipment equipment = new Equipment();
                    equipment.setEquipmentId(resultSet.getInt("equipment_id"));
                    equipment.setEquipmentName(resultSet.getString("equipment_name"));
                    equipmentList.add(equipment);
                }
            }
        }
        return equipmentList;
    }
    @Override
    public void updateEquipmentForExperiment(int experimentId, int oldEquipmentId, int newEquipmentId) throws SQLException {
        try (Connection connection = connectionPool.getConnection().getSqlConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE experiment_equipment SET equipment_id = ? WHERE experiment_id = ? AND equipment_id = ?")) {
            statement.setInt(1, newEquipmentId);
            statement.setInt(2, experimentId);
            statement.setInt(3, oldEquipmentId);
            statement.executeUpdate();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void removeEquipmentFromExperiment(int experimentId, int equipmentId) throws SQLException, InterruptedException {
        try (Connection connection = connectionPool.getConnection().getSqlConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM experiment_equipment WHERE experiment_id = ? AND equipment_id = ?")) {
            statement.setInt(1, experimentId);
            statement.setInt(2, equipmentId);
            statement.executeUpdate();
        }
    }



    @Override
    protected String getCreateQuery() {
        return "INSERT INTO experiments (experiment_name, scientist_id) VALUES (?, ?)";
    }

    @Override
    protected void setCreateStatement(PreparedStatement statement, Experiment experiment) throws SQLException {
        statement.setString(1, experiment.getExperimentName());
        statement.setInt(2, experiment.getScientist().getScientistId());
    }

    @Override
    protected String getReadQuery() {
        return "SELECT e.*, s.scientist_id, s.name as scientist_name, s.email as scientist_email " +
                "FROM experiments e " +
                "JOIN scientists s ON e.scientist_id = s.scientist_id " +
                "WHERE e.experiment_id = ?";
    }

    @Override
    protected void setReadStatement(PreparedStatement statement, Integer id) throws SQLException {
        statement.setInt(1, id);
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE experiments SET experiment_name = ?, scientist_id = ? WHERE experiment_id = ?";
    }

    @Override
    protected void setUpdateStatement(PreparedStatement statement, Experiment experiment) throws SQLException {
        statement.setString(1, experiment.getExperimentName());
        statement.setInt(2, experiment.getScientist().getScientistId());
        statement.setInt(3, experiment.getExperimentId());
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM experiments WHERE experiment_id = ?";
    }

    @Override
    protected void setDeleteStatement(PreparedStatement statement, Integer id) throws SQLException {
        statement.setInt(1, id);
    }

    @Override
    protected String getFindAllQuery() {
        return "SELECT e.*, s.scientist_id, s.name as scientist_name, s.email as scientist_email " +
                "FROM experiments e " +
                "JOIN scientists s ON e.scientist_id = s.scientist_id";
    }
}
