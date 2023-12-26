package com.solvd.laba.domain;

public class ExperimentEquipment {
    private int experimentEquipmentId;
    private int experimentId;
    private int equipmentId;

    public ExperimentEquipment() {
    }

    public ExperimentEquipment(int experimentEquipmentId, int experimentId, int equipmentId) {
        this.experimentEquipmentId = experimentEquipmentId;
        this.experimentId = experimentId;
        this.equipmentId = equipmentId;
    }

    public int getExperimentEquipmentId() {
        return experimentEquipmentId;
    }

    public void setExperimentEquipmentId(int experimentEquipmentId) {
        this.experimentEquipmentId = experimentEquipmentId;
    }

    public int getExperimentId() {
        return experimentId;
    }

    public void setExperimentId(int experimentId) {
        this.experimentId = experimentId;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }
}
