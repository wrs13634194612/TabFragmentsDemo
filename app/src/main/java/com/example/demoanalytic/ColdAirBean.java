package com.example.demoanalytic;

public class ColdAirBean {

    private int keyIndex;
    private String keyName;
    private boolean isView;

    public ColdAirBean(int keyIndex, String keyName, boolean isView) {
        this.keyIndex = keyIndex;
        this.keyName = keyName;
        this.isView = isView;
    }

    public int getKeyIndex() {
        return keyIndex;
    }

    public void setKeyIndex(int keyIndex) {
        this.keyIndex = keyIndex;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public boolean isView() {
        return isView;
    }

    public void setView(boolean view) {
        isView = view;
    }
}
