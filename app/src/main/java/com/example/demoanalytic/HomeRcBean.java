package com.example.demoanalytic;

public class HomeRcBean {

    private int deviceId;// 设备id
    private int checkedImg; //选中图片
    private int checkImg; //未选中图片

    public HomeRcBean(int deviceId, int checkedImg, int checkImg) {
        this.deviceId = deviceId;
        this.checkedImg = checkedImg;
        this.checkImg = checkImg;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public int getCheckedImg() {
        return checkedImg;
    }

    public void setCheckedImg(int checkedImg) {
        this.checkedImg = checkedImg;
    }

    public int getCheckImg() {
        return checkImg;
    }

    public void setCheckImg(int checkImg) {
        this.checkImg = checkImg;
    }
}
