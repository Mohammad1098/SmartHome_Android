package com.example.android.smarthome.Devices;

public class Device {


    //this class will represent the device object

    private String name;
    private int type;
    private String room;
    private long device_Id,microController_Id;



    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public String getRoom() {
        return room;
    }

    public long getId() { return device_Id; }

    public long getMicroController_Id() {return microController_Id;}




    public void setName(String name) {this.name = name;}

    public void setType(int type) {
        this.type = type;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setMicroController_Id(long microController_Id) {this.microController_Id = microController_Id;}

    public void setId(long id) { this.device_Id = id;}

}
