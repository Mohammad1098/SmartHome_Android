package com.example.android.smarthome.Devices;

public class Device {


    //this class will represent the device object

    private String name;
    private int type;
    private String room;
    private long id;



    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public String getRoom() {
        return room;
    }

    public long getId() { return id; }




    public void setName(String name) {this.name = name;}

    public void setType(int type) {
        this.type = type;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setId(long id) { this.id = id;}

}
