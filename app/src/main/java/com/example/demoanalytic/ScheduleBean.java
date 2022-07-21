package com.example.demoanalytic;


public class ScheduleBean {
    private String brandId;
    private String modeId;
    private String productId;
    private String equipmentId;
    private String startTime;
    private String endTime;
    private int openIf;
    private int closeIf;
    private String week;
    private int id;  // 0  新增   非0 修改


    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getModeId() {
        return modeId;
    }

    public void setModeId(String modeId) {
        this.modeId = modeId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getOpenIf() {
        return openIf;
    }

    public void setOpenIf(int openIf) {
        this.openIf = openIf;
    }

    public int getCloseIf() {
        return closeIf;
    }

    public void setCloseIf(int closeIf) {
        this.closeIf = closeIf;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
