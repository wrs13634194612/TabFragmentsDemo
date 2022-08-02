package com.example.demoanalytic;


public class CountDownBean {
    /**
     * brandId : 1
     * modeId : 0f30aaaa5d1655867968558
     * productId : zcz004
     * equipmentId : zcz00410tt0
     * executeTime : 13:00
     * switchStatus : 1
     */

    private String brandId;
    private String modeId;
    private String productId;
    private String equipmentId;
    private String executeTime;
    private int switchStatus;
    private int remoteControlId;

    public int getRemoteControlId() {
        return remoteControlId;
    }

    public void setRemoteControlId(int remoteControlId) {
        this.remoteControlId = remoteControlId;
    }

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

    public String getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(String executeTime) {
        this.executeTime = executeTime;
    }

    public int getSwitchStatus() {
        return switchStatus;
    }

    public void setSwitchStatus(int switchStatus) {
        this.switchStatus = switchStatus;
    }
}
