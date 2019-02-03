package com.example.android.smarthome.Devices;

public class Device {


    //this class will represent the device object

    private String name;
    private int type;
    private int room;


    public Device (){


    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public int getRoom() {
        return room;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setRoom(int room) {
        this.room = room;
    }
}
