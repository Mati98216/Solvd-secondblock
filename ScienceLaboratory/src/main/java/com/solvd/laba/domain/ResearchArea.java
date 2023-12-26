package com.solvd.laba.domain;

public class ResearchArea {
    private int areaId;
    private String areaName;


    public ResearchArea() {
    }

    public ResearchArea(int areaId, String areaName) {
        this.areaId = areaId;
        this.areaName = areaName;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
