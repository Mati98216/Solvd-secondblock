package com.solvd.laba.domain;

public class LaboratoryAssistant {
    private int assistantId;
    private String name;
    private String email;
    private int departmentId;
    private int areaId;


    public LaboratoryAssistant() {
    }

    public LaboratoryAssistant(int assistantId, String name, String email, int departmentId, int areaId) {
        this.assistantId = assistantId;
        this.name = name;
        this.email = email;
        this.departmentId = departmentId;
        this.areaId = areaId;
    }

    public int getAssistantId() {
        return assistantId;
    }

    public void setAssistantId(int assistantId) {
        this.assistantId = assistantId;
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
