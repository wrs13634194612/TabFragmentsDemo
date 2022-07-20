package com.example.demoanalytic;

public class RcDeviceBean {
    private int deviceId;       //设备ID
    private String deviceName;  //设备名称
    private int deviceIcon;  //设备图片
    private int deviceIconCheck; //选中的设备图片

    public RcDeviceBean(int deviceId, String deviceName, int deviceIcon, int deviceIconCheck) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.deviceIcon = deviceIcon;
        this.deviceIconCheck = deviceIconCheck;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public int getDeviceIcon() {
        return deviceIcon;
    }

    public void setDeviceIcon(int deviceIcon) {
        this.deviceIcon = deviceIcon;
    }

    public int getDeviceIconCheck() {
        return deviceIconCheck;
    }

    public void setDeviceIconCheck(int deviceIconCheck) {
        this.deviceIconCheck = deviceIconCheck;
    }
}
