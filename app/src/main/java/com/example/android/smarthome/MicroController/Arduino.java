package com.example.android.smarthome.MicroController;

public class Arduino {

    private String name;

    private int[] pins;

    private String room;



    public void setName(String name) {

        this.name = name;
    }

    public void setPins(int[] pins) {
        this.pins = pins;
    }

    public void setRoom(String room) {
        this.room = room;
    }


    public String getName() {
        return name;
    }

    public int[] getPins() {
        return pins;
    }

    public String getRoom() {
        return room;
    }
}
