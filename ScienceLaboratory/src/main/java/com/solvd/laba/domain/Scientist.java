package com.solvd.laba.domain;

public class Scientist {
    private int scientistId;
    private String name;
    private String email;
    private int departmentId;
    private int areaId;

    public Scientist(int scientistId, String name, String email, int departmentId, int areaId) {
        this.scientistId = scientistId;
        this.name = name;
        this.email = email;
        this.departmentId = departmentId;
        this.areaId = areaId;
    }

    public int getScientistId() {
        return scientistId;
    }

    public void setScientistId(int scientistId) {
        this.scientistId = scientistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }
}
