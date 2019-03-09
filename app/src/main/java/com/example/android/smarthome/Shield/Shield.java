package com.example.android.smarthome.Shield;

public class Shield {

    private long MicroControllerID;
    private String Name;
    private int type;

    public void setMicroControllerID(long microControllerID) {
        MicroControllerID = microControllerID;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getMicroControllerID() {
        return MicroControllerID;
    }

    public String getName() {
        return Name;
    }

    public int getType() {
        return type;
    }
}
