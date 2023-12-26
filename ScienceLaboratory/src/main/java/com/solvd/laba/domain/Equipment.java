package com.solvd.laba.domain;

public class Equipment {
    private int equipmentId;
    private String equipmentName;
    private int departmentId;

    public Equipment() {
    }

    public Equipment(int equipmentId, String equipmentName, int departmentId) {
        this.equipmentId = equipmentId;
        this.equipmentName = equipmentName;
        this.departmentId = departmentId;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
}
